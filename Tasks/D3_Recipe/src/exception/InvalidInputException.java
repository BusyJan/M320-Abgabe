package exception;

/**
 * Eigene Exception-Klasse für ungültige Benutzereingaben.
 * Demonstriert Exception Handling auf Niveau 3.
 */
public class InvalidInputException extends Exception {
    
    /**
     * Erstellt eine neue InvalidInputException mit einer Nachricht.
     * 
     * @param message Fehlernachricht für den Benutzer
     */
    public InvalidInputException(String message) {
        super(message);
    }
    
    /**
     * Erstellt eine neue InvalidInputException mit Nachricht und Ursache.
     * 
     * @param message Fehlernachricht für den Benutzer
     * @param cause Ursprüngliche Exception
     */
    public InvalidInputException(String message, Throwable cause) {
        super(message, cause);
    }
}

