/**
 * Repräsentiert ein Bankkonto mit Kapselung und Zustandsänderung.
 * - saldo ist privat, veränderbar nur über Methoden (einzahlen/abheben/transfer).
 * - Kommunikation: transfer(...) ruft Methoden auf einem *anderen* Konto-Objekt auf.
 * - Primitive Datentypen: double (saldo, betrag)
 * - Komplex: Kunde (Referenztyp)
 */
public class Konto {
    private final String iban;
    private final Kunde inhaber;
    private double saldo; // primitiver Typ: double → wird by value übergeben

    public Konto(String iban, Kunde inhaber, double startSaldo) {
        if (iban == null || iban.isBlank()) throw new IllegalArgumentException("IBAN darf nicht leer sein.");
        if (inhaber == null) throw new IllegalArgumentException("Inhaber darf nicht null sein.");
        if (startSaldo < 0) throw new IllegalArgumentException("Startsaldo darf nicht negativ sein.");
        this.iban = iban;
        this.inhaber = inhaber;
        this.saldo = startSaldo;
    }

    public String getIban() {
        return iban;
    }

    public Kunde getInhaber() {
        return inhaber;
    }

    public double getSaldo() {
        return saldo;
    }

    // --- Zustandsänderungen (nur über Methoden erlaubt = Kapselung) ---

    public void einzahlen(double betrag) {
        pruefePositivenBetrag(betrag);
        saldo += betrag; // Zustand ändert sich
    }

    public void abheben(double betrag) {
        pruefePositivenBetrag(betrag);
        if (betrag > saldo) throw new IllegalStateException("Nicht genügend Deckung.");
        saldo -= betrag; // Zustand ändert sich
    }

    /**
     * Kommunikation & Werteübergabe:
     * - dieses Konto ("Quelle") ruft Methoden auf dem Zielkonto auf und übergibt den Betrag (double)
     */
    public void transfer(Konto ziel, double betrag, String referenz) {
        if (ziel == null) throw new IllegalArgumentException("Zielkonto darf nicht null sein.");
        if (this == ziel) throw new IllegalArgumentException("Transfer an sich selbst ist nicht erlaubt.");
        abheben(betrag);        // Zustand Quelle ändert sich
        ziel.einzahlen(betrag); // Methode auf fremdem Objekt, Wert wird übergeben
        System.out.printf("Transfer '%s': %s → %s: %.2f CHF%n", referenz, this.iban, ziel.iban, betrag);
    }

    private void pruefePositivenBetrag(double betrag) {
        if (betrag <= 0) throw new IllegalArgumentException("Betrag muss > 0 sein.");
    }

    @Override
    public String toString() {
        return "%s | %s | Saldo: %.2f CHF".formatted(iban, inhaber.getName(), saldo);
    }
}
