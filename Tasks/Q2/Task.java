/**
 * Repräsentiert eine einzelne Aufgabe mit Titel, Beschreibung und Status.
 * Diese Klasse verwaltet den Zustand einer Aufgabe und bietet Methoden
 * zur Statusänderung und Validierung.
 * 
 * @author TBZ Student
 * @version 1.0
 * @since 2024
 */
public class Task {
    
    /** Der Titel der Aufgabe */
    private String titel;
    
    /** Die Beschreibung der Aufgabe */
    private String beschreibung;
    
    /** Der aktuelle Status der Aufgabe (true = erledigt, false = offen) */
    private boolean erledigt;
    
    /** Die Priorität der Aufgabe (1 = niedrig, 2 = mittel, 3 = hoch) */
    private int prioritaet;

    /**
     * Konstruktor für eine neue Aufgabe.
     * Erstellt eine Aufgabe mit Standardpriorität (mittlere Priorität).
     * 
     * @param titel Der Titel der Aufgabe (muss nicht null sein)
     * @param beschreibung Die Beschreibung der Aufgabe
     * @throws IllegalArgumentException wenn titel null oder leer ist
     */
    public Task(String titel, String beschreibung) {
        this(titel, beschreibung, 2); // Standard-Priorität: mittel
    }

    /**
     * Konstruktor für eine neue Aufgabe mit spezifischer Priorität.
     * 
     * @param titel Der Titel der Aufgabe
     * @param beschreibung Die Beschreibung der Aufgabe
     * @param prioritaet Die Priorität (1-3: niedrig, mittel, hoch)
     * @throws IllegalArgumentException wenn titel null/leer ist oder 
     *         prioritaet nicht im gültigen Bereich liegt
     */
    public Task(String titel, String beschreibung, int prioritaet) {
        if (titel == null || titel.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel darf nicht leer sein");
        }
        if (prioritaet < 1 || prioritaet > 3) {
            throw new IllegalArgumentException("Priorität muss zwischen 1 und 3 liegen");
        }
        this.titel = titel.trim();
        this.beschreibung = beschreibung != null ? beschreibung : "";
        this.prioritaet = prioritaet;
        this.erledigt = false;
    }

    /**
     * Gibt den Titel der Aufgabe zurück.
     * 
     * @return Der Titel der Aufgabe
     */
    public String getTitel() {
        return titel;
    }

    /**
     * Setzt einen neuen Titel für die Aufgabe.
     * 
     * @param titel Der neue Titel
     * @throws IllegalArgumentException wenn titel null oder leer ist
     */
    public void setTitel(String titel) {
        if (titel == null || titel.trim().isEmpty()) {
            throw new IllegalArgumentException("Titel darf nicht leer sein");
        }
        this.titel = titel.trim();
    }

    /**
     * Gibt die Beschreibung der Aufgabe zurück.
     * 
     * @return Die Beschreibung der Aufgabe
     */
    public String getBeschreibung() {
        return beschreibung;
    }

    /**
     * Setzt eine neue Beschreibung für die Aufgabe.
     * 
     * @param beschreibung Die neue Beschreibung (null wird als leerer String behandelt)
     */
    public void setBeschreibung(String beschreibung) {
        this.beschreibung = beschreibung != null ? beschreibung : "";
    }

    /**
     * Prüft, ob die Aufgabe erledigt ist.
     * 
     * @return true wenn die Aufgabe erledigt ist, false sonst
     */
    public boolean istErledigt() {
        return erledigt;
    }

    /**
     * Markiert die Aufgabe als erledigt.
     * Diese Methode kann mehrfach aufgerufen werden ohne Nebeneffekte.
     */
    public void alsErledigtMarkieren() {
        this.erledigt = true;
    }

    /**
     * Markiert die Aufgabe als offen (nicht erledigt).
     * Diese Methode kann mehrfach aufgerufen werden ohne Nebeneffekte.
     */
    public void alsOffenMarkieren() {
        this.erledigt = false;
    }

    /**
     * Gibt die Priorität der Aufgabe zurück.
     * 
     * @return Die Priorität (1 = niedrig, 2 = mittel, 3 = hoch)
     */
    public int getPrioritaet() {
        return prioritaet;
    }

    /**
     * Setzt eine neue Priorität für die Aufgabe.
     * 
     * @param prioritaet Die neue Priorität (1-3: niedrig, mittel, hoch)
     * @throws IllegalArgumentException wenn prioritaet nicht im gültigen Bereich liegt
     */
    public void setPrioritaet(int prioritaet) {
        if (prioritaet < 1 || prioritaet > 3) {
            throw new IllegalArgumentException("Priorität muss zwischen 1 und 3 liegen");
        }
        this.prioritaet = prioritaet;
    }

    /**
     * Gibt die Priorität als lesbaren String zurück.
     * 
     * @return "niedrig", "mittel" oder "hoch" basierend auf der Priorität
     */
    public String getPrioritaetAlsString() {
        return switch (prioritaet) {
            case 1 -> "niedrig";
            case 2 -> "mittel";
            case 3 -> "hoch";
            default -> "unbekannt";
        };
    }

    /**
     * Gibt eine String-Repräsentation der Aufgabe zurück.
     * Enthält Titel, Status und Priorität in einem lesbaren Format.
     * 
     * @return Eine formatierte String-Darstellung der Aufgabe
     */
    @Override
    public String toString() {
        String status = erledigt ? "[✓]" : "[ ]";
        return String.format("%s %s (Priorität: %s)", status, titel, getPrioritaetAlsString());
    }
}

