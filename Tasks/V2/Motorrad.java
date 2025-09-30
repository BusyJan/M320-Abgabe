public class Motorrad extends Fahrzeug {
    public Motorrad(String marke, int kilometerstand) {
        super(marke, kilometerstand);
    }

    @Override
    public double berechneReparaturKosten() {
        return getKilometerstand() * 0.1 + 100;
    }
}
