package recipes.soraya.com.models;

public class Recipe {
    private String name;
    private Ingredient ingredient;
    private String imageURL;
    private String originalURL;

    public Recipe(String name, Ingredient ingredient, String imageURL, String originalURL) {
        this.name = name;
        this.ingredient = ingredient;
        this.imageURL = imageURL;
        this.originalURL = originalURL;
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
}
