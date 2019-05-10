/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 5/10/19 4:19 PM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.view.home.fragment.FavoriteFragment;
import com.haerul.foodsapp.view.home.fragment.SettingFragment;

public class FunctionActivity extends AppCompatActivity {
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_function);
		
		BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
		bottomNav.setOnNavigationItemSelectedListener(navListener);
				
		//I added this if statement to keep the selected fragment when rotating the device
		if (savedInstanceState == null) {
			getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new FavoriteFragment()).commit();
		}
	}
	
	private BottomNavigationView.OnNavigationItemSelectedListener navListener =
	            new BottomNavigationView.OnNavigationItemSelectedListener() {
	                @Override
	                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
	                    Fragment selectedFragment = null;
	 
	                    switch (item.getItemId()) {
	                        case R.id.nav_fav:
	                            selectedFragment = new FavoriteFragment();
	                            break;
	                        case R.id.nav_setting:
	                            selectedFragment = new SettingFragment();
	                            break;
	                    }
	                    
	 
	                    getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,
	                            selectedFragment).commit();
	 
	                    return true;
	                }
	            };
}
