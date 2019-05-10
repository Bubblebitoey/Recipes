package com.haerul.foodsapp.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
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

public class RecyclerViewFavorite extends RecyclerView.Adapter<RecyclerViewFavorite.RecyclerViewHolder> {
	
   
	private static ClickListener clickListener;
	private FavoriteRepository favoriteRepository = FavoriteRepository.getInstance();
	private Context context;
	private View view;
	
	
	@NonNull
	@Override
	public RecyclerViewFavorite.RecyclerViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
		view = LayoutInflater.from(context).inflate(R.layout.item_recycler_meal,
				
				viewGroup, false);
		
		return new RecyclerViewHolder(view);
	}
	
	@Override
	public void onBindViewHolder(@NonNull RecyclerViewFavorite.RecyclerViewHolder recyclerViewHolder, int position) {
		favoriteRepository.getFavoriteList().get(position);
		
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
		return 0;
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
		
		public void setOnItemListener(ClickListener clickListener) {
			RecyclerViewFavorite.clickListener = clickListener;
		}
		
		public interface ClickListener {
			void onClick(View v, int position);
		}
		
		public void showMessage(String message) {
			Toast.makeText(this.context.getApplicationContext(), message, Toast.LENGTH_SHORT).show();
		}
		
	
}
