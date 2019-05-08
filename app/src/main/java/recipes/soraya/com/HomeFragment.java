package recipes.soraya.com;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.SearchView;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;
import io.reactivex.subjects.BehaviorSubject;
import io.reactivex.subjects.Subject;
import recipes.soraya.com.adapter.RecipeSearchResultAdapter;
import recipes.soraya.com.model.RecipePreview;
import recipes.soraya.com.server.recipe.RecipeService;
import recipes.soraya.com.server.recipe.recipePuppy.RecipePuppyService;

import java.util.*;
import java.util.concurrent.TimeUnit;

public class HomeFragment extends Fragment implements SearchView.OnQueryTextListener {
	
	private final int MAX_RECIPES_TO_SHOW = 25;
	
	private RecipeService recipeService;
	private RecipeSearchResultAdapter adapter;
	private SearchView searchView;
	private ListView searchResultList;
	private Subject<String> searchTextSubject;
	private Observable<String> onSearchTextChanged;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.fragment_home, container, false);
		
			initialiseApiClient();
	        searchView = (SearchView) view.findViewById(R.id.searchBar);
	        searchResultList = (ListView) view.findViewById(R.id.searchResultList);
	        adapter = new RecipeSearchResultAdapter(getContext());
	        searchResultList.setAdapter(adapter);
	        searchView.setOnQueryTextListener(this);
	        searchTextSubject = BehaviorSubject.create();
	        // Debounce searches by 300ms to prevent lots of API requests in quick succession
	        onSearchTextChanged = searchTextSubject.debounce(300, TimeUnit.MILLISECONDS);
	        subscribeToSearchTextChanges();
		
		return view;
	}
	
	private void initialiseApiClient() {
        this.recipeService = new RecipePuppyService() {
        };
    }

    @Override
    public boolean onQueryTextSubmit(String query) {
        return true;
    }

    @Override
    public boolean onQueryTextChange(final String newText) {
        if (newText.trim().length() == 0) {
            adapter.clear();
            return true;
        }
        searchTextSubject.onNext(newText);
        return true;
    }

    private void subscribeToSearchTextChanges() {
        onSearchTextChanged.subscribe(new Consumer<String>() {
            @Override
            public void accept(final String text) throws Exception {
                recipeService.searchRecipes(text, MAX_RECIPES_TO_SHOW)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .doOnNext(new Consumer<List<RecipePreview>>() {
                            @Override
                            public void accept(List<RecipePreview> recipes) throws Exception {
                                adapter.clear();
                                adapter.addAll(recipes);
                            }
                        })
                        .subscribe();
            }
        });
    }
}
