import java.util.HashMap;
import java.util.Map;

/**
 * Implementierung der Zürcher Börse (SIX Swiss Exchange).
 * Preise sind in Schweizer Franken (CHF).
 */
public class ZurichStockExchange implements StockExchange {
    private Map<String, Double> stockPrices;
    
    /**
     * Konstruktor initialisiert die Börse mit Beispieldaten.
     */
    public ZurichStockExchange() {
        stockPrices = new HashMap<>();
        // Beispieldaten für Aktienpreise in CHF
        stockPrices.put("Microsoft", 120.0);
        stockPrices.put("Apple", 180.0);
        stockPrices.put("Google", 140.0);
        stockPrices.put("Nestlé", 110.0);
        stockPrices.put("Novartis", 95.0);
        stockPrices.put("UBS", 25.0);
    }
    
    @Override
    public double getPrice(String stockName) {
        return stockPrices.getOrDefault(stockName, -1.0);
    }
    
    @Override
    public String getExchangeName() {
        return "Zürich (SIX Swiss Exchange)";
    }
    
    @Override
    public String getCurrency() {
        return "CHF";
    }
    
    /**
     * Fügt eine neue Aktie hinzu oder aktualisiert den Preis.
     * 
     * @param stockName Name der Aktie
     * @param price Preis in CHF
     */
    public void setPrice(String stockName, double price) {
        stockPrices.put(stockName, price);
    }
}

