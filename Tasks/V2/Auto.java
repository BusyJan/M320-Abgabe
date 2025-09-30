/**
 * Repräsentiert ein Auto als spezifischen Fahrzeugtyp.
 * Erbt von Fahrzeug und überschreibt die Kostenberechnung für Autos.
 * Autos haben moderate Reparaturkosten basierend auf dem Kilometerstand.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Auto extends Fahrzeug {
    
    /**
     * Konstruktor für ein neues Auto.
     * Ruft den Konstruktor der Basisklasse Fahrzeug auf.
     * 
     * @param marke Die Marke des Autos
     * @param kilometerstand Der aktuelle Kilometerstand
     */
    public Auto(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    /**
     * Berechnet die Reparaturkosten für ein Auto.
     * Autos haben moderate Kosten: 0.2€ pro Kilometer plus 200€ Grundkosten.
     * 
     * @return Die berechneten Reparaturkosten in Euro
     */
    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.2 + 200;
    }
}
