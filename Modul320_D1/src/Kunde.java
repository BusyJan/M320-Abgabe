public class Kunde {
    private final String name;
    private final String kundennummer; // z.B. K0001

    public Kunde(String name, String kundennummer) {
        if (name == null || name.isBlank()) throw new IllegalArgumentException("Name darf nicht leer sein.");
        if (kundennummer == null || kundennummer.isBlank()) throw new IllegalArgumentException("Kundennummer darf nicht leer sein.");
        this.name = name;
        this.kundennummer = kundennummer;
    }

    public String getName() {
        return name;
    }

    public String getKundennummer() {
        return kundennummer;
    }

    @Override
    public String toString() {
        return name + " (" + kundennummer + ")";
    }
}
