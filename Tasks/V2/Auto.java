public class Auto extends Fahrzeug {
    public Auto(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.2 + 200;
    }
}
