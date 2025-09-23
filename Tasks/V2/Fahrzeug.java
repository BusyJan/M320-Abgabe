public class Fahrzeug {
    private String marke;
    private int kilometerstand;

    public Fahrzeug(String marke, int kilometerstand) {
        this.marke = marke;
        this.kilometerstand = kilometerstand;
    }

    public String getMarke() {
        return marke;
    }

    public int getKilometerstand() {
        return kilometerstand;
    }

    public double berechneReparaturKosten() {
        return 100.0;
    }

    public void reparieren() {
        System.out.println("Allgemeine Reparatur durchgeführt");
    }

    public void reparieren(String teil) {
        System.out.println("Teil gewechselt: " + teil);
    }
}
