package com.soraya.recipes.view.home.fragment;

import com.soraya.recipes.model.Meals;

import java.util.*;

public interface FragmentView {
	
    void setMeals(ArrayList<Meals.Meal> favList);
    void onErrorLoading(String message);
}
