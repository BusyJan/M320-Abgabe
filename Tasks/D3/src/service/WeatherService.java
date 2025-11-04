package service;

import model.WeatherData;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Scanner;

/**
 * WeatherService - Ruft Echtzeit-Wetterdaten von einer externen API ab.
 * 
 * DELEGATION: Diese Klasse ist verantwortlich für die externe API-Kommunikation.
 * Der Controller ruft nur diese Methode auf, ohne die API-Details zu kennen.
 * 
 * Verwendet Open-Meteo API (kostenlos, keine API-Key erforderlich):
 * 1. Geocoding: Stadt → Koordinaten
 * 2. Weather: Koordinaten → Wetterdaten
 */
public class WeatherService {

    /**
     * Ruft Wetterdaten für eine Stadt ab und zeigt sie an.
     * 
     * @param city Name der Stadt
     */
    public void showWeather(String city) {
        try {
            WeatherData data = fetchWeather(city);
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                    WETTER-DATEN                            ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            System.out.printf("║  Stadt:         %-42s  ║%n", data.getCity());
            System.out.printf("║  Temperatur:    %-38s°C  ║%n", data.getTemperature());
            System.out.printf("║  Wind:          %-36s km/h  ║%n", data.getWind());
            System.out.printf("║  Beschreibung:  %-42s  ║%n", data.getDescription());
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        } catch (IOException e) {
            System.out.println("\n>>> FEHLER: Wetterdaten konnten nicht abgerufen werden.");
            System.out.println(">>> Details: " + e.getMessage() + " <<<\n");
        }
    }

    /**
     * Ruft Wetterdaten für eine Stadt ab (ohne Anzeige).
     * 
     * @param city Name der Stadt
     * @return WeatherData-Objekt mit den Wetterdaten
     * @throws IOException wenn API-Aufruf fehlschlägt
     */
    public WeatherData fetchWeather(String city) throws IOException {
        // Schritt 1: Stadt in Koordinaten umwandeln (Geocoding)
        Coordinates coords = geocodeCity(city);
        
        if (coords == null) {
            throw new IOException("Stadt '" + city + "' nicht gefunden!");
        }
        
        // Schritt 2: Wetterdaten für Koordinaten abrufen
        return fetchWeatherByCoordinates(city, coords.latitude, coords.longitude);
    }

    /**
     * Geocoding: Wandelt Stadtnamen in Koordinaten um.
     * Verwendet Open-Meteo Geocoding API.
     * 
     * @param city Stadtname
     * @return Koordinaten oder null wenn Stadt nicht gefunden
     * @throws IOException bei Netzwerkfehlern
     */
    private Coordinates geocodeCity(String city) throws IOException {
        // URL-Encoding für Sonderzeichen (z.B. "New York" → "New%20York")
        String encodedCity = URLEncoder.encode(city, StandardCharsets.UTF_8.toString());
        
        // Open-Meteo Geocoding API
        String url = String.format(
            "https://geocoding-api.open-meteo.com/v1/search?name=%s&count=1&language=de&format=json",
            encodedCity
        );
        
        // API-Aufruf
        String json = callAPI(url);
        
        // Prüfen ob Stadt gefunden wurde
        if (!json.contains("\"results\":[{")) {
            return null; // Stadt nicht gefunden
        }
        
        // JSON parsen (einfaches String-Splitting, keine externe Library nötig)
        try {
            String resultsSection = json.split("\"results\":\\[\\{")[1].split("}\\]")[0];
            
            String latStr = resultsSection.split("\"latitude\":")[1].split(",")[0];
            String lonStr = resultsSection.split("\"longitude\":")[1].split(",")[0];
            
            double lat = Double.parseDouble(latStr);
            double lon = Double.parseDouble(lonStr);
            
            return new Coordinates(lat, lon);
            
        } catch (Exception e) {
            throw new IOException("Fehler beim Parsen der Geocoding-Daten: " + e.getMessage());
        }
    }

    /**
     * Ruft Wetterdaten für spezifische Koordinaten ab.
     * Verwendet Open-Meteo Weather API.
     * 
     * @param city Stadtname (für Anzeige)
     * @param latitude Breitengrad
     * @param longitude Längengrad
     * @return WeatherData-Objekt
     * @throws IOException bei Fehlern
     */
    private WeatherData fetchWeatherByCoordinates(String city, double latitude, double longitude) throws IOException {
        // Open-Meteo Weather API mit allen gewünschten Parametern
        String url = String.format(
            "https://api.open-meteo.com/v1/forecast?latitude=%.4f&longitude=%.4f&current_weather=true&timezone=auto",
            latitude, longitude
        );
        
        // API-Aufruf
        String json = callAPI(url);
        
        // Prüfen ob Daten vorhanden
        if (!json.contains("\"current_weather\":{")) {
            throw new IOException("Keine Wetterdaten in API-Antwort gefunden!");
        }
        
        // JSON parsen
        try {
            String weatherSection = json.split("\"current_weather\":\\{")[1].split("}")[0];
            
            // Temperatur extrahieren
            String tempStr = weatherSection.split("\"temperature\":")[1].split(",")[0];
            
            // Windgeschwindigkeit extrahieren
            String windStr = weatherSection.split("\"windspeed\":")[1].split(",")[0];
            
            // Wettercode extrahieren (für Beschreibung)
            String weatherCodeStr = weatherSection.split("\"weathercode\":")[1].split(",")[0];
            int weatherCode = Integer.parseInt(weatherCodeStr.trim());
            
            // WeatherData-Objekt erstellen
            return new WeatherData(
                city,
                tempStr,
                windStr,
                getWeatherDescription(weatherCode)
            );
            
        } catch (Exception e) {
            throw new IOException("Fehler beim Parsen der Wetterdaten: " + e.getMessage());
        }
    }

    /**
     * Führt einen API-Aufruf durch und gibt die Antwort als String zurück.
     * 
     * @param urlString URL der API
     * @return JSON-Response als String
     * @throws IOException bei Netzwerkfehlern
     */
    private String callAPI(String urlString) throws IOException {
        URL url = new URL(urlString);
        Scanner scanner = new Scanner(url.openStream(), StandardCharsets.UTF_8.toString());
        StringBuilder json = new StringBuilder();
        
        while (scanner.hasNext()) {
            json.append(scanner.nextLine());
        }
        
        scanner.close();
        return json.toString();
    }

    /**
     * Wandelt WMO Weather Code in lesbare Beschreibung um.
     * Quelle: https://open-meteo.com/en/docs
     * 
     * @param code WMO Weather Code
     * @return Wetterbeschreibung auf Deutsch
     */
    private String getWeatherDescription(int code) {
        switch (code) {
            case 0: return "Klarer Himmel";
            case 1: return "Überwiegend klar";
            case 2: return "Teilweise bewölkt";
            case 3: return "Bedeckt";
            case 45: return "Nebel";
            case 48: return "Gefrierender Nebel";
            case 51: return "Leichter Nieselregen";
            case 53: return "Mäßiger Nieselregen";
            case 55: return "Starker Nieselregen";
            case 61: return "Leichter Regen";
            case 63: return "Mäßiger Regen";
            case 65: return "Starker Regen";
            case 71: return "Leichter Schneefall";
            case 73: return "Mäßiger Schneefall";
            case 75: return "Starker Schneefall";
            case 77: return "Schneegriesel";
            case 80: return "Leichte Regenschauer";
            case 81: return "Mäßige Regenschauer";
            case 82: return "Starke Regenschauer";
            case 85: return "Leichte Schneeschauer";
            case 86: return "Starke Schneeschauer";
            case 95: return "Gewitter";
            case 96: return "Gewitter mit leichtem Hagel";
            case 99: return "Gewitter mit starkem Hagel";
            default: return "Unbekannt (" + code + ")";
        }
    }

    /**
     * Hilfklasse für Koordinaten.
     */
    private static class Coordinates {
        double latitude;
        double longitude;
        
        Coordinates(double latitude, double longitude) {
            this.latitude = latitude;
            this.longitude = longitude;
        }
    }
}
