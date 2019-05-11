/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 5/10/19 1:38 PM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.model;

import android.util.Log;

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
	
	public void addToFavoriteList(Meals.Meal meal) {
		if(meal == null) { return ; }
		for(Meals.Meal m : this.favoriteList) {
			if (m.getStrMeal().equals(meal.getStrMeal())) {
				return;
			}
		}
		this.favoriteList.add(meal);
		int index = this.favoriteList.indexOf(meal);
		setChanged();
		notifyObservers("add " + index);
	}
	
	public void removeFromFavoriteList(Meals.Meal meal) {
		String id = meal.getIdMeal();
		this.favoriteList.remove(meal);
		setChanged();
		notifyObservers("remove " + id);
	}
}
