# ✅ JavaDoc - Minimale Einstellungen (nur das Nötigste)

## 🔴 WICHTIG: Diese Einstellung ist FALSCH!

**JavaDoc Scope:** 
- ❌ Aktuell: "File '...README.md'" 
- ✅ Ändere zu: **"Whole project"** (oder Custom Scope: Q2 Ordner)

---

## ✅ Korrekte Einstellungen für "nur das Nötigste":

### 1. JavaDoc Scope:
- [ ] **"Whole project"** auswählen
- Oder: **"Custom scope: Q2"** (wenn du nur Q2 willst)

### 2. JavaDoc Options:
- [ ] **Output directory:** `Tasks\Q2\javadoc` (oder `Tasks\Q2\JavaDoc` - wie du willst)
- [ ] **Visibility level:** `protected` ✅ (oder `private` - beides ok)

### 3. Generation Options:
**Für "nur das Nötigste" kannst du deaktivieren:**
- [ ] ~~Generate hierarchy tree~~ (nicht nötig)
- [x] **Generate navigation bar** ✅ (brauchst du)
- [x] **Generate index** ✅ (brauchst du)
- [ ] ~~Separate index per letter~~ (nicht nötig)

### 4. JavaDoc Tags:
**WICHTIG - diese MÜSSEN aktiviert sein, da dein Code sie verwendet:**
- [x] **@author** ✅ (DEIN CODE HAT DIESE TAGS!)
- [x] **@version** ✅ (DEIN CODE HAT DIESE TAGS!)
- [x] @deprecated ✅ (ok)

### 5. Andere Einstellungen:
- [x] **Open generated documentation in browser** ✅ (praktisch!)
- **Command line arguments:** Leer lassen (ok)

---

## 📋 Checkliste vor dem Klicken auf "Generate":

1. ✅ Scope: **"Whole project"** (NICHT README.md!)
2. ✅ Output directory: `Tasks\Q2\javadoc` (oder ähnlich)
3. ✅ Visibility: `protected` oder `private` (beides ok)
4. ✅ **@author** aktiviert
5. ✅ **@version** aktiviert
6. ✅ "Open generated documentation in browser" aktiviert

Dann auf **"Generate"** klicken!

---

## 🎯 Nach der Generierung:

- Ordner `javadoc` (oder `JavaDoc`) wird erstellt
- `index.html` öffnet sich automatisch im Browser
- Fertig! 🎉

