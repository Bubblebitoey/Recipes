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
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.view.home.MainActivity;

public class SettingFragment extends Fragment {
	private FirebaseAuth mAuth;
		    private FirebaseUser mUser;
		    private Button signout_btn;
			
			@Nullable
			@Override
			public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
				
				View view  = inflater.inflate(R.layout.fragment_setting, container, false);
				signout_btn = (Button)view.findViewById(R.id.signout_btn);
				mAuth = FirebaseAuth.getInstance();
				mUser = mAuth.getCurrentUser();
				
				signout_btn.setOnClickListener(new View.OnClickListener() {
			            @Override
			            public void onClick(View v) {
			                mAuth.signOut();
			                startActivity(new Intent(getActivity(), MainActivity.class));
			            }
			        });
				return view;
			}
}
