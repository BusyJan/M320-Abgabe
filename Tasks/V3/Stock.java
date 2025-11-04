/**
 * Repräsentiert eine Aktie im Portfolio.
 */
public class Stock {
    private String name;
    private int quantity;
    
    /**
     * Erstellt eine neue Aktie.
     * 
     * @param name Name der Aktie
     * @param quantity Anzahl der Aktien
     */
    public Stock(String name, int quantity) {
        this.name = name;
        this.quantity = quantity;
    }
    
    /**
     * Gibt den Namen der Aktie zurück.
     * 
     * @return Name der Aktie
     */
    public String getName() {
        return name;
    }
    
    /**
     * Gibt die Anzahl der Aktien zurück.
     * 
     * @return Anzahl der Aktien
     */
    public int getQuantity() {
        return quantity;
    }
    
    /**
     * Setzt die Anzahl der Aktien.
     * 
     * @param quantity Neue Anzahl
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    
    @Override
    public String toString() {
        return quantity + "x " + name;
    }
}

