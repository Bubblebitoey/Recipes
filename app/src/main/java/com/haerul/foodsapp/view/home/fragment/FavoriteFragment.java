/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 5/10/19 4:33 PM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import butterknife.BindView;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.adapter.RecyclerViewFavorite;
import com.haerul.foodsapp.model.FavoriteRepository;

import java.util.*;

public class FavoriteFragment extends Fragment implements Observer {
	
	@BindView(R.id.toolbar)
	Toolbar toolbar;
	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.viewPager)
	ViewPager viewPager;
	
		FavoriteRepository favoriteRepository = FavoriteRepository.getInstance();
		RecyclerViewFavorite recyclerViewFav;
		private RecyclerView favFromDB;
		
		
		@Nullable
			@Override
			public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_fav, container, false);
			
			favoriteRepository.getFavoriteList();
			
			
			
			
			return v;
		}
		
	@Override
	public void update(Observable observable, Object o) {
	
	
	}
}
