import controller.RecipeController;

/**
 * Hauptklasse - Einstiegspunkt der Applikation.
 * Verantwortlich nur für das Starten der Applikation (Delegation).
 */
public class Main {
    public static void main(String[] args) {
        // Delegation: Main delegiert die gesamte Applikationslogik an den Controller
        RecipeController controller = new RecipeController();
        controller.start();
    }
}

