/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/24/19 12:55 PM                                             -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.adapter.ViewPagerCategoryAdapter;
import com.haerul.foodsapp.model.Categories;
import com.haerul.foodsapp.view.home.HomeActivity;
import android.support.v7.widget.Toolbar;

import java.util.*;

public class CategoryActivity extends AppCompatActivity {

    //TODO 4. Annotate fields with @BindView and a view ID
	@BindView(R.id.toolbar)
    Toolbar toolbar;
	@BindView(R.id.tabLayout)
	TabLayout tabLayout;
	@BindView(R.id.viewPager)
	ViewPager viewPager;
    
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_category);
        //TODO 5. Bind ButterKnife
	    ButterKnife.bind(this);
	    
	    

        initActionBar();
        
        //TODO 9. Init getIntent() data from home activity
	    initActionBar();
	    initIntent();
     
     
    }
	
	private void initIntent() {
		Intent intent = getIntent();
		List<Categories.Category> categories = (List<Categories.Category>) intent.getSerializableExtra(HomeActivity.EXTRA_CATEGORY);
		
		int position = intent.getIntExtra(HomeActivity.EXTRA_POSITION, 0);
		
		//TODO 11. Declare fragment viewPager adapter
		ViewPagerCategoryAdapter adapter = new ViewPagerCategoryAdapter(getSupportFragmentManager(), categories);
		viewPager.setAdapter(adapter);
		tabLayout.setupWithViewPager(viewPager);
		viewPager.setCurrentItem(position, true);
		adapter.notifyDataSetChanged();
		
	}
	
	private void initActionBar() {
    	setSupportActionBar(toolbar);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
    }
    
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                break;
        }
        return true;
    }
}