package util;

import exception.InvalidInputException;

/**
 * Utility-Klasse für die Validierung von Benutzereingaben.
 * Trennt die Validierungslogik vom Controller (Separation of Concerns).
 */
public class InputValidator {
    
    /**
     * Validiert eine Menü-Auswahl.
     * 
     * @param input Benutzereingabe
     * @param min Minimaler erlaubter Wert
     * @param max Maximaler erlaubter Wert
     * @return Validierte Eingabe als Integer
     * @throws InvalidInputException wenn die Eingabe ungültig ist
     */
    public static int validateMenuChoice(String input, int min, int max) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException("Eingabe darf nicht leer sein!");
        }
        
        try {
            int choice = Integer.parseInt(input.trim());
            
            if (choice < min || choice > max) {
                throw new InvalidInputException(
                    String.format("Eingabe muss zwischen %d und %d liegen!", min, max)
                );
            }
            
            return choice;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Eingabe muss eine Zahl sein!", e);
        }
    }
    
    /**
     * Validiert einen Suchbegriff.
     * 
     * @param searchTerm Suchbegriff
     * @return Validierter und bereinigter Suchbegriff
     * @throws InvalidInputException wenn der Suchbegriff ungültig ist
     */
    public static String validateSearchTerm(String searchTerm) throws InvalidInputException {
        if (searchTerm == null || searchTerm.trim().isEmpty()) {
            throw new InvalidInputException("Suchbegriff darf nicht leer sein!");
        }
        
        String trimmed = searchTerm.trim();
        
        if (trimmed.length() < 2) {
            throw new InvalidInputException("Suchbegriff muss mindestens 2 Zeichen lang sein!");
        }
        
        if (trimmed.length() > 50) {
            throw new InvalidInputException("Suchbegriff darf maximal 50 Zeichen lang sein!");
        }
        
        // Nur Buchstaben, Zahlen und Leerzeichen erlauben
        if (!trimmed.matches("[a-zA-ZäöüÄÖÜß0-9 ]+")) {
            throw new InvalidInputException("Suchbegriff darf nur Buchstaben, Zahlen und Leerzeichen enthalten!");
        }
        
        return trimmed;
    }
    
    /**
     * Validiert eine Rezept-ID.
     * 
     * @param input Benutzereingabe
     * @return Validierte Rezept-ID
     * @throws InvalidInputException wenn die ID ungültig ist
     */
    public static int validateRecipeId(String input) throws InvalidInputException {
        if (input == null || input.trim().isEmpty()) {
            throw new InvalidInputException("Rezept-ID darf nicht leer sein!");
        }
        
        try {
            int id = Integer.parseInt(input.trim());
            
            if (id <= 0) {
                throw new InvalidInputException("Rezept-ID muss größer als 0 sein!");
            }
            
            return id;
        } catch (NumberFormatException e) {
            throw new InvalidInputException("Rezept-ID muss eine Zahl sein!", e);
        }
    }
    
    /**
     * Validiert einen Kategorienamen.
     * 
     * @param category Kategorie
     * @return Validierte Kategorie
     * @throws InvalidInputException wenn die Kategorie ungültig ist
     */
    public static String validateCategory(String category) throws InvalidInputException {
        if (category == null || category.trim().isEmpty()) {
            throw new InvalidInputException("Kategorie darf nicht leer sein!");
        }
        
        String trimmed = category.trim();
        
        // Liste gültiger Kategorien
        String[] validCategories = {"Hauptgericht", "Vorspeise", "Dessert", "Snack", "Getränk"};
        
        for (String valid : validCategories) {
            if (valid.equalsIgnoreCase(trimmed)) {
                return valid; // Gibt die korrekt kapitalisierte Version zurück
            }
        }
        
        throw new InvalidInputException(
            "Ungültige Kategorie! Gültige Kategorien: Hauptgericht, Vorspeise, Dessert, Snack, Getränk"
        );
    }
}

