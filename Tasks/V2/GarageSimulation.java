import java.util.ArrayList;

public class GarageSimulation {
    public static void main(String[] args) {
        ArrayList<Fahrzeug> fahrzeuge = new ArrayList<>();

        fahrzeuge.add(new Auto("BMW", 120000));
        fahrzeuge.add(new Motorrad("Yamaha", 30000));
        fahrzeuge.add(new LKW("Mercedes", 200000));

        for (Fahrzeug f : fahrzeuge) {
            System.out.println("Fahrzeug: " + f.getMarke());
            f.reparieren();
            f.reparieren("Bremsen");
            System.out.println("Kosten: " + f.berechneReparaturKosten());
            System.out.println();
        }
    }
}
