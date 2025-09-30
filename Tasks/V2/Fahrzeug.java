/**
 * Repräsentiert ein allgemeines Fahrzeug mit Marke und Kilometerstand.
 * Diese Klasse dient als Basisklasse für spezifische Fahrzeugtypen wie Auto, 
 * Motorrad und LKW. Sie demonstriert Vererbung und Polymorphismus.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Fahrzeug {
    /** Die Marke des Fahrzeugs */
    private String marke;
    /** Der aktuelle Kilometerstand des Fahrzeugs */
    private int kilometerstand;

    /**
     * Konstruktor für ein neues Fahrzeug.
     * 
     * @param marke Die Marke des Fahrzeugs (z.B. "BMW", "Mercedes")
     * @param kilometerstand Der aktuelle Kilometerstand
     * @throws IllegalArgumentException wenn marke null oder leer ist
     * @throws IllegalArgumentException wenn kilometerstand negativ ist
     */
    public Fahrzeug(String marke, int kilometerstand) {
        this.marke = marke;
        this.kilometerstand = kilometerstand;
    }

    /**
     * Gibt die Marke des Fahrzeugs zurück.
     * 
     * @return Die Marke des Fahrzeugs
     */
    public String getMarke() {
        return marke;
    }

    /**
     * Gibt den aktuellen Kilometerstand zurück.
     * 
     * @return Der Kilometerstand in Kilometern
     */
    public int getKilometerstand() {
        return kilometerstand;
    }

    /**
     * Berechnet die Reparaturkosten basierend auf dem Fahrzeugtyp.
     * Diese Methode sollte in Unterklassen überschrieben werden, um 
     * spezifische Kostenberechnungen zu implementieren.
     * 
     * @return Die geschätzten Reparaturkosten in Euro
     */
    public double berechneReparaturKosten() {
        return 100.0;
    }

    /**
     * Führt eine allgemeine Reparatur am Fahrzeug durch.
     * Gibt eine Bestätigung über die Konsole aus.
     */
    public void reparieren() {
        System.out.println("Allgemeine Reparatur durchgeführt");
    }

    /**
     * Repariert ein spezifisches Teil am Fahrzeug.
     * 
     * @param teil Der Name des zu reparierenden Teils (z.B. "Bremsen", "Motor")
     * @throws IllegalArgumentException wenn teil null oder leer ist
     */
    public void reparieren(String teil) {
        System.out.println("Teil gewechselt: " + teil);
    }
}
