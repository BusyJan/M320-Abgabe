# Q3: JavaDoc Dokumentation

## Aufgabe
Kommentieren Sie Ihren eigenen Level 2 Code und generieren Sie JavaDoc. Zeigen Sie die JavaDoc Ihrem Lehrer.

## Fragen
1. **Was kommentiere ich?**
   - Klassen: Zweck, Vererbungsbeziehungen, Verwendung
   - Öffentliche Methoden: Parameter, Rückgabewerte, Ausnahmen, Verhalten
   - Konstruktoren: Parametervalidierung, Initialisierungsdetails
   - Komplexe Algorithmen: Geschäftslogik, Berechnungsformeln
   - Überschriebene Methoden: Unterschiede zur Elternklasse

2. **Was kommentiere ich NICHT?**
   - Offensichtlichen Code (Getter/Setter ohne Logik)
   - Selbst erklärende Variablennamen
   - Code der bereits klar ist
   - Implementierungsdetails in öffentlichen API-Docs

3. **Wie generiere ich JavaDoc in meiner IDE?**

### IntelliJ IDEA:
1. `Tools` → `Generate JavaDoc...`
2. Ausgabeverzeichnis festlegen (z.B. `docs` oder `javadoc`)
3. Scope konfigurieren (Projekt, Package oder spezifische Klassen)
4. Optionen konfigurieren:
   - Visibility: Public, Protected, Package, Private
   - Andere Optionen nach Bedarf
5. `OK` klicken
6. Die generierte HTML-Dokumentation öffnen (index.html im Ausgabeverzeichnis)

### Eclipse:
1. Rechtsklick auf Projekt → `Generate Javadoc...`
2. Zielordner auswählen
3. Packages/Klassen auswählen die dokumentiert werden sollen
4. Sichtbarkeit konfigurieren
5. `Finish` klicken

### Kommandozeile:
```bash
# Navigieren Sie zum Q3 Verzeichnis
cd Tasks/Q3

# JavaDoc generieren
javadoc -d javadoc -encoding UTF-8 -charset UTF-8 *.java

# Die Dokumentation öffnen: javadoc/index.html
```

## Inhalt dieses Projekts

Dieses Projekt enthält:
- **Task.java**: Repräsentiert eine einzelne Aufgabe mit Titel, Beschreibung, Status und Priorität
- **TaskManager.java**: Verwaltet eine Sammlung von Aufgaben mit verschiedenen Filtermethoden
- **TaskManagerDemo.java**: Demonstration der Funktionalität

Alle Klassen sind vollständig mit JavaDoc-Kommentaren dokumentiert.

## JavaDoc Beispiel

```java
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
```

## Vorgehen

1. Code kommentieren (bereits erledigt)
2. JavaDoc generieren (siehe oben)
3. Dokumentation zeigen (javadoc/index.html öffnen)

