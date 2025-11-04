import java.util.Scanner;

/**
 * Hauptklasse für die Airport-Management-Anwendung.
 * Demonstriert die Verwendung von HashMap und Lambda-Ausdrücken.
 */
public class AirportApp {
    private static AirportManager manager;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        scanner = new Scanner(System.in);
        manager = new AirportManager();
        
        // Daten importieren
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          AIRPORT MANAGEMENT SYSTEM                         ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        
        manager.importFromCSV("airports.csv");
        
        // Hauptmenü
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    searchAirport();
                    break;
                case 2:
                    displayAllAirports();
                    break;
                case 3:
                    displaySortedAirports();
                    break;
                case 4:
                    addNewAirport();
                    break;
                case 5:
                    removeAirport();
                    break;
                case 6:
                    showSearchHistory();
                    break;
                case 7:
                    demonstrateLambda();
                    break;
                case 0:
                    System.out.println("\n>>> Programm beendet. Auf Wiedersehen! <<<\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n>>> Ungültige Eingabe! Bitte wählen Sie 0-7. <<<\n");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Zeigt das Hauptmenü an.
     */
    private static void printMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              AIRPORT MANAGEMENT SYSTEM                     ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Flughafen suchen (nach Code)                         ║");
        System.out.println("║  [2] Alle Flughäfen anzeigen                              ║");
        System.out.println("║  [3] Flughäfen alphabetisch sortiert anzeigen             ║");
        System.out.println("║  [4] Neuen Flughafen hinzufügen                           ║");
        System.out.println("║  [5] Flughafen entfernen                                  ║");
        System.out.println("║  [6] Such-Historie anzeigen                               ║");
        System.out.println("║  [7] Lambda-Expression Demonstration                      ║");
        System.out.println("║  [0] Beenden                                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("Ihre Wahl: ");
    }
    
    /**
     * Sucht nach einem Flughafen.
     */
    private static void searchAirport() {
        System.out.print("\nFlughafen-Code eingeben (z.B. JFK): ");
        scanner.nextLine(); // Buffer leeren
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("\n>>> Kein Code eingegeben! <<<\n");
            return;
        }
        
        Airport airport = manager.search(code);
        
        if (airport != null) {
            System.out.println("\n╔════════════════════════════════════════════════════════════╗");
            System.out.println("║                  FLUGHAFEN GEFUNDEN                        ║");
            System.out.println("╠════════════════════════════════════════════════════════════╣");
            String[] lines = airport.toDetailedString().split("\n");
            for (String line : lines) {
                System.out.printf("║  %-56s ║%n", line);
            }
            System.out.println("╚════════════════════════════════════════════════════════════╝\n");
        } else {
            System.out.println("\n>>> Flughafen mit Code '" + code + "' nicht gefunden! <<<\n");
        }
    }
    
    /**
     * Zeigt alle Flughäfen an.
     */
    private static void displayAllAirports() {
        manager.displayAll();
    }
    
    /**
     * Zeigt alle Flughäfen alphabetisch sortiert an.
     */
    private static void displaySortedAirports() {
        manager.displaySorted();
    }
    
    /**
     * Fügt einen neuen Flughafen hinzu.
     */
    private static void addNewAirport() {
        System.out.println("\n=== Neuen Flughafen hinzufügen ===");
        scanner.nextLine(); // Buffer leeren
        
        System.out.print("Code (z.B. JFK): ");
        String code = scanner.nextLine().trim().toUpperCase();
        
        System.out.print("Name: ");
        String name = scanner.nextLine().trim();
        
        System.out.print("Stadt: ");
        String city = scanner.nextLine().trim();
        
        System.out.print("Bundesstaat: ");
        String state = scanner.nextLine().trim();
        
        System.out.print("Land: ");
        String country = scanner.nextLine().trim();
        
        if (code.isEmpty() || name.isEmpty()) {
            System.out.println("\n>>> Code und Name sind Pflichtfelder! <<<\n");
            return;
        }
        
        Airport airport = new Airport(code, name, city, state, country);
        boolean isNew = manager.addAirport(airport);
        
        if (isNew) {
            System.out.println("\n>>> Flughafen erfolgreich hinzugefügt! <<<\n");
        } else {
            System.out.println("\n>>> Flughafen mit Code '" + code + "' wurde überschrieben! <<<\n");
        }
    }
    
    /**
     * Entfernt einen Flughafen.
     */
    private static void removeAirport() {
        System.out.print("\nFlughafen-Code zum Entfernen eingeben: ");
        scanner.nextLine(); // Buffer leeren
        String code = scanner.nextLine().trim();
        
        if (code.isEmpty()) {
            System.out.println("\n>>> Kein Code eingegeben! <<<\n");
            return;
        }
        
        boolean removed = manager.remove(code);
        
        if (removed) {
            System.out.println("\n>>> Flughafen '" + code + "' erfolgreich entfernt! <<<\n");
        } else {
            System.out.println("\n>>> Flughafen '" + code + "' nicht gefunden! <<<\n");
        }
    }
    
    /**
     * Zeigt die Such-Historie an.
     */
    private static void showSearchHistory() {
        manager.displaySearchHistory();
    }
    
    /**
     * Demonstriert die Verwendung von Lambda-Ausdrücken.
     */
    private static void demonstrateLambda() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            LAMBDA-EXPRESSION DEMONSTRATION                 ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                            ║");
        System.out.println("║  Lambda-Ausdrücke ermöglichen kompakte funktionale         ║");
        System.out.println("║  Programmierung in Java.                                   ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Beispiel - Iteration mit forEach:                         ║");
        System.out.println("║                                                            ║");
        System.out.println("║  myHash.forEach((k,v) -> {                                 ║");
        System.out.println("║      System.out.println(\"Key: \" + k + \": Value: \" + v); ║");
        System.out.println("║  });                                                       ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Wird verwendet in:                                        ║");
        System.out.println("║  - AirportManager.displayAll()                             ║");
        System.out.println("║  - AirportManager.getSortedAirports()                      ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n>>> Drücken Sie Enter, um fortzufahren... <<<");
        scanner.nextLine();
        scanner.nextLine();
    }
    
    /**
     * Liest eine Integer-Eingabe vom Benutzer ein.
     */
    private static int getIntInput() {
        try {
            return scanner.nextInt();
        } catch (Exception e) {
            scanner.nextLine(); // Buffer leeren
            return -1;
        }
    }
}

