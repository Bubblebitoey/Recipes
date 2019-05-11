package com.haerul.foodsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.haerul.foodsapp.R;
import com.haerul.foodsapp.model.FavoriteRepository;
import com.haerul.foodsapp.model.Meals;
import com.squareup.picasso.Picasso;

import java.util.*;

public class RecyclerViewFavoriteAdapter extends RecyclerView.Adapter<RecyclerViewFavoriteAdapter.RecyclerViewHolder> {


    private static ClickListener clickListener;
    private FavoriteRepository favoriteRepository;
    private Context context;
    private View view;

    public RecyclerViewFavoriteAdapter(Context context) {
        this.context = context;
        this.favoriteRepository = FavoriteRepository.getInstance();
    }


    @NonNull
    @Override
    public RecyclerViewFavoriteAdapter.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        view = LayoutInflater.from(context).inflate(R.layout.fragment_fav_recycler_meal, viewGroup, false);
        return new RecyclerViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerViewFavoriteAdapter.RecyclerViewHolder recyclerViewHolder, int position) {
        Log.e("TEST_FAV", "" + position);
        if(favoriteRepository.getFavoriteList().size() == 0) { return;}
        //TODO: bind favList into viewHolder
        String strMealThumb = favoriteRepository.getFavoriteList().get(position).getStrMealThumb();
        Picasso.get().load(strMealThumb).placeholder(R.drawable.shadow_bottom_to_top).into(recyclerViewHolder.mealThumb);

        String strMealName = favoriteRepository.getFavoriteList().get(position).getStrMeal();
        recyclerViewHolder.mealName.setText(strMealName);

        view.findViewById(R.id.delete).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                favoriteRepository.removeFromFavoriteList(favoriteRepository.getFavoriteList().get(position));
                showMessage("Sucessfully deleted from favorite.");
            }
        });
    }

    @Override
    public int getItemCount() {
        return favoriteRepository.getFavoriteList().size();
    }


    public void setOnItemListener(ClickListener clickListener) {
        RecyclerViewFavoriteAdapter.clickListener = clickListener;
    }

    public interface ClickListener {
        void onClick(View v, int position);
    }

    public void showMessage(String message) {
        Toast.makeText(this.context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }

    static class RecyclerViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.mealThumb)
        ImageView mealThumb;
        @BindView(R.id.mealName)
        TextView mealName;

        RecyclerViewHolder(@NonNull View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            clickListener.onClick(v, getAdapterPosition());
        }

    }
}
