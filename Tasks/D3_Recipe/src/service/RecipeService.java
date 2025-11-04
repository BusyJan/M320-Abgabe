package service;

import model.Recipe;
import exception.RecipeNotFoundException;
import java.util.*;
import java.util.stream.Collectors;

/**
 * Service-Klasse für die Rezept-Logik.
 * 
 * DELEGATION: Diese Klasse übernimmt die gesamte Business-Logik.
 * Sie ist verantwortlich für:
 * - Datenverwaltung (würde normalerweise mit einer API/Datenbank kommunizieren)
 * - Such- und Filterlogik
 * - Datenverarbeitung
 * 
 * Der Controller delegiert alle logischen Operationen hierher.
 */
public class RecipeService {
    private List<Recipe> recipes;
    
    /**
     * Konstruktor initialisiert den Service.
     * In einer echten Anwendung würde hier eine Verbindung zu einer
     * externen API oder Datenbank hergestellt werden.
     */
    public RecipeService() {
        this.recipes = new ArrayList<>();
        initializeMockData(); // Simuliert API-Call
    }
    
    /**
     * Simuliert einen API-Call zur Initialisierung der Daten.
     * In einer echten Anwendung würde dies eine externe Recipe-API aufrufen.
     */
    private void initializeMockData() {
        // Simulierte Daten (würde normalerweise von einer API kommen)
        recipes.add(new Recipe(
            1, "Spaghetti Carbonara", "Hauptgericht", "Italienisch", 25, 4,
            Arrays.asList("400g Spaghetti", "200g Speck", "4 Eier", "100g Parmesan", "Salz, Pfeffer"),
            Arrays.asList(
                "Spaghetti in Salzwasser kochen",
                "Speck in Würfel schneiden und anbraten",
                "Eier mit Parmesan verquirlen",
                "Spaghetti abgießen, mit Speck mischen",
                "Eier-Mix unterrühren, servieren"
            ),
            650
        ));
        
        recipes.add(new Recipe(
            2, "Caesar Salad", "Vorspeise", "Amerikanisch", 15, 2,
            Arrays.asList("1 Römersalat", "100g Croutons", "50g Parmesan", "Caesar Dressing", "Hühnerbrust"),
            Arrays.asList(
                "Salat waschen und zerkleinern",
                "Hühnerbrust braten und in Streifen schneiden",
                "Salat mit Dressing mischen",
                "Croutons und Parmesan darüber geben",
                "Mit Hühnchen toppen und servieren"
            ),
            420
        ));
        
        recipes.add(new Recipe(
            3, "Tiramisu", "Dessert", "Italienisch", 30, 6,
            Arrays.asList("500g Mascarpone", "6 Eier", "200g Zucker", "300ml Espresso", "Löffelbiskuits", "Kakao"),
            Arrays.asList(
                "Eigelb mit Zucker schaumig schlagen",
                "Mascarpone unterrühren",
                "Eiweiß steif schlagen und unterheben",
                "Löffelbiskuits in Espresso tränken",
                "Schichten: Biskuits, Creme, Biskuits, Creme",
                "Mit Kakao bestäuben, 4 Stunden kühlen"
            ),
            380
        ));
        
        recipes.add(new Recipe(
            4, "Thai Curry", "Hauptgericht", "Asiatisch", 35, 4,
            Arrays.asList("400ml Kokosmilch", "2 EL Curry-Paste", "400g Hühnchen", "Gemüse nach Wahl", "Reis"),
            Arrays.asList(
                "Reis nach Packungsanleitung kochen",
                "Hühnchen in Stücke schneiden und anbraten",
                "Curry-Paste kurz mit anbraten",
                "Kokosmilch zugeben und köcheln lassen",
                "Gemüse hinzufügen und garen",
                "Mit Reis servieren"
            ),
            580
        ));
        
        recipes.add(new Recipe(
            5, "Schokoladen-Brownies", "Dessert", "Amerikanisch", 40, 12,
            Arrays.asList("200g Schokolade", "200g Butter", "4 Eier", "300g Zucker", "150g Mehl", "Prise Salz"),
            Arrays.asList(
                "Ofen auf 180°C vorheizen",
                "Schokolade und Butter schmelzen",
                "Eier und Zucker schaumig schlagen",
                "Schokoladenmischung unterrühren",
                "Mehl und Salz einrühren",
                "In Form füllen, 25-30 Min. backen"
            ),
            320
        ));
        
        recipes.add(new Recipe(
            6, "Guacamole", "Snack", "Mexikanisch", 10, 4,
            Arrays.asList("3 reife Avocados", "1 Tomate", "1 Zwiebel", "1 Limette", "Koriander", "Salz"),
            Arrays.asList(
                "Avocados halbieren und Kern entfernen",
                "Fruchtfleisch mit Gabel zerdrücken",
                "Tomate und Zwiebel fein würfeln",
                "Alles vermischen",
                "Limettensaft, Koriander und Salz hinzufügen",
                "Mit Nachos servieren"
            ),
            180
        ));
    }
    
    /**
     * Sucht Rezepte nach Namen.
     * BUSINESS-LOGIK: Implementiert die Suchfunktionalität.
     * 
     * @param searchTerm Suchbegriff (bereits validiert vom Controller)
     * @return Liste gefundener Rezepte
     */
    public List<Recipe> searchByName(String searchTerm) {
        String lowerSearchTerm = searchTerm.toLowerCase();
        return recipes.stream()
                .filter(recipe -> recipe.getName().toLowerCase().contains(lowerSearchTerm))
                .collect(Collectors.toList());
    }
    
    /**
     * Sucht Rezepte nach Kategorie.
     * 
     * @param category Kategorie (bereits validiert)
     * @return Liste gefundener Rezepte
     */
    public List<Recipe> searchByCategory(String category) {
        return recipes.stream()
                .filter(recipe -> recipe.getCategory().equalsIgnoreCase(category))
                .collect(Collectors.toList());
    }
    
    /**
     * Sucht Rezepte nach Küche.
     * 
     * @param cuisine Küche
     * @return Liste gefundener Rezepte
     */
    public List<Recipe> searchByCuisine(String cuisine) {
        String lowerCuisine = cuisine.toLowerCase();
        return recipes.stream()
                .filter(recipe -> recipe.getCuisine().toLowerCase().contains(lowerCuisine))
                .collect(Collectors.toList());
    }
    
    /**
     * Sucht ein Rezept nach ID.
     * 
     * @param id Rezept-ID (bereits validiert)
     * @return Rezept-Objekt
     * @throws RecipeNotFoundException wenn kein Rezept gefunden wurde
     */
    public Recipe getRecipeById(int id) throws RecipeNotFoundException {
        return recipes.stream()
                .filter(recipe -> recipe.getId() == id)
                .findFirst()
                .orElseThrow(() -> new RecipeNotFoundException(
                    "Rezept mit ID " + id + " wurde nicht gefunden!"
                ));
    }
    
    /**
     * Gibt alle verfügbaren Rezepte zurück.
     * 
     * @return Liste aller Rezepte
     */
    public List<Recipe> getAllRecipes() {
        return new ArrayList<>(recipes);
    }
    
    /**
     * Filtert Rezepte nach maximaler Zubereitungszeit.
     * 
     * @param maxTime Maximale Zeit in Minuten
     * @return Liste gefundener Rezepte
     */
    public List<Recipe> filterByMaxTime(int maxTime) {
        return recipes.stream()
                .filter(recipe -> recipe.getPreparationTime() <= maxTime)
                .collect(Collectors.toList());
    }
    
    /**
     * Filtert Rezepte nach maximalen Kalorien.
     * 
     * @param maxCalories Maximale Kalorien
     * @return Liste gefundener Rezepte
     */
    public List<Recipe> filterByMaxCalories(int maxCalories) {
        return recipes.stream()
                .filter(recipe -> recipe.getCalories() <= maxCalories)
                .collect(Collectors.toList());
    }
    
    /**
     * Gibt verfügbare Kategorien zurück.
     * 
     * @return Liste aller Kategorien
     */
    public List<String> getAvailableCategories() {
        return recipes.stream()
                .map(Recipe::getCategory)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
    
    /**
     * Gibt verfügbare Küchen zurück.
     * 
     * @return Liste aller Küchen
     */
    public List<String> getAvailableCuisines() {
        return recipes.stream()
                .map(Recipe::getCuisine)
                .distinct()
                .sorted()
                .collect(Collectors.toList());
    }
}

