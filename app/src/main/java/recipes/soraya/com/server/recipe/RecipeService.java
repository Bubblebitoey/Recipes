package recipes.soraya.com.server.recipe;

import io.reactivex.Observable;
import recipes.soraya.com.model.RecipePreview;

import java.util.*;


public interface RecipeService {
	
	/**
  * Search the recipe database for recipes containing a specific term.
  * @param term the term to search for.
  * @param maxResults the maximum number of results to return to the client.
  * @return an {@link Observable} which emits the list of matching recipes.
  */
	Observable<List<RecipePreview>> searchRecipes(String term, int maxResults);
}
