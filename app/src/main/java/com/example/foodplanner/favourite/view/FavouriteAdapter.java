package com.example.foodplanner.favourite.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public class FavouriteAdapter extends RecyclerView.Adapter<FavouriteAdapter.FavouriteViewHolder> {
   List<Meal> mealList;
   Context context;
   FavouriteClick favouriteClick;
   public FavouriteAdapter(List<Meal> mealList,Context context,FavouriteClick favouriteClick){
       this.mealList=mealList;
       this.context=context;
       this.favouriteClick=favouriteClick;
   }
    @NonNull
    @Override
    public FavouriteViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.favourite_item,parent,false);
        return new FavouriteViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FavouriteViewHolder holder, int position) {
       Meal meal=mealList.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.favouriteImage);
        holder.tvFavourite.setText(meal.getStrMeal());
        holder.favoriteIcon.setOnClickListener(v->{
            favouriteClick.onFavouriteClick(meal);
        });
        holder.favouriteImage.setOnClickListener(v->{
            favouriteClick.onFavouriteItemClick(meal);
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setList(List<Meal> mealList){
       this.mealList=mealList;
       notifyDataSetChanged();
    }
    class FavouriteViewHolder extends RecyclerView.ViewHolder {
        ImageView favouriteImage,favoriteIcon;
        TextView tvFavourite;

        public FavouriteViewHolder(@NonNull View itemView) {
            super(itemView);
            favouriteImage=itemView.findViewById(R.id.favouriteImage);
            tvFavourite=itemView.findViewById(R.id.tvPlan);
            favoriteIcon=itemView.findViewById(R.id.favouriteIcon);
        }
    }
}
