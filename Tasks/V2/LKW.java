/**
 * Repräsentiert einen Lastkraftwagen (LKW) als spezifischen Fahrzeugtyp.
 * Erbt von Fahrzeug und überschreibt die Kostenberechnung für LKWs.
 * LKWs haben die höchsten Reparaturkosten unter den Fahrzeugtypen.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class LKW extends Fahrzeug {
    
    /**
     * Konstruktor für einen neuen LKW.
     * Ruft den Konstruktor der Basisklasse Fahrzeug auf.
     * 
     * @param marke Die Marke des LKWs
     * @param kilometerstand Der aktuelle Kilometerstand
     */
    public LKW(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    /**
     * Berechnet die Reparaturkosten für einen LKW.
     * LKWs haben die höchsten Kosten: 0.5€ pro Kilometer plus 500€ Grundkosten.
     * 
     * @return Die berechneten Reparaturkosten in Euro
     */
    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.5 + 500;
    }
}
