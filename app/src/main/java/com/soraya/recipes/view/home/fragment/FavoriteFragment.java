package com.soraya.recipes.view.home.fragment;

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
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

import com.soraya.foodsapp.R;
import com.soraya.recipes.adapter.RecyclerViewFavoriteAdapter;
import com.soraya.recipes.model.FavoriteRepository;
import com.soraya.recipes.view.home.detail.DetailActivity;

import java.util.*;

import static com.soraya.recipes.view.home.HomeActivity.EXTRA_DETAIL;

public class FavoriteFragment extends Fragment implements Observer {


    @BindView(R.id.favfromDB)
    RecyclerView recyclerView;
    @BindView(R.id.textWelcomeUser)
    TextView textWelcomeUser;


    FavoriteRepository favoriteRepository = FavoriteRepository.getInstance();
    public static final String EXTRA_POSITION = "position";


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_fav_menucard, container, false);
        ButterKnife.bind(this, view);

        favoriteRepository.addObserver(this);

        loadFavoriteList();

        return view;
    }

    public void loadFavoriteList() {
        RecyclerViewFavoriteAdapter adapter = new RecyclerViewFavoriteAdapter(getActivity());
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();


        adapter.setOnItemListener(new RecyclerViewFavoriteAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, favoriteRepository.getFavoriteList().get(position).getStrMeal());

                intent.putExtra(EXTRA_POSITION, position);

                startActivity(intent);
            }
        });
    }


    public void updateAdapter() {
        RecyclerViewFavoriteAdapter adapter = new RecyclerViewFavoriteAdapter(getActivity());
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();
        adapter.setOnItemListener(new RecyclerViewFavoriteAdapter.ClickListener() {
            @Override
            public void onClick(View v, int position) {
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra(EXTRA_DETAIL, favoriteRepository.getFavoriteList().get(position).getStrMeal());

                intent.putExtra(EXTRA_POSITION, position);

                startActivity(intent);
            }
        });
    }


    @Override
    public void update(Observable observable, Object o) {
        String[] array = o.toString().split(" ");

        if (array[0].equals("remove")) {
            updateAdapter();
        }
    }
}
