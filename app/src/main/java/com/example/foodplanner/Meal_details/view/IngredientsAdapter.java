package com.example.foodplanner.Meal_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.example.foodplanner.Meal_details.pojo.IngredientItem;
import com.example.foodplanner.Meal_details.pojo.Ingredients;
import com.example.foodplanner.R;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.IngredientsViewHolder> {
List<Ingredients> ingredients;
Context context;
public IngredientsAdapter(List<Ingredients> ingredients,Context context){
    this.ingredients=ingredients;
    this.context=context;
}
    @NonNull
    @Override
    public IngredientsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
    View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.ingredients_item,parent,false);
        return new IngredientsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull IngredientsViewHolder holder, int position) {
        Ingredients ingredientItems=ingredients.get(position);
    holder.ingredientName.setText(ingredientItems.getName());
    holder.tvIngredientMeasure.setText(ingredientItems.getIngredientMeasure());
        Glide.with(context).load(ingredientItems.getImageUrl()).into(holder.ingredientImage);

    }
    @Override
    public int getItemCount() {
        return ingredients.size();
    }
    public void setList(List<Ingredients> ingredients){
    this.ingredients=ingredients;
    notifyDataSetChanged();
    }

     class IngredientsViewHolder extends RecyclerView.ViewHolder {
    ImageView ingredientImage;
    TextView ingredientName,tvIngredientMeasure;
        public IngredientsViewHolder(@NonNull View itemView) {
            super(itemView);
            ingredientImage=itemView.findViewById(R.id.ingredientImage);
            ingredientName=itemView.findViewById(R.id.tvIngredient);
            tvIngredientMeasure=itemView.findViewById(R.id.tvIngredientMeasure);
        }
    }
}
