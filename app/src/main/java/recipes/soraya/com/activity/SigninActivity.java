package recipes.soraya.com.activity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.MenuItem;
import recipes.soraya.com.*;

import java.util.*;

public class SigninActivity extends AppCompatActivity {
	
	private RecyclerView recyclerView;
	private GridLayoutManager gridLayoutManager;
	//private CustomAdapter adapter;
	private List<MyData> dataList;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_signin);
		
		BottomNavigationView bottomNav = findViewById(R.id.bottom_navigation);
		bottomNav.setOnNavigationItemSelectedListener(navListener);
		
		//I added this if statement to keep the selected fragment when rotating the device
		        if (savedInstanceState == null) {
			        getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, new HomeFragment()).commit();
		        }
		        
		        //recyclerView = (RecyclerView)findViewById(R.id.recycler_view);
		        dataList = new ArrayList<>();
		        load_data_from_server(0);
	}
	
	private void load_data_from_server(int id) {
		AsyncTask<Integer, Void, Void> task = new AsyncTask<Integer, Void, Void>() {
			@Override
			protected Void doInBackground(Integer... integers) {
				
				return null;
			}
			
			@Override
			protected void onPostExecute(Void aVoid) {
				super.onPostExecute(aVoid);
			}
		};
		task.execute(id);
	}
	
	private BottomNavigationView.OnNavigationItemSelectedListener navListener =
            new BottomNavigationView.OnNavigationItemSelectedListener() {
                @Override
                public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                    Fragment selectedFragment = null;
 
                    switch (item.getItemId()) {
                        case R.id.nav_home:
                            selectedFragment = new HomeFragment();
                            break;
                        case R.id.nav_fav:
                            selectedFragment = new FavFragment();
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
