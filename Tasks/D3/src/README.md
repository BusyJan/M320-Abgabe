# ☀️ Weather Forecast App – Kompetenznachweis D3

## 🧭 Beschreibung
Diese Java-Applikation ruft aktuelle Wetterdaten über die **Open-Meteo-API** ab.  
Der Benutzer gibt eine Stadt ein, und das Programm zeigt die aktuelle Temperatur und Windgeschwindigkeit an.  
Die Anwendung demonstriert **Delegation**, **Benutzer-Input-Validierung**, **Exception-Handling** und eine klare **Schichtentrennung**.

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

Enter city name: Zürich
Aktuelles Wetter in Zürich:
Temperatur: 5.2°C
Wind: 4.8 km/h

yaml
Copy code

Wenn der Benutzer keine Eingabe macht:

Enter city name:
Error: Stadt darf nicht leer sein.

yaml
Copy code

---

## 🧠 Technische Umsetzung

- **Programmiersprache:** Java (Version 17 oder höher)
- **API:** [Open-Meteo Weather API](https://open-meteo.com)
- **Netzwerkzugriff:** `java.net.URL` und `Scanner`
- **Datenverarbeitung:** String-Manipulation (kein externes JSON-Framework)
- **Exception Handling:** eigene Klasse `InvalidInputException`

---

## 🪞 Reflexion

Ich habe gelernt, wie man eine Applikation in logische Schichten trennt und mit Delegation arbeitet.  
Ich verstehe, wie die Trennung zwischen Benutzer-Eingabe, Logik und Service-Aufrufen den Code übersichtlicher macht.  
Herausfordernd war das Parsen der API-Antwort, da JSON ohne Library etwas umständlich ist.  
Beim nächsten Mal würde ich eine JSON-Library wie `org.json` oder `GSON` einbinden, um den Code sauberer zu gestalten.

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

© 2025 – Denis  
**Kompetenznachweis D3 – Softwareentwicklung mit Delegation und API-Axnbindung**