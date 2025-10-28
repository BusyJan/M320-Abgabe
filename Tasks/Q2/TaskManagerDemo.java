/**
 * Demonstration der TaskManager-Funktionalität.
 * Diese Klasse zeigt die Verwendung von TaskManager und Task
 * mit verschiedenen Beispielen.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 * @see TaskManager
 * @see Task
 */
public class TaskManagerDemo {
    
    /**
     * Hauptmethode zur Ausführung der Demonstration.
     * Erstellt Aufgaben, führt Operationen durch und gibt die Ergebnisse aus.
     * 
     * @param args Kommandozeilenargumente (werden in dieser Demo nicht verwendet)
     */
    public static void main(String[] args) {
        TaskManager manager = new TaskManager();
        
        // Aufgaben erstellen
        Task aufgabe1 = manager.neueAufgabeErstellen("JavaDoc dokumentieren", 
            "Code mit JavaDoc-Kommentaren versehen");
        Task aufgabe2 = manager.neueAufgabeErstellen("Code testen", 
            "Unit-Tests schreiben und ausführen");
        Task aufgabe3 = new Task("Präsentation vorbereiten", 
            "Folien für die Präsentation erstellen", 3); // hohe Priorität
        
        manager.aufgabeHinzufuegen(aufgabe3);
        aufgabe1.setPrioritaet(3); // hohe Priorität setzen
        
        // Aufgabenübersicht ausgeben
        System.out.println("=== Nach Erstellung ===");
        manager.uebersichtAusgeben();
        
        // Eine Aufgabe als erledigt markieren
        aufgabe1.alsErledigtMarkieren();
        
        System.out.println("\n=== Nach Erledigung einer Aufgabe ===");
        manager.uebersichtAusgeben();
        
        // Aufgaben mit hoher Priorität anzeigen
        System.out.println("\n=== Aufgaben mit hoher Priorität ===");
        manager.aufgabenMitPrioritaet(3).forEach(System.out::println);
        
        // Erledigte Aufgaben entfernen
        int entfernt = manager.erledigteAufgabenEntfernen();
        System.out.println("\nEntfernte erledigte Aufgaben: " + entfernt);
        
        System.out.println("\n=== Finale Übersicht ===");
        manager.uebersichtAusgeben();
    }
}

