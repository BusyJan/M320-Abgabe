public class LKW extends Fahrzeug {
    public LKW(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.5 + 500;
    }
}
