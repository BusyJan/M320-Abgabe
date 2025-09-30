/**
 * Repräsentiert ein Motorrad als spezifischen Fahrzeugtyp.
 * Erbt von Fahrzeug und überschreibt die Kostenberechnung für Motorräder.
 * Motorräder haben die niedrigsten Reparaturkosten unter den Fahrzeugtypen.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Motorrad extends Fahrzeug {
    
    /**
     * Konstruktor für ein neues Motorrad.
     * Ruft den Konstruktor der Basisklasse Fahrzeug auf.
     * 
     * @param marke Die Marke des Motorrads
     * @param kilometerstand Der aktuelle Kilometerstand
     */
    public Motorrad(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    /**
     * Berechnet die Reparaturkosten für ein Motorrad.
     * Motorräder haben die niedrigsten Kosten: 0.1€ pro Kilometer plus 100€ Grundkosten.
     * 
     * @return Die berechneten Reparaturkosten in Euro
     */
    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.1 + 100;
    }
}
