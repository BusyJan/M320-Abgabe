import java.util.*;

/**
 * Bank-Verwaltung (In-Memory).
 * - Hält Konten in einer Map (komplexer Datentyp).
 * - Erzeugt IBANs und Kundennummern.
 * - Bietet gekapselte Zugriffsmethoden.
 */
public class Bank {
    private final String name;
    private final Map<String, Konto> konten = new HashMap<>();
    private int laufendeKundenNummer = 1;
    private int laufendeIbanNummer = 1;

    public Bank(String name) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name darf nicht leer sein.");
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Kunde neuerKunde(String kundenName) {
        String knr = "K%04d".formatted(laufendeKundenNummer++);
        return new Kunde(kundenName, knr);
    }

    public Konto kontoEroeffnen(Kunde inhaber, double startSaldo) {
        String iban = generiereIban();
        if (konten.containsKey(iban)) throw new IllegalStateException("IBAN bereits vergeben (sollte nicht passieren).");
        Konto k = new Konto(iban, inhaber, startSaldo);
        konten.put(iban, k);
        return k;
    }

    public Optional<Konto> findeKonto(String iban) {
        return Optional.ofNullable(konten.get(iban));
    }

    public Collection<Konto> alleKonten() {
        return Collections.unmodifiableCollection(konten.values());
    }

    private String generiereIban() {
        // sehr vereinfachte pseudo-IBAN
        return "CH93-0000-0000-%04d".formatted(laufendeIbanNummer++);
    }

    public void druckeUebersicht() {
        System.out.println("=== " + name + " – Kontenübersicht ===");
        if (konten.isEmpty()) {
            System.out.println("(keine Konten vorhanden)");
            return;
        }
        for (Konto k : konten.values()) {
            System.out.println(k);
        }
    }
}
