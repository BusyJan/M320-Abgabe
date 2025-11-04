package model;

import java.util.List;

/**
 * Datenmodell für ein Rezept.
 * Repräsentiert ein Rezept mit allen relevanten Informationen.
 */
public class Recipe {
    private int id;
    private String name;
    private String category;
    private String cuisine;
    private int preparationTime; // in Minuten
    private int servings;
    private List<String> ingredients;
    private List<String> instructions;
    private int calories;
    
    public Recipe(int id, String name, String category, String cuisine, 
                  int preparationTime, int servings, List<String> ingredients, 
                  List<String> instructions, int calories) {
        this.id = id;
        this.name = name;
        this.category = category;
        this.cuisine = cuisine;
        this.preparationTime = preparationTime;
        this.servings = servings;
        this.ingredients = ingredients;
        this.instructions = instructions;
        this.calories = calories;
    }
    
    // Getters
    public int getId() { return id; }
    public String getName() { return name; }
    public String getCategory() { return category; }
    public String getCuisine() { return cuisine; }
    public int getPreparationTime() { return preparationTime; }
    public int getServings() { return servings; }
    public List<String> getIngredients() { return ingredients; }
    public List<String> getInstructions() { return instructions; }
    public int getCalories() { return calories; }
    
    @Override
    public String toString() {
        return String.format("%s (%s) - %s Küche, %d Min., %d kcal", 
            name, category, cuisine, preparationTime, calories);
    }
    
    /**
     * Gibt eine detaillierte Darstellung des Rezepts zurück.
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("\n╔════════════════════════════════════════════════════════════╗\n");
        sb.append(String.format("║  %-56s  ║%n", name.toUpperCase()));
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append(String.format("║  Kategorie:      %-42s  ║%n", category));
        sb.append(String.format("║  Küche:          %-42s  ║%n", cuisine));
        sb.append(String.format("║  Zubereitungszeit: %-38d Min.║%n", preparationTime));
        sb.append(String.format("║  Portionen:      %-42d  ║%n", servings));
        sb.append(String.format("║  Kalorien:       %-38d kcal║%n", calories));
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append("║  ZUTATEN:                                                  ║\n");
        for (String ingredient : ingredients) {
            sb.append(String.format("║  • %-55s║%n", ingredient));
        }
        sb.append("╠════════════════════════════════════════════════════════════╣\n");
        sb.append("║  ANLEITUNG:                                                ║\n");
        for (int i = 0; i < instructions.size(); i++) {
            String instruction = instructions.get(i);
            sb.append(String.format("║  %d. %-54s║%n", i + 1, instruction));
        }
        sb.append("╚════════════════════════════════════════════════════════════╝\n");
        return sb.toString();
    }
}

