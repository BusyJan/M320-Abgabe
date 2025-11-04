import java.util.Scanner;

/**
 * Hauptklasse zur Demonstration von Interfaces und Polymorphismus.
 * 
 * Das Portfolio verwendet das StockExchange-Interface, wodurch verschiedene
 * Börsen-Implementierungen transparent verwendet werden können.
 */
public class PortfolioApp {
    private static Portfolio portfolio;
    private static StockExchange[] exchanges;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        // Initialisierung
        scanner = new Scanner(System.in);
        initializePortfolio();
        initializeExchanges();
        
        // Hauptmenü
        boolean running = true;
        while (running) {
            printMainMenu();
            int choice = getIntInput();
            
            switch (choice) {
                case 1:
                    showPortfolioValue();
                    break;
                case 2:
                    compareExchanges();
                    break;
                case 3:
                    showPortfolioContents();
                    break;
                case 4:
                    addStockToPortfolio();
                    break;
                case 5:
                    demonstratePolymorphism();
                    break;
                case 0:
                    System.out.println("\n>>> Programm beendet. Auf Wiedersehen! <<<\n");
                    running = false;
                    break;
                default:
                    System.out.println("\n>>> Ungültige Eingabe! Bitte wählen Sie eine Zahl von 0-5. <<<\n");
            }
        }
        
        scanner.close();
    }
    
    /**
     * Initialisiert das Portfolio mit Beispielaktien.
     */
    private static void initializePortfolio() {
        portfolio = new Portfolio();
        portfolio.addStock("Microsoft", 10);
        portfolio.addStock("Apple", 5);
        portfolio.addStock("Google", 8);
    }
    
    /**
     * Initialisiert die verschiedenen Börsen-Implementierungen.
     * Demonstriert Polymorphismus: Alle drei Klassen implementieren StockExchange.
     */
    private static void initializeExchanges() {
        exchanges = new StockExchange[3];
        exchanges[0] = new ZurichStockExchange();
        exchanges[1] = new NewYorkStockExchange();
        exchanges[2] = new LondonStockExchange();
    }
    
    /**
     * Zeigt das Hauptmenü an.
     */
    private static void printMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              PORTFOLIO MANAGEMENT SYSTEM                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Portfolio-Wert abfragen (Börse wählen)               ║");
        System.out.println("║  [2] Alle Börsen vergleichen                              ║");
        System.out.println("║  [3] Portfolio-Inhalt anzeigen                            ║");
        System.out.println("║  [4] Aktie zum Portfolio hinzufügen                       ║");
        System.out.println("║  [5] Polymorphismus-Demonstration                         ║");
        System.out.println("║  [0] Beenden                                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        System.out.print("Ihre Wahl: ");
    }
    
    /**
     * Zeigt den Portfolio-Wert für eine gewählte Börse an.
     * Demonstriert Polymorphismus: Der Benutzer wählt die konkrete Implementierung.
     */
    private static void showPortfolioValue() {
        System.out.println("\n=== Portfolio-Wert abfragen ===");
        StockExchange selectedExchange = selectExchange();
        
        if (selectedExchange != null) {
            portfolio.printValuation(selectedExchange);
        }
    }
    
    /**
     * Vergleicht den Portfolio-Wert über alle Börsen.
     * Demonstriert Polymorphismus: Dieselbe Methode wird mit verschiedenen Implementierungen aufgerufen.
     */
    private static void compareExchanges() {
        System.out.println("\n=== Börsen-Vergleich ===");
        
        for (StockExchange exchange : exchanges) {
            portfolio.printValuation(exchange);
        }
        
        System.out.println(">>> Hinweis: Unterschiedliche Werte entstehen durch verschiedene");
        System.out.println(">>> Preise und Währungen an den jeweiligen Börsen. <<<\n");
    }
    
    /**
     * Zeigt den Inhalt des Portfolios an.
     */
    private static void showPortfolioContents() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              PORTFOLIO-INHALT                              ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        if (portfolio.size() == 0) {
            System.out.println("║  Portfolio ist leer.                                       ║");
        } else {
            for (Stock stock : portfolio.getStocks()) {
                System.out.printf("║  %-56s ║%n", stock.toString());
            }
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Fügt eine neue Aktie zum Portfolio hinzu.
     */
    private static void addStockToPortfolio() {
        System.out.println("\n=== Aktie hinzufügen ===");
        System.out.print("Aktienname: ");
        scanner.nextLine(); // Buffer leeren
        String stockName = scanner.nextLine();
        
        System.out.print("Anzahl: ");
        int quantity = getIntInput();
        
        portfolio.addStock(stockName, quantity);
        System.out.println("\n>>> Aktie erfolgreich hinzugefügt! <<<\n");
    }
    
    /**
     * Demonstriert Polymorphismus anhand eines Beispiels.
     */
    private static void demonstratePolymorphism() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║          POLYMORPHISMUS-DEMONSTRATION                      ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║                                                            ║");
        System.out.println("║  Polymorphismus bedeutet, dass ein Objekt in verschiedenen ║");
        System.out.println("║  Formen auftreten kann.                                    ║");
        System.out.println("║                                                            ║");
        System.out.println("║  In diesem Programm:                                       ║");
        System.out.println("║  - Das Portfolio kennt nur das Interface 'StockExchange'   ║");
        System.out.println("║  - Es kann mit JEDER Börse arbeiten, die dieses Interface ║");
        System.out.println("║    implementiert                                           ║");
        System.out.println("║  - Zur Laufzeit wird entschieden, welche konkrete Börse   ║");
        System.out.println("║    verwendet wird                                          ║");
        System.out.println("║                                                            ║");
        System.out.println("║  Code-Beispiel:                                            ║");
        System.out.println("║    StockExchange exchange = new ZurichStockExchange();     ║");
        System.out.println("║    portfolio.calculateValue(exchange);                     ║");
        System.out.println("║                                                            ║");
        System.out.println("║    // Später kann einfach eine andere Börse verwendet      ║");
        System.out.println("║    // werden, ohne den Portfolio-Code zu ändern!           ║");
        System.out.println("║    exchange = new NewYorkStockExchange();                  ║");
        System.out.println("║    portfolio.calculateValue(exchange);                     ║");
        System.out.println("║                                                            ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
        
        System.out.println("\n>>> Drücken Sie Enter, um fortzufahren... <<<");
        scanner.nextLine();
        scanner.nextLine();
    }
    
    /**
     * Lässt den Benutzer eine Börse auswählen.
     * 
     * @return Gewählte Börse oder null bei ungültiger Auswahl
     */
    private static StockExchange selectExchange() {
        System.out.println("\nWelche Börse möchten Sie verwenden?");
        System.out.println("[1] " + exchanges[0].getExchangeName() + " (" + exchanges[0].getCurrency() + ")");
        System.out.println("[2] " + exchanges[1].getExchangeName() + " (" + exchanges[1].getCurrency() + ")");
        System.out.println("[3] " + exchanges[2].getExchangeName() + " (" + exchanges[2].getCurrency() + ")");
        System.out.print("Ihre Wahl: ");
        
        int choice = getIntInput();
        
        if (choice >= 1 && choice <= 3) {
            return exchanges[choice - 1];
        } else {
            System.out.println("\n>>> Ungültige Auswahl! <<<\n");
            return null;
        }
    }
    
    /**
     * Liest eine Integer-Eingabe vom Benutzer ein.
     * 
     * @return Eingegebene Zahl oder -1 bei ungültiger Eingabe
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

