package recipes.soraya.com.server.recipe.recipePuppy;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import recipes.soraya.com.model.RecipePreview;
import recipes.soraya.com.server.recipe.RecipeService;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import java.util.*;

public class RecipePuppyService implements RecipeService {
	private final int RECIPES_PER_PAGE = 10;

    private final RecipePuppyApiClient apiClient;

    /**
     * Constructor which allows injection of a configured {@link RecipePuppyApiClient} instance
     * to be used when fetching recipes from the internet.
     * @param apiClient the apiClient used to query the
     */
    public RecipePuppyService(RecipePuppyApiClient apiClient) {
        this.apiClient = apiClient;
    }

    /**
     * Default constructor which handles the initialisation of the underlying {@link Retrofit}
     * {@link RecipePuppyApiClient} instance.
     */
    public RecipePuppyService() {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.createWithScheduler(Schedulers.io()))
                .baseUrl(RecipePuppyApiClient.SERVICE_ENDPOINT)
                .build();
        this.apiClient = retrofit.create(RecipePuppyApiClient.class);
    }

    @Override
    public io.reactivex.Observable<List<RecipePreview>> searchRecipes(final String term, final int maxResults) {
        return io.reactivex.Observable.create(new ObservableOnSubscribe<List<RecipePreview>>() {
            @Override
            public void subscribe(ObservableEmitter<List<RecipePreview>> e) throws Exception {
                int nextPage = 1;
                List<RecipePreview> recipes = new ArrayList<>();
                boolean hasMoreResults = true;
                do {
                    List<RecipePreview> recipesInPage = getPageOfSearchResults(term, nextPage).blockingFirst();
                    recipes.addAll(recipesInPage);
                    if (isLastPageOfRecipes(recipesInPage) || hasFilledRecipeQuota(recipes, maxResults)) {
                        e.onNext(truncateRecipeList(recipes, maxResults));
                        e.onComplete();
                        hasMoreResults = false;
                    } else {
                        nextPage = recipes.size() / RECIPES_PER_PAGE + 1;
                    }
                } while (hasMoreResults);
            }
        });
    }
    
    

    private boolean isLastPageOfRecipes(List<RecipePreview> recipes) {
        return recipes.size() < RECIPES_PER_PAGE;
    }

    private boolean hasFilledRecipeQuota(List<RecipePreview> recipes, int quota) {
        return recipes.size() >= quota;
    }

    private List<RecipePreview> truncateRecipeList(List<RecipePreview> recipes, int maxResults) {
        return recipes.size() > maxResults ? recipes.subList(0, maxResults) : recipes;
    }

    private Observable<List<RecipePreview>> getPageOfSearchResults(String term, int page) {
        return this.apiClient.searchRecipes(term, page)
                .map(new Function<GetRecipeResponse, List<RecipePreview>>() {
                    @Override
                    public List<RecipePreview> apply(GetRecipeResponse getRecipeResponse) throws Exception {
                        return getRecipeResponse.getRecipes();
                    }
                })
                .subscribeOn(Schedulers.io());
    }
}
