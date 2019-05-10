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
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.model.FavoriteRepository;

public class FavoriteFragment extends Fragment {
	private FavoriteRepository favoriteRepository;
		private RecyclerView mRecyclerView;
		FirebaseDatabase mFirebaseDatabase;
		DatabaseReference mRef;
		
		@Nullable
			@Override
			public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
			
			View v = inflater.inflate(R.layout.fragment_fav, container, false);
			
			//RecyclerView
			mRecyclerView = v.findViewById(R.id.favfromDB);
			mRecyclerView.setHasFixedSize(true);
			
			fetch();
			
			return v;
		}
			
			private void fetch() {
			
			favoriteRepository.getInstance();
			
			
			}
}
