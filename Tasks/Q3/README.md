# Q3 - Using the Map Datastructure

## Beschreibung
Dieses Projekt demonstriert die Verwendung der Map-Datenstruktur (HashMap) zur Organisation und Verwaltung von Flughafen-Daten.

## Konzept
Eine HashMap wird verwendet, um US-Flughäfen zu speichern, wobei der IATA-Code (z.B. "JFK") als Schlüssel und das Airport-Objekt als Wert dient. Die Anwendung ermöglicht Suchen, Hinzufügen, Löschen und Sortieren von Flughäfen.

**HashMap-Deklaration:** `AirportManager.java` Zeile 9  
**HashMap-Initialisierung:** `AirportManager.java` Zeile 16

## Klassenstruktur

### Datenklasse
- **Airport**: Repräsentiert einen Flughafen
  - `code`: IATA-Code (z.B. "JFK")
  - `name`: Name des Flughafens
  - `city`: Stadt
  - `state`: Bundesstaat
  - `country`: Land

### Manager-Klasse
- **AirportManager**: Verwaltet Flughäfen in einer HashMap
  - `addAirport()`: Fügt einen Flughafen hinzu (überschreibt bei existierendem Key)  
    → `AirportManager.java` Zeile 27-31
  - `search()`: Sucht nach Code (speichert in Historie)  
    → `AirportManager.java` Zeile 40-44
  - `remove()`: Entfernt einen Flughafen  
    → `AirportManager.java` Zeile 52-54
  - `getSortedAirports()`: Gibt alphabetisch sortierte Liste zurück  
    → `AirportManager.java` Zeile 61-65
  - `displayAll()`: Zeigt alle Flughäfen (mit Lambda forEach)  
    → `AirportManager.java` Zeile 71-88
  - `displaySearchHistory()`: Zeigt Such-Historie (neueste zuerst)  
    → `AirportManager.java` Zeile 115-136
  - `importFromCSV()`: Importiert Daten aus CSV-Datei  
    → `AirportManager.java` Zeile 145-200

### Hauptprogramm
- **AirportApp**: Interaktive Anwendung mit Menü

## Map-Regeln (aus Aufgabenstellung)

1. **Ein Schlüssel = Ein Wert**: Jeder Key (Airport-Code) hat nur einen Wert (Airport-Objekt)  
   → Implementiert mit `Map<String, Airport>` in `AirportManager.java` Zeile 9

2. **Überschreiben**: Wenn ein Key bereits existiert, wird der alte Wert überschrieben  
   → Implementiert mit `airports.put()` in `AirportManager.java` Zeile 29

## Lambda-Ausdrücke

Das Projekt demonstriert Lambda-Ausdrücke zur Iteration:

```java
// Beispiel 1: forEach mit Lambda (AirportManager.java Zeile 82-84)
airports.forEach((code, airport) -> 
    System.out.printf("║ %-74s ║%n", airport.toString())
);

// Beispiel 2: Sortierung mit Method Reference (AirportManager.java Zeile 63)
sortedList.sort(Comparator.comparing(Airport::getCode));
```

## Funktionen

1. **Flughafen suchen**: Suche nach IATA-Code (wird in Historie gespeichert)  
   → `AirportApp.java` Zeile 49-70

2. **Alle anzeigen**: Zeigt alle Flughäfen mit forEach/Lambda  
   → `AirportApp.java` Zeile 75-77 (ruft `AirportManager.displayAll()` auf)

3. **Sortiert anzeigen**: Alphabetisch sortierte Ausgabe  
   → `AirportApp.java` Zeile 82-84 (ruft `AirportManager.displaySorted()` auf)

4. **Hinzufügen**: Neuen Flughafen hinzufügen  
   → `AirportApp.java` Zeile 89-116

5. **Entfernen**: Flughafen löschen  
   → `AirportApp.java` Zeile 121-139

6. **Such-Historie**: Zeigt alle Suchen (neueste zuerst)  
   → `AirportManager.java` Zeile 42 (add zur Liste), Zeile 115-136 (Anzeige)

7. **Lambda-Demo**: Erklärung der Lambda-Ausdrücke  
   → `AirportApp.java` Zeile 144-166

## Kompilieren und Ausführen

```bash
# Alle Dateien kompilieren
javac *.java

# Programm starten
java AirportApp
```

## CSV-Datei Format

Das Programm unterstützt zwei Formate:

**Format 1 (2 Spalten):**
```csv
AIRPORT CODE,AIRPORT
JFK,New York John F. Kennedy International
LAX,Los Angeles International
```

**Format 2 (5 Spalten):**
```csv
CODE,NAME,CITY,STATE,COUNTRY
JFK,John F. Kennedy International Airport,New York,NY,USA
LAX,Los Angeles International Airport,Los Angeles,CA,USA
```

**CSV-Import Logik:** `AirportManager.java` Zeile 166-186

## Beispiel-Output

```
╔════════════════════════════════════════════════════════════╗
║                  FLUGHAFEN GEFUNDEN                        ║
╠════════════════════════════════════════════════════════════╣
║  Code:    JFK                                              ║
║  Name:    John F. Kennedy International Airport            ║
║  City:    New York                                         ║
║  State:   NY                                               ║
║  Country: USA                                              ║
╚════════════════════════════════════════════════════════════╝
```

## Lernziele

✅ HashMap verwenden und verstehen  
✅ Key-Value-Paare korrekt verwalten  
✅ Lambda-Ausdrücke für Iteration nutzen  
✅ Daten aus CSV-Datei importieren  
✅ Such-Historie implementieren (neueste zuerst)  
✅ Alphabetische Sortierung mit Comparator  

## Autor
Jan Ludwig - Modul 320, Q3

