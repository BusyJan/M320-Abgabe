# D3 - Rezept-Verwaltung mit Delegation

## Projektbeschreibung

Eine Rezept-Verwaltungs-Applikation, die das Konzept der **Delegation** demonstriert. Die Anwendung ermöglicht es Benutzern, Rezepte zu suchen, zu filtern und detaillierte Informationen anzuzeigen. Der Code ist sauber in verschiedene Verantwortlichkeiten getrennt.

## Lernziele (Niveau 3)

✅ **Delegation anwenden** - Klare Trennung von Verantwortlichkeiten  
✅ **Exception Handling** - Eigene Exception-Klassen für Validierung  
✅ **Code-Trennung** - User-Input, Logik und Service-Aufrufe sind separiert  
✅ **Objekt-Kommunikation** - Objekte kommunizieren über definierte Schnittstellen  

## Architektur & Delegation

### Übersicht der Delegation

```
Main.java (Einstiegspunkt)
    ↓ delegiert an
RecipeController.java (Vermittler)
    ↓ delegiert Input-Validierung an
    InputValidator.java (Validierungslogik)
    ↓ delegiert Business-Logik an
    RecipeService.java (Datenverarbeitung & Logik)
```

### Klassenstruktur

#### 1. **Main.java** - Einstiegspunkt
**Zeilen 6-9:** Einzige Aufgabe ist das Starten der Applikation
```java
public static void main(String[] args) {
    RecipeController controller = new RecipeController();
    controller.start(); // DELEGATION an Controller
}
```

#### 2. **controller/RecipeController.java** - Vermittler
**Verantwortlichkeiten:**
- User-Interface (Menü anzeigen, Eingaben holen)
- **Delegiert Validierung** an `InputValidator` (Zeilen 46-49, 115-118, etc.)
- **Delegiert Logik** an `RecipeService` (Zeilen 121, 135, 149, etc.)
- Exception Handling (Zeilen 89-95)

**Beispiel Delegation (Zeilen 111-126):**
```java
private void handleSearchByName() throws InvalidInputException {
    String searchTerm = getUserInput("\nRezeptname eingeben: ");
    
    // DELEGATION: Validierung an InputValidator
    String validatedTerm = InputValidator.validateSearchTerm(searchTerm);
    
    // DELEGATION: Suche an RecipeService
    List<Recipe> results = recipeService.searchByName(validatedTerm);
    
    // Ausgabe
    displaySearchResults(results, "Suche nach: " + validatedTerm);
}
```

#### 3. **util/InputValidator.java** - Validierung
**Zeilen 12-90:** Verschiedene Validierungsmethoden
- `validateMenuChoice()` - Zeilen 19-39
- `validateSearchTerm()` - Zeilen 48-70
- `validateRecipeId()` - Zeilen 78-92
- `validateCategory()` - Zeilen 100-120

**Wirft eigene Exceptions** bei ungültigen Eingaben (Zeilen 26, 33, 36)

#### 4. **service/RecipeService.java** - Business-Logik
**Verantwortlichkeiten:**
- Datenverwaltung (simuliert API-Calls)
- Such- und Filterlogik (Zeilen 83-97, 105-114, 122-130)
- Datenverarbeitung mit Streams

**Methoden:**
- `searchByName()` - Zeile 83
- `searchByCategory()` - Zeile 97
- `getRecipeById()` - Zeile 122
- `filterByMaxTime()` - Zeile 141
- `getAllRecipes()` - Zeile 135

#### 5. **model/Recipe.java** - Datenmodell
**Zeilen 8-28:** Rezept-Klasse mit allen Eigenschaften
**Zeilen 66-87:** Formatierte Ausgabe der Rezept-Details

#### 6. **exception/** - Eigene Exceptions
- **InvalidInputException.java** (Zeilen 8-26)
- **RecipeNotFoundException.java** (Zeilen 8-14)

## Trennung der Verantwortlichkeiten

### 1. User-Input (Controller)
```
RecipeController.getUserInput() - Zeile 56
RecipeController.getUserChoice() - Zeile 46
```
**Keine Logik**, nur Eingabe holen!

### 2. Validierung (InputValidator)
```
InputValidator.validateSearchTerm() - Zeile 48
InputValidator.validateMenuChoice() - Zeile 19
InputValidator.validateRecipeId() - Zeile 78
```
**Nur Validierung**, wirft Exceptions bei Fehlern!

### 3. Business-Logik (RecipeService)
```
RecipeService.searchByName() - Zeile 83
RecipeService.filterByMaxTime() - Zeile 141
RecipeService.getRecipeById() - Zeile 122
```
**Nur Logik und Datenverarbeitung**, keine UI!

## Objekt-Kommunikation

### Werte werden mitgegeben:

1. **Controller → InputValidator** (Zeile 115)
   ```java
   String validatedTerm = InputValidator.validateSearchTerm(searchTerm);
   // Gibt: String zurück (validiert)
   ```

2. **Controller → RecipeService** (Zeile 121)
   ```java
   List<Recipe> results = recipeService.searchByName(validatedTerm);
   // Gibt: List<Recipe> zurück
   ```

3. **Service → Controller → User** (Zeile 124)
   ```java
   displaySearchResults(results, "Suche nach: " + validatedTerm);
   // Empfängt: List<Recipe>, String
   ```

### Kommunikationsfluss Beispiel (Suche):

```
User gibt "Pasta" ein
    ↓
Controller.handleSearchByName() empfängt Input
    ↓ gibt "Pasta" weiter
InputValidator.validateSearchTerm("Pasta")
    ↓ gibt validierten String zurück
Controller empfängt "Pasta" (validiert)
    ↓ gibt "Pasta" weiter
RecipeService.searchByName("Pasta")
    ↓ gibt List<Recipe> zurück
Controller empfängt Ergebnisse
    ↓ gibt Ergebnisse weiter
displaySearchResults(results) zeigt Daten an
```

## Exception Handling

### Eigene Exception-Klassen:

1. **InvalidInputException** (`exception/InvalidInputException.java`)
   - Für ungültige Benutzereingaben
   - Wird geworfen in: `InputValidator` (Zeilen 26, 33, 36, 54, 58, 62, 88, 116)
   - Wird gefangen in: `RecipeController` (Zeilen 35, 91)

2. **RecipeNotFoundException** (`exception/RecipeNotFoundException.java`)
   - Wenn Rezept nicht gefunden
   - Wird geworfen in: `RecipeService.getRecipeById()` (Zeile 129)
   - Wird gefangen in: `RecipeController` (Zeile 93)

### Beispiel Exception Flow:

```
User gibt "xyz" als Rezept-ID ein
    ↓
InputValidator.validateRecipeId("xyz")
    ↓ wirft InvalidInputException (Zeile 88)
Controller fängt Exception (Zeile 91)
    ↓
displayError() zeigt Fehlermeldung
```

## Simulation externer API

**`RecipeService.initializeMockData()`** (Zeilen 30-78)
- Simuliert API-Call
- In echter Anwendung würde hier eine Recipe-API (z.B. Spoonacular) aufgerufen

## Kompilieren und Ausführen

```bash
# Aus dem D3_Recipe Verzeichnis
cd Tasks/D3_Recipe

# Kompilieren (mit Packages)
javac -d bin src/**/*.java src/*.java

# Ausführen
java -cp bin Main
```

Oder einfacher:
```bash
cd src
javac **/*.java *.java
java Main
```

## Beispiel-Ausgabe

```
╔════════════════════════════════════════════════════════════╗
║            WILLKOMMEN ZUR REZEPT-VERWALTUNG                ║
╚════════════════════════════════════════════════════════════╝

╔════════════════════════════════════════════════════════════╗
║                        HAUPTMENÜ                           ║
╠════════════════════════════════════════════════════════════╣
║  [1] Rezept nach Namen suchen                             ║
║  [2] Rezepte nach Kategorie suchen                        ║
...
```

## Fragen zur Besprechung - Antworten

### 1. Zeigen Sie die Delegation

**Hauptdelegation:**
- `Main` → `RecipeController` (Zeile 7 in Main.java)
- `RecipeController` → `InputValidator` (z.B. Zeile 115)
- `RecipeController` → `RecipeService` (z.B. Zeile 121)

**Beispiel:** `handleSearchByName()` (Zeilen 111-126):
- Holt Input vom User (keine Logik)
- Delegiert Validierung an `InputValidator`
- Delegiert Suche an `RecipeService`
- Zeigt nur Ergebnisse an

### 2. Wie kommunizieren Objekte? Werden Werte mitgegeben?

**Ja, Werte werden über Methoden-Parameter übergeben:**

```java
// Controller gibt String an Validator
String validatedTerm = InputValidator.validateSearchTerm(searchTerm);

// Controller gibt validierten String an Service
List<Recipe> results = recipeService.searchByName(validatedTerm);

// Service gibt Recipe-Liste zurück an Controller
```

**Objekte kommunizieren über:**
- Methodenaufrufe mit Parametern
- Rückgabewerte
- Exceptions (für Fehler)

### 3. Trennung von User-Input, Logik, API-Aufrufe

**User-Input:** `RecipeController` (Zeilen 46-60)
- Nur Scanner.nextLine()
- Keine Validierung oder Logik

**Validierung:** `InputValidator` (separate Klasse)
- Prüft Eingaben
- Wirft Exceptions

**Logik:** `RecipeService` (separate Klasse)
- Suche, Filter, Datenverarbeitung
- Keine UI-Interaktion

**"API"-Aufrufe:** `RecipeService.initializeMockData()` (Zeilen 30-78)
- Würde in echter App externe API aufrufen
- Komplett gekapselt im Service

## Erweiterungsmöglichkeiten

- Echte REST-API Integration (z.B. Spoonacular API)
- Datenbank-Anbindung statt Mock-Daten
- Rezepte hinzufügen/bearbeiten/löschen
- Favoriten-Verwaltung
- Einkaufslisten-Generator

## Autor

Jan Ludwig - Modul 320, D3 - Delegation und Exception Handling

