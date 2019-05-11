/*-----------------------------------------------------------------------------
 - Developed by Haerul Muttaqin                                               -
 - Last modified 3/24/19 1:02 PM                                              -
 - Subscribe : https://www.youtube.com/haerulmuttaqin                         -
 - Copyright (c) 2019. All rights reserved                                    -
 -----------------------------------------------------------------------------*/
package com.haerul.foodsapp.view.home.category;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.haerul.foodsapp.R;
import com.haerul.foodsapp.Utils;
import com.haerul.foodsapp.adapter.RecyclerViewMealByCategory;
import com.haerul.foodsapp.model.FavoriteRepository;
import com.haerul.foodsapp.model.Meals;
import com.haerul.foodsapp.view.home.detail.DetailActivity;
import com.squareup.picasso.Picasso;

import java.lang.reflect.Array;
import java.util.*;

import static com.haerul.foodsapp.view.home.HomeActivity.EXTRA_DETAIL;
import static com.haerul.foodsapp.view.home.HomeActivity.EXTRA_POSITION;

public class CategoryFragment extends Fragment implements CategoryView, Observer {

    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    @BindView(R.id.progressBar)
    ProgressBar progressBar;
    @BindView(R.id.imageCategory)
    ImageView imageCategory;
    @BindView(R.id.imageCategoryBg)
    ImageView imageCategoryBg;
    @BindView(R.id.textCategory)
    TextView textCategory;

    AlertDialog.Builder descDialog;

    private FavoriteRepository favoriteRepository;
    private FirebaseAuth mAuth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference databaseReference;


    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_category, container, false);
        ButterKnife.bind(this, view);

        favoriteRepository = FavoriteRepository.getInstance();
        favoriteRepository.addObserver(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        mAuth = FirebaseAuth.getInstance();

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (getArguments() != null) {
            textCategory.setText(getArguments().getString("EXTRA_DATA_DESC"));
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(imageCategory);
            Picasso.get().load(getArguments().getString("EXTRA_DATA_IMAGE")).into(imageCategoryBg);
            descDialog = new AlertDialog.Builder(getActivity()).setTitle(getArguments().getString("EXTRA_DATA_NAME")).setMessage(getArguments().getString("EXTRA_DATA_DESC"));

            CategoryPresenter presenter = new CategoryPresenter(this);
            presenter.getMealByCategory(getArguments().getString("EXTRA_DATA_NAME"));
        }

    }

    @Override
    public void showLoading() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void setMeals(List<Meals.Meal> meals) {
        RecyclerViewMealByCategory adapter = new RecyclerViewMealByCategory(getActivity(), meals);
        recyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        recyclerView.setClipToPadding(false);
        recyclerView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

        adapter.setOnItemClickListener((view, position) -> {
            //TODO #8.2 make an intent to DetailActivity (get the name of the meal from the edit text view, then send the name of the meal to DetailActivity)
            TextView mealName = view.findViewById(R.id.mealName);
            Intent intent = new Intent(getActivity(), DetailActivity.class);
            intent.putExtra(EXTRA_DETAIL, mealName.getText().toString());

            intent.putExtra(EXTRA_POSITION, position);

            startActivity(intent);
        });
    }

    @Override
    public void onErrorLoading(String message) {
        Utils.showDialogMessage(getActivity(), "Error ", message);
    }

    @OnClick(R.id.cardCategory)
    public void onClick() {
        descDialog.setPositiveButton("CLOSE", (dialog, which) -> dialog.dismiss());
        descDialog.show();
    }

    @Override
    public void update(Observable observable, Object o) {
        String[] array = o.toString().split(" ");
        int index = Integer.parseInt(array[1]);
        if (array[0].equals("add")) {
            databaseReference
                    .child(mAuth.getCurrentUser().getUid())
                    .child("favorite")
                    .child(FavoriteRepository.getInstance().getFavoriteList().get(index).getIdMeal())
                    .setValue(FavoriteRepository.getInstance().getFavoriteList().get(index));

        } else if (array[0].equals("remove")) {
            databaseReference.child(mAuth.getCurrentUser().getUid())
                    .child("favorite")
                    .child(index + "")
                    .removeValue();
        }
    }
}