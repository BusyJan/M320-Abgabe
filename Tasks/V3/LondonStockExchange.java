import java.util.HashMap;
import java.util.Map;

/**
 * Implementierung der Londoner Börse (LSE - London Stock Exchange).
 * Preise sind in Britischen Pfund (GBP).
 */
public class LondonStockExchange implements StockExchange {
    private Map<String, Double> stockPrices;
    
    /**
     * Konstruktor initialisiert die Börse mit Beispieldaten.
     */
    public LondonStockExchange() {
        stockPrices = new HashMap<>();
        // Beispieldaten für Aktienpreise in GBP
        stockPrices.put("Microsoft", 85.0);
        stockPrices.put("Apple", 130.0);
        stockPrices.put("Google", 105.0);
        stockPrices.put("BP", 45.0);
        stockPrices.put("Barclays", 18.0);
        stockPrices.put("HSBC", 62.0);
    }
    
    @Override
    public double getPrice(String stockName) {
        return stockPrices.getOrDefault(stockName, -1.0);
    }
    
    @Override
    public String getExchangeName() {
        return "London (LSE)";
    }
    
    @Override
    public String getCurrency() {
        return "GBP";
    }
    
    /**
     * Fügt eine neue Aktie hinzu oder aktualisiert den Preis.
     * 
     * @param stockName Name der Aktie
     * @param price Preis in GBP
     */
    public void setPrice(String stockName, double price) {
        stockPrices.put(stockName, price);
    }
}

