/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 5/10/19 4:33 PM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;
import butterknife.BindView;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.adapter.RecyclerViewFavoriteAdapter;
import com.haerul.foodsapp.model.FavoriteRepository;
import com.haerul.foodsapp.model.Meals;
import com.haerul.foodsapp.view.home.detail.DetailActivity;

import java.util.*;

import static com.haerul.foodsapp.view.home.HomeActivity.EXTRA_DETAIL;

public class FavoriteFragment extends Fragment implements FragmentView, Observer {
	
	
	@BindView(R.id.favfromDB)
	RecyclerView recyclerView;
	@BindView(R.id.loadProgressBar)
	ProgressBar progressBar;
	
	
	
	FavoriteRepository favoriteRepository = FavoriteRepository.getInstance();
	RecyclerViewFavoriteAdapter adapter;
	public static final String EXTRA_POSITION = "position";
	private FirebaseAuth mAuth;
	private FirebaseDatabase firebaseDatabase = FirebaseDatabase.getInstance();
	private DatabaseReference databaseReference;
		
		
		@Nullable
			@Override
			public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			View view = inflater.inflate(R.layout.fragment_fav_recycler_meal, container, false);
			//ButterKnife.bind(this, view);
			
			favoriteRepository.addObserver(this);
			
			favoriteRepository.getFavoriteList();
			
			return view;
		}
	
	@Override
	public void update(Observable observable, Object o) {
		//TODO: Don't know what to update
		databaseReference = firebaseDatabase.getReference().child(mAuth.getCurrentUser().getUid());
		
	};
	
	//TODO: I try to setData from favoriteList
	@Override
	public void setMeals(ArrayList<Meals.Meal> favList) {
			favoriteRepository = FavoriteRepository.getInstance();
		RecyclerViewFavoriteAdapter adapter = new RecyclerViewFavoriteAdapter(getActivity(),favoriteRepository.getFavoriteList() );
		recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
		recyclerView.setClipToPadding(false);
		recyclerView.setAdapter(adapter);
		adapter.notifyDataSetChanged();
		
		adapter.setOnItemListener((view, position) -> {
			
			TextView mealName = view.findViewById(R.id.mealName);
			Intent intent = new Intent(getActivity(), DetailActivity.class);
			intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());
			intent.putExtra(EXTRA_POSITION, position);
			
			startActivity(intent);
		});
	}
	
	@Override
	public void onErrorLoading(String message) {
		Utils.showDialogMessage(getActivity(), "Error ", message);
	}
}
