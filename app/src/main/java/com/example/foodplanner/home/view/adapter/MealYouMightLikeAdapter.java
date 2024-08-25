package com.example.foodplanner.home.view.adapter;

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


public class MealYouMightLikeAdapter extends RecyclerView.Adapter<MealYouMightLikeAdapter.MealYouMightLikeViewHolder> {
    List<Meal> meals;
    Context context;
    MealMightLike mealMightLike;

    public MealYouMightLikeAdapter(List<Meal> meals, Context context,MealMightLike mealMightLike) {
        this.meals = meals;
        this.context = context;
        this.mealMightLike = mealMightLike;
    }



    @NonNull
    @Override
    public MealYouMightLikeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new MealYouMightLikeViewHolder(inflater.inflate(R.layout.meals_you_might_like_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull MealYouMightLikeViewHolder holder, int position) {
        Meal meal=meals.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.youMightLike);
        holder.tvYouMightLikeImage.setText(meal.getStrMeal());
        holder.tvCountry.setText(meal.getStrArea()+" Meal");
        holder.youMightLike.setOnClickListener(v->{
            mealMightLike.onClickMealsYouMightLike(meal);
        });
    }

    public void setList(List<Meal> meals) {
        this.meals = meals;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public class MealYouMightLikeViewHolder extends RecyclerView.ViewHolder {
        ImageView youMightLike;
        TextView tvYouMightLikeImage,tvCountry;

        public MealYouMightLikeViewHolder(@NonNull View itemView) {
            super(itemView);
            youMightLike = itemView.findViewById(R.id.youMightLike);
            tvYouMightLikeImage = itemView.findViewById(R.id.tvName);
            tvCountry=itemView.findViewById(R.id.tvCountry);
        }
    }
}
