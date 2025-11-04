import java.util.HashMap;
import java.util.Map;

/**
 * Implementierung der New Yorker Börse (NYSE).
 * Preise sind in US-Dollar (USD).
 */
public class NewYorkStockExchange implements StockExchange {
    private Map<String, Double> stockPrices;
    
    /**
     * Konstruktor initialisiert die Börse mit Beispieldaten.
     */
    public NewYorkStockExchange() {
        stockPrices = new HashMap<>();
        // Beispieldaten für Aktienpreise in USD
        stockPrices.put("Microsoft", 100.0);
        stockPrices.put("Apple", 150.0);
        stockPrices.put("Google", 120.0);
        stockPrices.put("Tesla", 220.0);
        stockPrices.put("Amazon", 135.0);
        stockPrices.put("Meta", 305.0);
    }
    
    @Override
    public double getPrice(String stockName) {
        return stockPrices.getOrDefault(stockName, -1.0);
    }
    
    @Override
    public String getExchangeName() {
        return "New York (NYSE)";
    }
    
    @Override
    public String getCurrency() {
        return "USD";
    }
    
    /**
     * Fügt eine neue Aktie hinzu oder aktualisiert den Preis.
     * 
     * @param stockName Name der Aktie
     * @param price Preis in USD
     */
    public void setPrice(String stockName, double price) {
        stockPrices.put(stockName, price);
    }
}

