
package com.soraya.recipes.view.home;

import android.support.annotation.NonNull;
import com.soraya.recipes.Utils;
import com.soraya.recipes.model.Categories;
import com.soraya.recipes.model.Meals;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

class HomePresenter {
    
    public HomePresenter(HomeView view) {
        this.view = view;
    }
    
    private HomeView view;
    

    // TODO 15 Create the constructor (View)
    

    void getMeals() {
        view.showLoading();
        
        Call<Meals> mealsCall = Utils.getApi().getMeal();
        mealsCall.enqueue(new Callback<Meals>() {
            @Override
            public void onResponse(@NonNull Call<Meals> call, @NonNull Response<Meals> response) {
                view.hideLoading();
        
                if (response.isSuccessful() && response.body() != null) { view.setMeal(response.body().getMeals()); } else { view.onErrorLoading(response.message()); } }
                
                @Override
                public void onFailure(@NonNull Call<Meals> call, @NonNull Throwable t) { view.hideLoading();
                view.onErrorLoading(t.getLocalizedMessage()); }
                });
    }


    void getCategories() {
        // TODO 26 do loading before making a request to the server
        view.showLoading();

        // TODO 27 create Call<Categories> categoriesCall = ...
        Call<Categories> categoriesCall = Utils.getApi().getCategories();

        // TODO 28 waiting for enqueue Callback
        categoriesCall.enqueue(new Callback<Categories>() {
            @Override
            public void onResponse(@NonNull Call<Categories> call, @NonNull Response<Categories> response) {
                // TODO 29 Non-empty results check & Non-empty results check
                view.hideLoading();
                if (response.isSuccessful() && response.body() != null) {
                    /*
                     * TODO 30 Receive the result
                     * input the results obtained into the setMeals() behavior
                     * and enter response.body() to the parameter
                     */
                    view.setCategory(response.body().getCategories());
                    


                }
                else {
                    // TODO 31 Show an error message if the conditions are not met
                    view.onErrorLoading(response.message());

                }
            }

            @Override
            public void onFailure(@NonNull Call<Categories> call, @NonNull Throwable t) {
                /*
                 * Failure will be thrown here
                 * for this you must do
                 * 1. closes loading
                 * 2. displays an error message
                 */

                // TODO 32 Close loading
                view.hideLoading();
                // TODO 33 Show an error message
                view.onErrorLoading(t.getLocalizedMessage());
            }
        });
    }
}
