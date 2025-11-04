/**
 * Interface für Börsen.
 * Demonstriert Polymorphismus - verschiedene Börsen können hinter diesem Interface versteckt werden.
 */
public interface StockExchange {
    /**
     * Gibt den aktuellen Preis einer Aktie zurück.
     * 
     * @param stockName Name der Aktie
     * @return Preis der Aktie, oder -1 wenn die Aktie nicht gefunden wurde
     */
    double getPrice(String stockName);
    
    /**
     * Gibt den Namen der Börse zurück.
     * 
     * @return Name der Börse
     */
    String getExchangeName();
    
    /**
     * Gibt die Währung der Börse zurück.
     * 
     * @return Währung (z.B. "CHF", "USD")
     */
    String getCurrency();
}

