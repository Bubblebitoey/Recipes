package recipes.soraya.com.presenters;

import java.util.ArrayList;

import recipes.soraya.com.models.Recipe;
import recipes.soraya.com.models.RecipeRepository;

public class RecipePresenter {
    private RecipeRepository recipeRepository;

    public RecipePresenter(RecipeRepository recipeRepository) {
        this.recipeRepository = recipeRepository;
    }

    public ArrayList<Recipe> getRecipeList() { return recipeRepository.getRecipeList(); }

    public ArrayList<Recipe> getFavoraiteList() {
        return recipeRepository.getFavoraiteList();
    }

    public void addToFavoriteList(Recipe recipe) {
        recipeRepository.addToFavoriteList(recipe);
    }

    public void removeFromFavoriteList(Recipe recipe) {
        recipeRepository.removeFromFavoriteList(recipe);
    }

}
