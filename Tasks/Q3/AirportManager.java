import java.util.*;
import java.io.*;

/**
 * Verwaltet Flughäfen in einer HashMap.
 * Demonstriert die Verwendung der Map-Datenstruktur.
 */
public class AirportManager {
    private Map<String, Airport> airports;
    private List<String> searchHistory;
    
    /**
     * Erstellt einen neuen AirportManager.
     */
    public AirportManager() {
        this.airports = new HashMap<>();
        this.searchHistory = new ArrayList<>();
    }
    
    /**
     * Fügt einen Flughafen hinzu.
     * Wenn der Code bereits existiert, wird der alte Wert überschrieben.
     * 
     * @param airport Flughafen, der hinzugefügt werden soll
     * @return true wenn ein neuer Eintrag erstellt wurde, false wenn überschrieben
     */
    public boolean addAirport(Airport airport) {
        boolean isNew = !airports.containsKey(airport.getCode());
        airports.put(airport.getCode(), airport);
        return isNew;
    }
    
    /**
     * Sucht einen Flughafen nach seinem Code.
     * Die Suche wird in der Historie gespeichert.
     * 
     * @param code IATA-Code des Flughafens
     * @return Flughafen oder null wenn nicht gefunden
     */
    public Airport search(String code) {
        String upperCode = code.toUpperCase();
        searchHistory.add(0, upperCode); // Am Anfang einfügen (neueste zuerst)
        return airports.get(upperCode);
    }
    
    /**
     * Entfernt einen Flughafen.
     * 
     * @param code IATA-Code des zu entfernenden Flughafens
     * @return true wenn erfolgreich entfernt, false wenn nicht gefunden
     */
    public boolean remove(String code) {
        return airports.remove(code.toUpperCase()) != null;
    }
    
    /**
     * Gibt alle Flughäfen sortiert nach Code zurück.
     * 
     * @return Sortierte Liste von Flughäfen
     */
    public List<Airport> getSortedAirports() {
        List<Airport> sortedList = new ArrayList<>(airports.values());
        sortedList.sort(Comparator.comparing(Airport::getCode));
        return sortedList;
    }
    
    /**
     * Zeigt alle Flughäfen an.
     * Demonstriert die Verwendung von forEach mit Lambda-Ausdrücken.
     */
    public void displayAll() {
        if (airports.isEmpty()) {
            System.out.println("Keine Flughäfen in der Datenbank.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                         ALLE FLUGHÄFEN                                     ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        
        // Lambda-Expression für Iteration
        airports.forEach((code, airport) -> 
            System.out.printf("║ %-74s ║%n", airport.toString())
        );
        
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Total: " + airports.size() + " Flughäfen\n");
    }
    
    /**
     * Zeigt alle Flughäfen alphabetisch sortiert an.
     */
    public void displaySorted() {
        if (airports.isEmpty()) {
            System.out.println("Keine Flughäfen in der Datenbank.");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║                   FLUGHÄFEN (ALPHABETISCH SORTIERT)                        ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════════╣");
        
        List<Airport> sorted = getSortedAirports();
        for (Airport airport : sorted) {
            System.out.printf("║ %-74s ║%n", airport.toString());
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════════════════════╝");
        System.out.println("Total: " + sorted.size() + " Flughäfen\n");
    }
    
    /**
     * Zeigt die Such-Historie an (neueste zuerst).
     */
    public void displaySearchHistory() {
        if (searchHistory.isEmpty()) {
            System.out.println("\nKeine Suchvorgänge in der Historie.\n");
            return;
        }
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                    SUCH-HISTORIE                           ║");
        System.out.println("║                  (Neueste zuerst)                          ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        int count = 1;
        for (String searchCode : searchHistory) {
            System.out.printf("║  %3d. %-50s ║%n", count++, searchCode);
            if (count > 20) { // Maximal 20 Einträge anzeigen
                System.out.println("║       ... und " + (searchHistory.size() - 20) + " weitere      ║");
                break;
            }
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Importiert Flughäfen aus einer CSV-Datei.
     * Format: CODE,NAME oder CODE,NAME,CITY,STATE,COUNTRY
     * 
     * @param filename Pfad zur CSV-Datei
     * @return Anzahl der importierten Flughäfen
     */
    public int importFromCSV(String filename) {
        int count = 0;
        
        try (BufferedReader br = new BufferedReader(new FileReader(filename))) {
            String line;
            boolean firstLine = true;
            
            while ((line = br.readLine()) != null) {
                // Erste Zeile überspringen (Header)
                if (firstLine) {
                    firstLine = false;
                    continue;
                }
                
                // Leere Zeilen überspringen
                if (line.trim().isEmpty()) {
                    continue;
                }
                
                String[] parts = line.split(",");
                
                if (parts.length >= 2) {
                    String code = parts[0].trim().toUpperCase();
                    String name = parts[1].trim();
                    
                    Airport airport;
                    
                    // Format mit 5 Spalten (CODE,NAME,CITY,STATE,COUNTRY)
                    if (parts.length >= 5) {
                        String city = parts[2].trim();
                        String state = parts[3].trim();
                        String country = parts[4].trim();
                        airport = new Airport(code, name, city, state, country);
                    } 
                    // Format mit 2 Spalten (CODE,NAME)
                    else {
                        airport = new Airport(code, name);
                    }
                    
                    addAirport(airport);
                    count++;
                }
            }
            
            System.out.println("✓ Erfolgreich " + count + " Flughäfen aus " + filename + " importiert.");
            
        } catch (FileNotFoundException e) {
            System.out.println("✗ Datei nicht gefunden: " + filename);
            System.out.println("  Erstelle Beispieldaten...");
            createSampleData();
        } catch (IOException e) {
            System.out.println("✗ Fehler beim Lesen der Datei: " + e.getMessage());
        }
        
        return count;
    }
    
    /**
     * Erstellt Beispieldaten wenn keine CSV-Datei vorhanden ist.
     */
    private void createSampleData() {
        addAirport(new Airport("JFK", "John F. Kennedy International Airport", "New York", "NY", "USA"));
        addAirport(new Airport("LAX", "Los Angeles International Airport", "Los Angeles", "CA", "USA"));
        addAirport(new Airport("ORD", "O'Hare International Airport", "Chicago", "IL", "USA"));
        addAirport(new Airport("DFW", "Dallas/Fort Worth International Airport", "Dallas", "TX", "USA"));
        addAirport(new Airport("DEN", "Denver International Airport", "Denver", "CO", "USA"));
        addAirport(new Airport("SFO", "San Francisco International Airport", "San Francisco", "CA", "USA"));
        addAirport(new Airport("SEA", "Seattle-Tacoma International Airport", "Seattle", "WA", "USA"));
        addAirport(new Airport("LAS", "Harry Reid International Airport", "Las Vegas", "NV", "USA"));
        addAirport(new Airport("MCO", "Orlando International Airport", "Orlando", "FL", "USA"));
        addAirport(new Airport("MIA", "Miami International Airport", "Miami", "FL", "USA"));
        
        System.out.println("✓ " + airports.size() + " Beispiel-Flughäfen erstellt.");
    }
    
    /**
     * Gibt die Anzahl der Flughäfen zurück.
     */
    public int size() {
        return airports.size();
    }
    
    /**
     * Prüft ob die Datenbank leer ist.
     */
    public boolean isEmpty() {
        return airports.isEmpty();
    }
}

