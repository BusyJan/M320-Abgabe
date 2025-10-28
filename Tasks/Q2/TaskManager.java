import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Verwaltet eine Sammlung von Aufgaben (Tasks).
 * Diese Klasse bietet Methoden zum Hinzufügen, Entfernen, Suchen und
 * Filtern von Aufgaben. Sie implementiert eine einfache In-Memory-Verwaltung.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 * @see Task
 */
public class TaskManager {
    
    /** Die Liste aller verwalteten Aufgaben */
    private final List<Task> aufgaben;

    /**
     * Erstellt einen neuen TaskManager mit einer leeren Aufgabenliste.
     */
    public TaskManager() {
        this.aufgaben = new ArrayList<>();
    }

    /**
     * Fügt eine neue Aufgabe zur Liste hinzu.
     * 
     * @param task Die hinzuzufügende Aufgabe
     * @throws IllegalArgumentException wenn task null ist
     */
    public void aufgabeHinzufuegen(Task task) {
        if (task == null) {
            throw new IllegalArgumentException("Aufgabe darf nicht null sein");
        }
        aufgaben.add(task);
    }

    /**
     * Erstellt eine neue Aufgabe mit den angegebenen Parametern und fügt sie hinzu.
     * 
     * @param titel Der Titel der neuen Aufgabe
     * @param beschreibung Die Beschreibung der neuen Aufgabe
     * @return Die erstellte und hinzugefügte Aufgabe
     * @throws IllegalArgumentException wenn titel null oder leer ist
     */
    public Task neueAufgabeErstellen(String titel, String beschreibung) {
        Task task = new Task(titel, beschreibung);
        aufgaben.add(task);
        return task;
    }

    /**
     * Entfernt eine Aufgabe aus der Liste.
     * 
     * @param task Die zu entfernende Aufgabe
     * @return true wenn die Aufgabe gefunden und entfernt wurde, false sonst
     */
    public boolean aufgabeEntfernen(Task task) {
        return aufgaben.remove(task);
    }

    /**
     * Gibt die Anzahl der verwalteten Aufgaben zurück.
     * 
     * @return Die Anzahl der Aufgaben
     */
    public int getAnzahlAufgaben() {
        return aufgaben.size();
    }

    /**
     * Gibt alle Aufgaben zurück.
     * 
     * @return Eine neue Liste mit allen Aufgaben (kopiert, nicht die originale Liste)
     */
    public List<Task> alleAufgaben() {
        return new ArrayList<>(aufgaben);
    }

    /**
     * Gibt alle noch nicht erledigten Aufgaben zurück.
     * 
     * @return Eine Liste mit allen offenen Aufgaben
     */
    public List<Task> offeneAufgaben() {
        return aufgaben.stream()
                .filter(task -> !task.istErledigt())
                .collect(Collectors.toList());
    }

    /**
     * Gibt alle erledigten Aufgaben zurück.
     * 
     * @return Eine Liste mit allen erledigten Aufgaben
     */
    public List<Task> erledigteAufgaben() {
        return aufgaben.stream()
                .filter(Task::istErledigt)
                .collect(Collectors.toList());
    }

    /**
     * Findet alle Aufgaben mit einer bestimmten Priorität.
     * 
     * @param prioritaet Die gesuchte Priorität (1-3: niedrig, mittel, hoch)
     * @return Eine Liste mit allen Aufgaben der angegebenen Priorität
     * @throws IllegalArgumentException wenn prioritaet nicht im gültigen Bereich liegt
     */
    public List<Task> aufgabenMitPrioritaet(int prioritaet) {
        if (prioritaet < 1 || prioritaet > 3) {
            throw new IllegalArgumentException("Priorität muss zwischen 1 und 3 liegen");
        }
        return aufgaben.stream()
                .filter(task -> task.getPrioritaet() == prioritaet)
                .collect(Collectors.toList());
    }

    /**
     * Markiert alle Aufgaben als erledigt.
     * Diese Methode iteriert über alle Aufgaben und markiert sie als erledigt.
     */
    public void alleErledigen() {
        aufgaben.forEach(Task::alsErledigtMarkieren);
    }

    /**
     * Gibt eine Übersicht aller Aufgaben in der Konsole aus.
     * Die Ausgabe enthält alle Aufgaben mit ihrem Status und ihrer Priorität.
     */
    public void uebersichtAusgeben() {
        if (aufgaben.isEmpty()) {
            System.out.println("Keine Aufgaben vorhanden.");
            return;
        }
        
        System.out.println("=== Aufgabenübersicht ===");
        System.out.println("Gesamt: " + getAnzahlAufgaben());
        System.out.println("Offen: " + offeneAufgaben().size());
        System.out.println("Erledigt: " + erledigteAufgaben().size());
        System.out.println("\nAufgaben:");
        
        for (int i = 0; i < aufgaben.size(); i++) {
            System.out.println((i + 1) + ". " + aufgaben.get(i));
        }
    }

    /**
     * Entfernt alle erledigten Aufgaben aus der Liste.
     * 
     * @return Die Anzahl der entfernten Aufgaben
     */
    public int erledigteAufgabenEntfernen() {
        int anzahlVorher = aufgaben.size();
        aufgaben.removeIf(Task::istErledigt);
        return anzahlVorher - aufgaben.size();
    }
}

