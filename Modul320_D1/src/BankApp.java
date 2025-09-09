import java.util.Scanner;

/**
 * Konsolen-App mit Menü:
 * - Konto anlegen
 * - Einzahlen
 * - Abheben
 * - Transfer
 * - Übersicht
 *
 * Zeigt:
 *  - Datenkapselung (private Attribute, Methodensteuerung)
 *  - Objektkommunikation (transfer → Methodenaufruf auf fremdem Konto mit Werteübergabe)
 *  - Zustandsänderung (Saldo ändert sich)
 *  - Primitive vs. komplexe Datentypen im Code
 */

public class BankApp {

    public static void main(String[] args) {
        Bank bank = new Bank("TBZ Bank");
        Scanner sc = new Scanner(System.in);

        // Demo-Daten (optional)
        var k1 = bank.neuerKunde("Alice Example");
        var k2 = bank.neuerKunde("Bob Muster");
        var a = bank.kontoEroeffnen(k1, 1000.0);
        var b = bank.kontoEroeffnen(k2, 200.0);

        boolean running = true;
        while (running) {
            System.out.println();
            System.out.println("==== " + bank.getName() + " ====");
            System.out.println("1) Konto eröffnen");
            System.out.println("2) Einzahlen");
            System.out.println("3) Abheben");
            System.out.println("4) Transfer");
            System.out.println("5) Kontenübersicht");
            System.out.println("0) Beenden");
            System.out.print("Auswahl: ");

            String auswahl = sc.nextLine().trim();
            try {
                switch (auswahl) {
                    case "1" -> kontoEroeffnen(bank, sc);
                    case "2" -> einzahlen(bank, sc);
                    case "3" -> abheben(bank, sc);
                    case "4" -> transfer(bank, sc);
                    case "5" -> bank.druckeUebersicht();
                    case "0" -> running = false;
                    default -> System.out.println("Ungültige Auswahl.");
                }
            } catch (Exception ex) {
                System.out.println("Fehler: " + ex.getMessage());
            }
        }

        // Primitive vs. komplex – kurze Demonstration zum Schluss:
        double betrag = 50.0; // primitiver Typ (by value)
        a.transfer(b, betrag, "Demo-Transfer");
        System.out.println("Primitive Variable 'betrag' nach Transfer (unverändert): " + betrag);

        System.out.println("Programm beendet.");
        sc.close();
    }

    private static void kontoEroeffnen(Bank bank, Scanner sc) {
        System.out.print("Kundenname: ");
        String name = sc.nextLine().trim();
        var kunde = bank.neuerKunde(name);

        System.out.print("Startsaldo (CHF): ");
        double startSaldo = parseDouble(sc.nextLine());

        var konto = bank.kontoEroeffnen(kunde, startSaldo);
        System.out.println("Konto erstellt: " + konto.getIban() + " | Inhaber: " + konto.getInhaber());
    }

    private static void einzahlen(Bank bank, Scanner sc) {
        var konto = frageKonto(bank, sc, "IBAN zum Einzahlen");
        System.out.print("Betrag (CHF): ");
        double betrag = parseDouble(sc.nextLine());
        konto.einzahlen(betrag);
        System.out.printf("Eingezahlt. Neuer Saldo: %.2f CHF%n", konto.getSaldo());
    }

    private static void abheben(Bank bank, Scanner sc) {
        var konto = frageKonto(bank, sc, "IBAN zum Abheben");
        System.out.print("Betrag (CHF): ");
        double betrag = parseDouble(sc.nextLine());
        konto.abheben(betrag);
        System.out.printf("Abgehoben. Neuer Saldo: %.2f CHF%n", konto.getSaldo());
    }

    private static void transfer(Bank bank, Scanner sc) {
        var quelle = frageKonto(bank, sc, "Quell-IBAN");
        var ziel   = frageKonto(bank, sc, "Ziel-IBAN");
        System.out.print("Betrag (CHF): ");
        double betrag = parseDouble(sc.nextLine());
        System.out.print("Referenz (optional): ");
        String ref = sc.nextLine().trim();
        if (ref.isBlank()) ref = "Ohne Referenz";
        quelle.transfer(ziel, betrag, ref);
        System.out.printf("Transfer OK. Quelle: %.2f CHF, Ziel: %.2f CHF%n",
                quelle.getSaldo(), ziel.getSaldo());
    }

    private static Konto frageKonto(Bank bank, Scanner sc, String prompt) {
        System.out.print(prompt + ": ");
        String iban = sc.nextLine().trim();
        return bank.findeKonto(iban)
                .orElseThrow(() -> new IllegalArgumentException("Kein Konto mit IBAN " + iban + " gefunden."));
    }

    private static double parseDouble(String s) {
        try {
            return Double.parseDouble(s.replace(",", "."));
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException("Bitte Zahl eingeben (z.B. 100.50).");
        }
    }
}
