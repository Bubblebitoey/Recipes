package com.haerul.foodsapp.view.home.fragment;

import com.haerul.foodsapp.model.Meals;

import java.util.*;

public interface FragmentView {
	
    void setMeals(ArrayList<Meals.Meal> favList);
    void onErrorLoading(String message);
}
