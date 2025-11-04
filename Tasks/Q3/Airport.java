/**
 * Repräsentiert einen Flughafen mit allen relevanten Informationen.
 */
public class Airport {
    private String code;        // IATA-Code (z.B. "JFK")
    private String name;        // Name des Flughafens
    private String city;        // Stadt
    private String state;       // Bundesstaat
    private String country;     // Land
    
    /**
     * Erstellt einen neuen Flughafen.
     * 
     * @param code IATA-Code
     * @param name Name des Flughafens
     * @param city Stadt
     * @param state Bundesstaat
     * @param country Land
     */
    public Airport(String code, String name, String city, String state, String country) {
        this.code = code;
        this.name = name;
        this.city = city;
        this.state = state;
        this.country = country;
    }
    
    /**
     * Erstellt einen neuen Flughafen (vereinfachte Version).
     * 
     * @param code IATA-Code
     * @param name Name des Flughafens
     */
    public Airport(String code, String name) {
        this(code, name, "", "", "");
    }
    
    public String getCode() {
        return code;
    }
    
    public String getName() {
        return name;
    }
    
    public String getCity() {
        return city;
    }
    
    public String getState() {
        return state;
    }
    
    public String getCountry() {
        return country;
    }
    
    @Override
    public String toString() {
        if (city.isEmpty() && state.isEmpty() && country.isEmpty()) {
            return String.format("%s - %s", code, name);
        }
        return String.format("%s - %s, %s, %s (%s)", 
            code, name, city, state, country);
    }
    
    /**
     * Gibt eine detaillierte Beschreibung des Flughafens zurück.
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Code:    ").append(code).append("\n");
        sb.append("Name:    ").append(name);
        
        if (!city.isEmpty()) {
            sb.append("\n").append("City:    ").append(city);
        }
        if (!state.isEmpty()) {
            sb.append("\n").append("State:   ").append(state);
        }
        if (!country.isEmpty()) {
            sb.append("\n").append("Country: ").append(country);
        }
        
        return sb.toString();
    }
}

