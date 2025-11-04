# V3 - Interfaces anwenden

## Beschreibung
Dieses Projekt demonstriert die Verwendung von Interfaces zur Gestaltung flexiblen Codes und zeigt Polymorphismus in der Praxis.

## Konzept
Ein Portfolio verwaltet verschiedene Aktien und kann deren Wert berechnen. Dabei werden die Aktienkurse von verschiedenen Börsen abgerufen. Das Portfolio kennt nur das `StockExchange`-Interface und nicht die konkreten Börsen-Implementierungen - dies ist **Polymorphismus** in Aktion!

## Klassenstruktur

### Interface
- **StockExchange**: Interface mit Methoden zum Abrufen von Aktienkursen
  - `getPrice(String stockName)`: Gibt den Preis einer Aktie zurück
  - `getExchangeName()`: Gibt den Namen der Börse zurück
  - `getCurrency()`: Gibt die Währung zurück

### Implementierungen
- **ZurichStockExchange**: Börse Zürich mit Preisen in CHF
- **NewYorkStockExchange**: Börse New York mit Preisen in USD
- **LondonStockExchange**: Börse London mit Preisen in GBP

### Datenklassen
- **Stock**: Repräsentiert eine Aktie mit Name und Anzahl
- **Portfolio**: Verwaltet eine Sammlung von Aktien und berechnet deren Wert
  - `addStock()`: Fügt eine Aktie hinzu
  - `calculateValue(StockExchange exchange)`: Berechnet den Gesamtwert
  - `printValuation(StockExchange exchange)`: Gibt eine detaillierte Bewertung aus

### Hauptprogramm
- **PortfolioApp**: Interaktive Anwendung mit Menü

## Polymorphismus-Demonstration

Der Kern dieses Projekts ist die Demonstration von Polymorphismus:

```java
// Das Portfolio kennt nur das Interface, nicht die konkreten Implementierungen
StockExchange exchange1 = new ZurichStockExchange();
StockExchange exchange2 = new NewYorkStockExchange();
StockExchange exchange3 = new LondonStockExchange();

// Dieselbe Methode funktioniert mit allen Implementierungen!
portfolio.calculateValue(exchange1);  // Berechnet in CHF
portfolio.calculateValue(exchange2);  // Berechnet in USD
portfolio.calculateValue(exchange3);  // Berechnet in GBP
```

## Vorteile dieser Architektur

1. **Flexibilität**: Neue Börsen können einfach hinzugefügt werden, ohne bestehenden Code zu ändern
2. **Erweiterbarkeit**: Das Interface kann erweitert werden (z.B. um historische Daten)
3. **Testbarkeit**: Mock-Implementierungen können für Tests verwendet werden
4. **Lose Kopplung**: Das Portfolio ist nicht von konkreten Börsen-Implementierungen abhängig

## Kompilieren und Ausführen

```bash
# Alle Dateien kompilieren
javac *.java

# Programm starten
java PortfolioApp
```

## Funktionen

1. **Portfolio-Wert abfragen**: Wähle eine Börse und sehe den aktuellen Wert
2. **Börsen vergleichen**: Vergleiche den Wert über alle drei Börsen
3. **Portfolio-Inhalt**: Zeige alle Aktien im Portfolio an
4. **Aktien hinzufügen**: Füge neue Aktien zum Portfolio hinzu
5. **Polymorphismus-Erklärung**: Detaillierte Erklärung des Konzepts

## Beispiel-Output

```
╔════════════════════════════════════════════════════════════╗
║         PORTFOLIO BEWERTUNG                                ║
╠════════════════════════════════════════════════════════════╣
║  Börse: Zürich (SIX Swiss Exchange)                       ║
╠════════════════════════════════════════════════════════════╣
║  Microsoft            10x  @ 120.00 =    1200.00 CHF  ║
║  Apple                 5x  @ 180.00 =     900.00 CHF  ║
║  Google                8x  @ 140.00 =    1120.00 CHF  ║
╠════════════════════════════════════════════════════════════╣
║  GESAMTWERT:                              3220.00 CHF  ║
╚════════════════════════════════════════════════════════════╝
```

## Lernziele

✅ Interface definieren und implementieren  
✅ Polymorphismus verstehen und anwenden  
✅ Lose Kopplung durch Interfaces erreichen  
✅ Verschiedene Implementierungen austauschbar machen  
✅ Clean Code und gute Architektur demonstrieren

## Autor
Jan Ludwig - Modul 320, V3

