package recipes.soraya.com.server.recipe.recipePuppy;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface RecipePuppyApiClient {
	/**
     * Public URL where the RecipePreview Puppy API can be located.
     */
    String SERVICE_ENDPOINT = "http://www.recipepuppy.com/";

    @GET("api/")
    Observable<GetRecipeResponse> searchRecipes(@Query("q") String searchTerm, @Query("p") int page);
}
