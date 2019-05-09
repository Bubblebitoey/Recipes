package recipes.soraya.com.fragment;

import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;

import io.reactivex.Observable;
import io.reactivex.subjects.Subject;
import recipes.soraya.com.R;
import recipes.soraya.com.models.Recipe;

public class HomeFragment extends Fragment {


    private Subject<String> searchTextSubject;
    private Observable<String> onSearchTextChanged;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);


        // load JSON recipes
        new DownloadTasks().execute();

        return view;
    }

    private class DownloadTasks extends AsyncTask<Void, Void, Void> {

        private void readRecipesFromJSON() {
            String json = null;
            try {
                InputStream is = getActivity().getAssets().open("recipes.json");
                int size = is.available();
                byte[] buffer = new byte[size];
                is.read(buffer);
                is.close();
                json = new String(buffer, "UTF-8");
                JSONObject jsonObject = new JSONObject(json);
                JSONArray jsonArray = jsonObject.getJSONArray("list");
                


//                HashMap<String,Object> result = new ObjectMapper().readValue(json, HashMap.class);

            } catch (IOException ex) {
                ex.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }

        @Override
        protected Void doInBackground(Void... voids) {
            readRecipesFromJSON();
            return null;
        }
    }
}


