
package com.haerul.foodsapp.view.home.detail;

import com.haerul.foodsapp.model.Meals;

public interface DetailView {
    //TODO #4 Add void method  for showLoading, hideLoading, setMeal, onErrorLoading;
    void showLoading();
    void hideLoading();
    void setMeals(Meals.Meal meal);
    void onErrorLoading(String message);
}