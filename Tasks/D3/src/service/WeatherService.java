package service;

import model.WeatherData;

import java.io.IOException;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Scanner;

public class WeatherService {

    public void showWeather(String city) {
        try {
            WeatherData data = fetchWeather(city);
            System.out.printf(
                    "Aktuelles Wetter in %s:%nTemperatur: %s°C%nWind: %s km/h%n",
                    data.getCity(), data.getTemperature(), data.getWind()
            );
        } catch (IOException e) {
            System.out.println("Fehler beim Abrufen der Wetterdaten.");
        }
    }

    private WeatherData fetchWeather(String city) throws IOException {
        // Open-Meteo API (fixe Koordinaten – Zürich)
        String url = "https://api.open-meteo.com/v1/forecast?latitude=47.37&longitude=8.54&current_weather=true";

        // Daten laden
        Scanner sc = new Scanner(new URL(url).openStream());
        StringBuilder json = new StringBuilder();
        while (sc.hasNext()) json.append(sc.nextLine());
        sc.close();

        // Nur den Teil nach "current_weather" auswerten
        String js = json.toString();
        if (!js.contains("\"current_weather\":")) {
            throw new IOException("API-Daten ungültig oder nicht gefunden.");
        }

        String currentSection = js.split("\"current_weather\":\\{")[1];
        // Trennt ab bei der schließenden Klammer des JSON-Blocks
        currentSection = currentSection.split("}")[0];

        // Temperatur und Wind extrahieren
        String temp = currentSection.split("\"temperature\":")[1].split(",")[0];
        String wind = currentSection.split("\"windspeed\":")[1].split(",")[0];

        // Objekt zurückgeben
        return new WeatherData(city, temp, wind);
    }

}
