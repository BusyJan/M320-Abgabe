package controller;

import model.Recipe;
import service.RecipeService;
import exception.InvalidInputException;
import exception.RecipeNotFoundException;
import util.InputValidator;
import java.util.List;
import java.util.Scanner;

/**
 * Controller-Klasse für die Rezept-Applikation.
 * 
 * DELEGATION - Der Controller delegiert Aufgaben:
 * 1. User-Input → Scanner (separiert)
 * 2. Input-Validierung → InputValidator
 * 3. Business-Logik → RecipeService
 * 4. Ausgabe → eigene Display-Methoden
 * 
 * Der Controller ist nur ein "Vermittler" und enthält selbst keine Logik!
 */
public class RecipeController {
    private Scanner scanner;
    private RecipeService recipeService;
    
    /**
     * Konstruktor initialisiert Scanner und Service.
     * DELEGATION: Service wird hier injiziert (Dependency Injection).
     */
    public RecipeController() {
        this.scanner = new Scanner(System.in);
        this.recipeService = new RecipeService(); // Service-Objekt erstellen
    }
    
    /**
     * Startet die Applikation.
     * Dies ist die einzige öffentliche Methode, die von Main aufgerufen wird.
     */
    public void start() {
        displayWelcome();
        
        boolean running = true;
        while (running) {
            try {
                displayMainMenu();
                int choice = getUserChoice(0, 7);
                running = handleMenuChoice(choice);
            } catch (InvalidInputException e) {
                displayError("Eingabefehler: " + e.getMessage());
            }
        }
        
        displayGoodbye();
        scanner.close();
    }
    
    /**
     * TRENNUNG: User-Input wird hier geholt (keine Logik!).
     * 
     * @param min Minimaler Wert
     * @param max Maximaler Wert
     * @return Validierte Benutzereingabe
     * @throws InvalidInputException bei ungültiger Eingabe
     */
    private int getUserChoice(int min, int max) throws InvalidInputException {
        System.out.print("\nIhre Wahl: ");
        String input = scanner.nextLine();
        
        // DELEGATION: Validierung wird an InputValidator delegiert
        return InputValidator.validateMenuChoice(input, min, max);
    }
    
    /**
     * TRENNUNG: User-Input für Text wird hier geholt.
     * 
     * @param prompt Eingabeaufforderung
     * @return Benutzereingabe
     */
    private String getUserInput(String prompt) {
        System.out.print(prompt);
        return scanner.nextLine();
    }
    
    /**
     * Verarbeitet die Menü-Auswahl.
     * DELEGATION: Ruft entsprechende Handler-Methoden auf.
     * 
     * @param choice Benutzerauswahl
     * @return true wenn weiterlaufen, false zum Beenden
     */
    private boolean handleMenuChoice(int choice) {
        try {
            switch (choice) {
                case 1:
                    handleSearchByName();
                    break;
                case 2:
                    handleSearchByCategory();
                    break;
                case 3:
                    handleSearchByCuisine();
                    break;
                case 4:
                    handleShowRecipeDetails();
                    break;
                case 5:
                    handleShowAllRecipes();
                    break;
                case 6:
                    handleFilterByTime();
                    break;
                case 7:
                    handleShowStatistics();
                    break;
                case 0:
                    return false; // Beenden
                default:
                    displayError("Ungültige Auswahl!");
            }
        } catch (InvalidInputException e) {
            displayError("Eingabefehler: " + e.getMessage());
        } catch (RecipeNotFoundException e) {
            displayError(e.getMessage());
        }
        
        return true;
    }
    
    /**
     * TRENNUNG: Holt User-Input, validiert, delegiert an Service.
     */
    private void handleSearchByName() throws InvalidInputException {
        String searchTerm = getUserInput("\nRezeptname eingeben: ");
        
        // DELEGATION: Validierung an InputValidator
        String validatedTerm = InputValidator.validateSearchTerm(searchTerm);
        
        // DELEGATION: Suche an RecipeService
        List<Recipe> results = recipeService.searchByName(validatedTerm);
        
        // Ausgabe
        displaySearchResults(results, "Suche nach: " + validatedTerm);
    }
    
    /**
     * Suche nach Kategorie mit Delegation.
     */
    private void handleSearchByCategory() throws InvalidInputException {
        // Zeige verfügbare Kategorien
        displayAvailableCategories();
        
        String category = getUserInput("\nKategorie eingeben: ");
        
        // DELEGATION: Validierung
        String validatedCategory = InputValidator.validateCategory(category);
        
        // DELEGATION: Suche an Service
        List<Recipe> results = recipeService.searchByCategory(validatedCategory);
        
        displaySearchResults(results, "Kategorie: " + validatedCategory);
    }
    
    /**
     * Suche nach Küche.
     */
    private void handleSearchByCuisine() throws InvalidInputException {
        // Zeige verfügbare Küchen
        displayAvailableCuisines();
        
        String cuisine = getUserInput("\nKüche eingeben: ");
        
        // DELEGATION: Validierung
        String validatedCuisine = InputValidator.validateSearchTerm(cuisine);
        
        // DELEGATION: Suche an Service
        List<Recipe> results = recipeService.searchByCuisine(validatedCuisine);
        
        displaySearchResults(results, "Küche: " + validatedCuisine);
    }
    
    /**
     * Zeigt Rezept-Details an.
     */
    private void handleShowRecipeDetails() throws InvalidInputException, RecipeNotFoundException {
        String input = getUserInput("\nRezept-ID eingeben: ");
        
        // DELEGATION: Validierung
        int id = InputValidator.validateRecipeId(input);
        
        // DELEGATION: Abruf an Service
        Recipe recipe = recipeService.getRecipeById(id);
        
        // Ausgabe
        displayRecipeDetails(recipe);
    }
    
    /**
     * Zeigt alle Rezepte an.
     */
    private void handleShowAllRecipes() {
        // DELEGATION: Abruf an Service
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        displaySearchResults(allRecipes, "Alle Rezepte");
    }
    
    /**
     * Filtert nach Zubereitungszeit.
     */
    private void handleFilterByTime() throws InvalidInputException {
        String input = getUserInput("\nMaximale Zubereitungszeit (Minuten): ");
        
        // DELEGATION: Validierung
        int maxTime = InputValidator.validateMenuChoice(input, 1, 999);
        
        // DELEGATION: Filter an Service
        List<Recipe> results = recipeService.filterByMaxTime(maxTime);
        
        displaySearchResults(results, "Rezepte bis " + maxTime + " Minuten");
    }
    
    /**
     * Zeigt Statistiken an.
     */
    private void handleShowStatistics() {
        // DELEGATION: Daten vom Service
        List<Recipe> allRecipes = recipeService.getAllRecipes();
        
        int totalRecipes = allRecipes.size();
        double avgTime = allRecipes.stream()
                .mapToInt(Recipe::getPreparationTime)
                .average()
                .orElse(0);
        double avgCalories = allRecipes.stream()
                .mapToInt(Recipe::getCalories)
                .average()
                .orElse(0);
        
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                      STATISTIKEN                           ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.printf("║  Gesamt Rezepte:              %-28d  ║%n", totalRecipes);
        System.out.printf("║  Durchschnittliche Zeit:      %-23.1f Min.║%n", avgTime);
        System.out.printf("║  Durchschnittliche Kalorien:  %-23.1f kcal║%n", avgCalories);
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    // ==================== DISPLAY-METHODEN (Ausgabe) ====================
    
    private void displayWelcome() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║            WILLKOMMEN ZUR REZEPT-VERWALTUNG                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    private void displayMainMenu() {
        System.out.println("╔════════════════════════════════════════════════════════════╗");
        System.out.println("║                        HAUPTMENÜ                           ║");
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        System.out.println("║  [1] Rezept nach Namen suchen                             ║");
        System.out.println("║  [2] Rezepte nach Kategorie suchen                        ║");
        System.out.println("║  [3] Rezepte nach Küche suchen                            ║");
        System.out.println("║  [4] Rezept-Details anzeigen (nach ID)                    ║");
        System.out.println("║  [5] Alle Rezepte anzeigen                                ║");
        System.out.println("║  [6] Nach Zubereitungszeit filtern                        ║");
        System.out.println("║  [7] Statistiken anzeigen                                 ║");
        System.out.println("║  [0] Beenden                                              ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝");
    }
    
    private void displaySearchResults(List<Recipe> recipes, String title) {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.printf("║  %-56s  ║%n", title);
        System.out.println("╠════════════════════════════════════════════════════════════╣");
        
        if (recipes.isEmpty()) {
            System.out.println("║  Keine Rezepte gefunden.                                   ║");
        } else {
            for (Recipe recipe : recipes) {
                System.out.printf("║  [%d] %-52s  ║%n", recipe.getId(), recipe.toString());
            }
        }
        
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
    
    private void displayRecipeDetails(Recipe recipe) {
        System.out.println(recipe.toDetailedString());
    }
    
    private void displayAvailableCategories() {
        List<String> categories = recipeService.getAvailableCategories();
        System.out.println("\nVerfügbare Kategorien: " + String.join(", ", categories));
    }
    
    private void displayAvailableCuisines() {
        List<String> cuisines = recipeService.getAvailableCuisines();
        System.out.println("\nVerfügbare Küchen: " + String.join(", ", cuisines));
    }
    
    private void displayError(String message) {
        System.out.println("\n>>> FEHLER: " + message + " <<<\n");
    }
    
    private void displayGoodbye() {
        System.out.println("\n╔════════════════════════════════════════════════════════════╗");
        System.out.println("║              Vielen Dank - Auf Wiedersehen!                ║");
        System.out.println("╚════════════════════════════════════════════════════════════╝\n");
    }
}

