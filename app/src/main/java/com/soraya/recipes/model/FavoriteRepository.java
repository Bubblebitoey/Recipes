package com.soraya.recipes.model;

import java.util.*;

public class FavoriteRepository extends Observable {
	
	private static FavoriteRepository instance = null;
	private ArrayList<Meals.Meal> favoriteList;
	
	private FavoriteRepository() {
		favoriteList = new ArrayList<>();
	}
	
	public static FavoriteRepository getInstance() {
		if(instance == null) {
			instance = new FavoriteRepository();
		}
		return instance;
	}
	
	public ArrayList<Meals.Meal> getFavoriteList() {
		return favoriteList;
	}
	
	public void setFavoriteList(ArrayList<Meals.Meal> favoriteList) {
		this.favoriteList = favoriteList;
	}
	
	public int addToFavoriteList(Meals.Meal meal) {
		if(meal == null) { return -1; }
		for(Meals.Meal m : this.favoriteList) {
			if (m.getStrMeal().equals(meal.getStrMeal())) {
				return 0;
			}
		}
		this.favoriteList.add(meal);
		int index = this.favoriteList.indexOf(meal);
		setChanged();
		notifyObservers("add " + index);
		return 1;
	}

	
	public void removeFromFavoriteList(Meals.Meal meal) {
		String id = meal.getIdMeal();
		this.favoriteList.remove(meal);
		setChanged();
		notifyObservers("remove " + id);
	}
}
