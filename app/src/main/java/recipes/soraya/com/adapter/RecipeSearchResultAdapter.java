package recipes.soraya.com.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import recipes.soraya.com.R;
import recipes.soraya.com.model.RecipePreview;

public class RecipeSearchResultAdapter extends ArrayAdapter<RecipePreview> {
	
    public RecipeSearchResultAdapter(@NonNull Context context) {
        super(context, R.layout.search_result);
    }
	

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.search_result, parent, false);
        }
        TextView itemTitle = convertView.findViewById(R.id.searchResultTitle);
        RecipePreview recipe = getItem(position);
        itemTitle.setText(recipe.getTitle());
        return convertView;
    }
	
}
