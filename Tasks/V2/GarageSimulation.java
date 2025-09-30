import java.util.ArrayList;

/**
 * Demonstration der Vererbung und Polymorphismus mit Fahrzeugen.
 * Diese Klasse zeigt, wie verschiedene Fahrzeugtypen (Auto, Motorrad, LKW)
 * als Fahrzeug-Objekte behandelt werden können und dabei ihre spezifischen
 * Methoden-Implementierungen verwenden.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class GarageSimulation {
    
    /**
     * Hauptmethode der Anwendung.
     * Erstellt verschiedene Fahrzeugtypen, führt Reparaturen durch
     * und berechnet die Kosten. Demonstriert Polymorphismus durch
     * die Verwendung der gemeinsamen Fahrzeug-Basisklasse.
     * 
     * @param args Kommandozeilenargumente (werden nicht verwendet)
     */
    public static void main(String[] args) {
        // Liste für verschiedene Fahrzeugtypen - Polymorphismus in Aktion
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();

        // Verschiedene Fahrzeugtypen zur Liste hinzufügen
        fahrzeuge.add(new Auto("BMW", 120000));
        fahrzeuge.add(new Motorrad("Yamaha", 30000));
        fahrzeuge.add(new LKW("Mercedes", 200000));

        // Polymorphismus: Alle Fahrzeuge werden gleich behandelt,
        // aber jede Unterklasse verwendet ihre eigene Kostenberechnung
        for (Fahrzeug f : fahrzeuge) {
            System.out.println("Fahrzeug: " + f.getMarke());
            f.reparieren();                    // Allgemeine Reparatur
            f.reparieren("Bremsen");           // Spezifische Reparatur
            System.out.println("Kosten: " + f.berechneReparaturKosten());
            System.out.println();
        }
    }
}
