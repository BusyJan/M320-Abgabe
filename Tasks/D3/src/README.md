# ☀️ Weather Forecast App – Kompetenznachweis D3

## 🧭 Beschreibung
Diese Java-Applikation ruft **Echtzeit-Wetterdaten** über die **Open-Meteo-API** ab.  
Der Benutzer gibt eine **beliebige Stadt** ein, und das Programm zeigt:
- Aktuelle Temperatur
- Windgeschwindigkeit  
- Wetterbeschreibung (z.B. "Klarer Himmel", "Regen", "Gewitter")

Die Anwendung demonstriert **Delegation**, **Benutzer-Input-Validierung**, **Exception-Handling**, **externe API-Integration** und eine klare **Schichtentrennung**.

**NEU:** Die App verwendet jetzt 2 APIs in Echtzeit:
1. **Geocoding API** - Wandelt Stadtnamen in Koordinaten um
2. **Weather API** - Ruft Wetterdaten für die Koordinaten ab

---

## 🎯 Lernziele

✅ Ich kann eine eigene Beschreibung für eine Applikation notieren  
✅ Ich kann Delegation in meinem Beispiel anwenden  
✅ Ich kann Benutzer-Eingaben validieren und bei falschem Input eine eigene Exception werfen  
✅ Ich kann eine saubere Trennung anwenden im Code von User-Input, Logik, Service-Aufrufen

---

## 🧩 Architektur

Die App besteht aus klar getrennten Klassen mit unterschiedlichen Verantwortlichkeiten:

Main → WeatherController → WeatherService → WeatherData
↑
InputValidator & InvalidInputException

markdown
Copy code

| Schicht | Aufgabe |
|----------|----------|
| **Main** | Startpunkt der Anwendung – delegiert an Controller |
| **Controller** | Nimmt Benutzereingaben entgegen, validiert sie |
| **Validator** | Prüft die Eingabe, wirft bei Fehlern eine Exception |
| **Service** | Beinhaltet Logik, ruft Wetter-API auf |
| **Model** | Hält Wetterdaten (Temperatur, Wind etc.) |
| **Exception** | Benutzerdefinierte Exception bei ungültiger Eingabe |

---

## 🔄 Delegation

- `Main` startet nur die App und übergibt die Kontrolle an `WeatherController`.
- Der `Controller` verarbeitet den User-Input und ruft den `WeatherService` auf.
- Der `Service` kommuniziert mit der API, erstellt ein `WeatherData`-Objekt und liefert es zurück.
- Das Ergebnis wird über den Controller an den Benutzer ausgegeben.

**Kommunikationsfluss:**

Main → Controller → Service → Model

yaml
Copy code

---

## ⚠️ Benutzer-Input & Exception-Handling

- Der Benutzer gibt den Stadtnamen über die Konsole ein.
- `InputValidator` überprüft, ob der Input leer ist oder ungültige Zeichen enthält.
- Bei falscher Eingabe wird eine eigene `InvalidInputException` geworfen.
- Diese Exception wird im Controller abgefangen und als Fehlermeldung angezeigt.

---

## 💻 Beispielausgabe

**Erfolgreiche Abfrage:**
```
Enter city name: London

╔════════════════════════════════════════════════════════════╗
║                    WETTER-DATEN                            ║
╠════════════════════════════════════════════════════════════╣
║  Stadt:         London                                     ║
║  Temperatur:    8.5°C                                      ║
║  Wind:          12.3 km/h                                  ║
║  Beschreibung:  Teilweise bewölkt                          ║
╚════════════════════════════════════════════════════════════╝
```

**Ungültige Eingabe:**
```
Enter city name:
Error: Stadt darf nicht leer sein.
```

**Stadt nicht gefunden:**
```
Enter city name: asdfgh

>>> FEHLER: Wetterdaten konnten nicht abgerufen werden.
>>> Details: Stadt 'asdfgh' nicht gefunden! <<<
```

---

## 🧠 Technische Umsetzung

- **Programmiersprache:** Java (Version 17 oder höher)
- **APIs:** 
  - [Open-Meteo Geocoding API](https://open-meteo.com/en/docs/geocoding-api) - Stadt → Koordinaten
  - [Open-Meteo Weather API](https://open-meteo.com) - Koordinaten → Wetterdaten
- **Netzwerkzugriff:** `java.net.URL` und `Scanner` (Zeilen 166-177 in WeatherService.java)
- **Datenverarbeitung:** String-Manipulation und JSON-Parsing ohne externe Libraries
- **Exception Handling:** eigene Klasse `InvalidInputException`
- **WMO Weather Codes:** Konvertierung in deutsche Beschreibungen (Zeilen 186-214)

### API-Ablauf:
```
User gibt "Berlin" ein
    ↓
1. Geocoding API: "Berlin" → Lat: 52.52, Lon: 13.41
    ↓
2. Weather API: Lat/Lon → Temperatur, Wind, Wettercode
    ↓
3. Wettercode → "Klarer Himmel" (deutsche Beschreibung)
    ↓
Ausgabe an User
```

---

## 🪞 Reflexion

Ich habe gelernt, wie man eine Applikation in logische Schichten trennt und mit Delegation arbeitet.  
Ich verstehe, wie die Trennung zwischen Benutzer-Eingabe, Logik und Service-Aufrufen den Code übersichtlicher macht.

**Neu hinzugefügt:**
- **Dynamische Stadt-Suche** - Jede beliebige Stadt weltweit kann abgefragt werden
- **Geocoding Integration** - Stadt wird automatisch in Koordinaten umgewandelt
- **WMO Weather Codes** - 20+ verschiedene Wetterbeschreibungen auf Deutsch
- **Verbesserte Fehlerbehandlung** - Stadt nicht gefunden vs. Netzwerkfehler

**Herausforderungen:**
- Das Parsen von JSON ohne Library ist umständlich, funktioniert aber mit String-Splitting
- Fehlerbehandlung bei mehreren API-Aufrufen erfordert präzises Exception Handling
- URL-Encoding für Städtenamen mit Sonderzeichen (z.B. "São Paulo")

**Verbesserungspotenzial:**
- JSON-Library wie `org.json` oder `GSON` würde den Code robuster machen
- Caching der Geocoding-Ergebnisse für häufig gesuchte Städte
- Mehr Wetterdaten (Luftfeuchtigkeit, Luftdruck, Niederschlag)

---

## 🧾 Bewertungshinweise

| Kriterium | Beschreibung | Erfüllt |
|------------|---------------|----------|
| **Delegation sichtbar** | Controller → Service → Model | ✅ |
| **Saubere Trennung** | Keine Logik in `main()`, klare Verantwortung | ✅ |
| **Validierung & Exception** | Eigene Exception bei leerem Input | ✅ |
| **Fremde API** | Open-Meteo-Schnittstelle integriert | ✅ |
| **Dokumentation vorhanden** | README mit Lernzielen und Reflexion | ✅ |

---

## 🔧 Code-Referenzen

### Delegation
- **Main → Controller:** `Main.java` Zeile 7
- **Controller → Service:** `WeatherController.java` (ruft `weatherService.showWeather()` auf)
- **Service → API:** `WeatherService.java` Zeilen 72-105 (Geocoding), Zeilen 117-157 (Weather)

### API-Integration
- **Geocoding API-Aufruf:** `WeatherService.java` Zeilen 72-105
- **Weather API-Aufruf:** `WeatherService.java` Zeilen 117-157
- **HTTP Request Methode:** `WeatherService.java` Zeilen 166-177
- **JSON Parsing:** `WeatherService.java` Zeilen 90-104, 133-156

### Exception Handling
- **InvalidInputException Definition:** `exception/InvalidInputException.java`
- **Stadt nicht gefunden:** `WeatherService.java` Zeile 57
- **Parsing-Fehler:** `WeatherService.java` Zeilen 102-104, 154-156

### Validierung
- **Input-Validierung:** `util/InputValidator.java`
- **Exception wird geworfen:** `InputValidator.java` (bei leerem/ungültigem Input)
- **Exception wird gefangen:** `controller/WeatherController.java` (in try-catch Block)

---

© 2025 – Jan Ludwig  
**Kompetenznachweis D3 – Softwareentwicklung mit Delegation und Echtzeit-API-Anbindung**