package recipes.soraya.com.models;

import java.util.ArrayList;

public class Recipe {
    private String name;
    private Ingredient ingredient;
    private ArrayList<String> steps;
    private ArrayList<String> timers;
    private String imageURL;
    private String originalURL;

    public Recipe(String name, Ingredient ingredient, ArrayList<String> steps, ArrayList<String> timers, String imageURL, String originalURL) {
        this.name = name;
        this.ingredient = ingredient;
        this.imageURL = imageURL;
        this.originalURL = originalURL;
        this.steps = new ArrayList<>();
        steps.addAll(steps);
        this.timers = new ArrayList<>();
        this.timers.addAll(timers);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getOriginalURL() {
        return originalURL;
    }

    public void setOriginalURL(String originalURL) {
        this.originalURL = originalURL;
    }

    public ArrayList<String> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<String> steps) {
        this.steps = steps;
    }

    public ArrayList<String> getTimers() {
        return timers;
    }

    public void setTimers(ArrayList<String> timers) {
        this.timers = timers;
    }
}
