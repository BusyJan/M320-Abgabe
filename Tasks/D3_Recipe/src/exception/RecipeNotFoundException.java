package exception;

/**
 * Exception für nicht gefundene Rezepte.
 */
public class RecipeNotFoundException extends Exception {
    
    public RecipeNotFoundException(String message) {
        super(message);
    }
}

