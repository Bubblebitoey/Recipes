package recipes.soraya.com.fragment;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import io.reactivex.Observable;
import io.reactivex.subjects.Subject;
import recipes.soraya.com.R;

public class HomeFragment extends Fragment {
	
	
	private Subject<String> searchTextSubject;
	private Observable<String> onSearchTextChanged;
	
	@Nullable
	@Override
	public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
		View view  = inflater.inflate(R.layout.fragment_home, container, false);
		
		return view;
	}
    }
