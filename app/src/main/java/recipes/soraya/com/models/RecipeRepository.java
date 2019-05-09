package recipes.soraya.com.models;

import java.util.ArrayList;

public class RecipeRepository {
    private static RecipeRepository instance = null;
    private ArrayList<Recipe> recipeList;
    private ArrayList<Recipe> favoraiteList;

    private RecipeRepository() {
        recipeList = new ArrayList<>();
        favoraiteList = new ArrayList<>();
    }

    public static RecipeRepository getInstance() {
        if(instance == null) {
            instance = new RecipeRepository();
        }
        return instance;
    }

    public ArrayList<Recipe> getRecipeList() {
        return recipeList;
    }

    public ArrayList<Recipe> getFavoraiteList() {
        return favoraiteList;
    }

    public void addToFavoriteList(Recipe recipe) {
        favoraiteList.add(recipe);
    }

    public void removeFromFavoriteList(Recipe recipe) {
        favoraiteList.remove(recipe);
    }
}
