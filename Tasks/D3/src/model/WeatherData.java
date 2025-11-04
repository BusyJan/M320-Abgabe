package model;

/**
 * WeatherData – Modellklasse für Wetterinformationen.
 *
 * Diese Klasse dient als Datencontainer (Data Transfer Object).
 * Sie wird vom WeatherService erstellt und an den Controller übergeben.
 */
public class WeatherData {

    // Attribute
    private final String city;
    private final String temperature;
    private final String wind;

    /**
     * Konstruktor – nimmt alle relevanten Wetterdaten entgegen.
     *
     * @param city        Stadtname, den der Benutzer eingegeben hat
     * @param temperature Temperaturwert aus der API (in °C)
     * @param wind        Windgeschwindigkeit aus der API (in km/h)
     */
    public WeatherData(String city, String temperature, String wind) {
        this.city = city;
        this.temperature = temperature;
        this.wind = wind;
    }

    // Getter-Methoden
    public String getCity() {
        return city;
    }

    public String getTemperature() {
        return temperature;
    }

    public String getWind() {
        return wind;
    }

    /**
     * Formatiert die Wetterdaten für die Anzeige im Terminal.
     */
    @Override
    public String toString() {
        return String.format(
                "Aktuelles Wetter in %s:%nTemperatur: %s°C%nWind: %s km/h%n",
                city, temperature, wind
        );
    }
}
