package com.example.foodplanner.countries_recipes.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipesMeal;
import com.example.foodplanner.home.view.adapter.CountryClick;

import java.util.List;

public class CountryRecipesAdapter extends RecyclerView.Adapter<CountryRecipesAdapter.CountryRecipesViewHolder> {
    List<CountryRecipesMeal> mealList;
    Context context;
    CountryRecipesMealClick countryClick;

    public CountryRecipesAdapter(List<CountryRecipesMeal> mealList, Context context, CountryRecipesMealClick countryClick) {
        this.mealList = mealList;
        this.context = context;
        this.countryClick = countryClick;
    }

    @NonNull
    @Override
    public CountryRecipesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_meals_item, parent, false);
        return new CountryRecipesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CountryRecipesViewHolder holder, int position) {
        CountryRecipesMeal meal = mealList.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.categoryImage);
        holder.tvCategoryMealName.setText(meal.getStrMeal());
        holder.categoryMealItem.setOnClickListener(v -> {
            countryClick.onCountryMealClick(meal.getidMeal());
        });
    }

    @Override
    public int getItemCount() {
        return mealList.size();
    }

    public void setList(List<CountryRecipesMeal> mealList) {
        this.mealList = mealList;
    }

        class CountryRecipesViewHolder extends RecyclerView.ViewHolder {
            ImageView categoryImage;
            TextView tvCategoryMealName;
            CardView categoryMealItem;

            public CountryRecipesViewHolder(@NonNull View itemView) {
                super(itemView);
                categoryImage = itemView.findViewById(R.id.categoryMealImage);
                tvCategoryMealName = itemView.findViewById(R.id.tvCategoryMealName);
                categoryMealItem = itemView.findViewById(R.id.categoryMealItem);
            }
        }
    }


