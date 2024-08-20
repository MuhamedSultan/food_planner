package com.example.foodplanner.search.view;

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
import com.example.foodplanner.home.pojo.ingredients.IngredientMeal;

import java.util.List;

public class IngredientsAdapter extends RecyclerView.Adapter<IngredientsAdapter.SearchViewHolder> {
    List<IngredientMeal> ingredientMeal;
    Context context;

    public IngredientsAdapter(List<IngredientMeal> ingredientMeal, Context context) {
        this.ingredientMeal = ingredientMeal;
        this.context = context;
    }



    @NonNull
    @Override
    public SearchViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        return new SearchViewHolder(inflater.inflate(R.layout.search_item, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SearchViewHolder holder, int position) {
      IngredientMeal meal=ingredientMeal.get(position);
        Glide.with(context).load(meal.getStrThumb()).into(holder.searchImage);
        holder.tvSearch.setText(meal.getStrIngredient());
    }

    @Override
    public int getItemCount() {
        return ingredientMeal.size();
    }
    public void setList(List<IngredientMeal> ingredientMeal) {
        this.ingredientMeal = ingredientMeal;
        notifyDataSetChanged();
    }
    public class SearchViewHolder extends RecyclerView.ViewHolder {
        ImageView searchImage;
        TextView tvSearch;
        public SearchViewHolder(@NonNull View itemView) {
            super(itemView);
            searchImage=itemView.findViewById(R.id.searchImage);
            tvSearch=itemView.findViewById(R.id.tvSearch);

        }
    }
}
