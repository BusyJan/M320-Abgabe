import java.util.ArrayList;
import java.util.List;

/**
 * Portfolio-Klasse verwaltet eine Sammlung von Aktien.
 * Demonstriert Polymorphismus: Das Portfolio kennt nur das StockExchange-Interface,
 * nicht die konkreten Implementierungen.
 */
public class Portfolio {
    private List<Stock> stocks;
    
    /**
     * Erstellt ein neues leeres Portfolio.
     */
    public Portfolio() {
        this.stocks = new ArrayList<>();
    }
    
    /**
     * Fügt eine Aktie zum Portfolio hinzu.
     * 
     * @param stock Aktie, die hinzugefügt werden soll
     */
    public void addStock(Stock stock) {
        stocks.add(stock);
    }
    
    /**
     * Fügt eine Aktie zum Portfolio hinzu.
     * 
     * @param stockName Name der Aktie
     * @param quantity Anzahl der Aktien
     */
    public void addStock(String stockName, int quantity) {
        stocks.add(new Stock(stockName, quantity));
    }
    
    /**
     * Gibt alle Aktien im Portfolio zurück.
     * 
     * @return Liste aller Aktien
     */
    public List<Stock> getStocks() {
        return stocks;
    }
    
    /**
     * Berechnet den Gesamtwert des Portfolios.
     * Polymorphismus: Diese Methode funktioniert mit jeder Implementierung von StockExchange!
     * 
     * @param exchange Börse, von der die Preise abgerufen werden sollen
     * @return Gesamtwert des Portfolios in der Währung der gewählten Börse
     */
    public double calculateValue(StockExchange exchange) {
        double totalValue = 0.0;
        
        for (Stock stock : stocks) {
            double price = exchange.getPrice(stock.getName());
            if (price > 0) {
                totalValue += price * stock.getQuantity();
            }
        }
        
        return totalValue;
    }
    
    /**
     * Gibt eine detaillierte Bewertung des Portfolios aus.
     * 
     * @param exchange Börse, von der die Preise abgerufen werden sollen
     */
    public void printValuation(StockExchange exchange) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║         PORTFOLIO BEWERTUNG                                ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  Börse: " + String.format("%-48s", exchange.getExchangeName()) + "║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        double totalValue = 0.0;
        
        for (Stock stock : stocks) {
            double price = exchange.getPrice(stock.getName());
            if (price > 0) {
                double value = price * stock.getQuantity();
                totalValue += value;
                System.out.printf("║  %-20s  %3dx  @%8.2f = %10.2f %-4s ║%n", 
                    stock.getName(), stock.getQuantity(), price, value, exchange.getCurrency());
            } else {
                System.out.printf("║  %-20s  %3dx  nicht verfügbar              ║%n", 
                    stock.getName(), stock.getQuantity());
            }
        }
        
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║  GESAMTWERT:                           %10.2f %-4s ║%n", 
            totalValue, exchange.getCurrency());
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    /**
     * Gibt die Anzahl der Aktien im Portfolio zurück.
     * 
     * @return Anzahl verschiedener Aktien
     */
    public int size() {
        return stocks.size();
    }
}

