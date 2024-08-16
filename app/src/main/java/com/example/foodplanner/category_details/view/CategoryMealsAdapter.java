package com.example.foodplanner.category_details.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.airbnb.lottie.L;
import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.category_details.pojo.Meal;

import java.util.List;

public class CategoryMealsAdapter extends RecyclerView.Adapter<CategoryMealsAdapter.CategoryMealViewHolder> {
List<Meal> mealList;
Context context;
 CategoryMealClick categoryMealClick;
public CategoryMealsAdapter(List<Meal> mealList, Context context, CategoryMealClick categoryMealClick){
    this.mealList=mealList;
    this.context=context;
    this.categoryMealClick=categoryMealClick;
}
    @NonNull
    @Override
    public CategoryMealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
     View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.all_category_meal_item,parent,false);
        return new CategoryMealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull CategoryMealViewHolder holder, int position) {
    Meal meal=mealList.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.categoryImage);
        holder.tvCategoryMealName.setText(meal.getStrMeal());
        holder.categoryMealItem.setOnClickListener(v->{
            categoryMealClick.onCategoryMealClick(meal.getidMeal());
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

    class CategoryMealViewHolder extends RecyclerView.ViewHolder {
        ImageView categoryImage;
        TextView tvCategoryMealName;
        CardView categoryMealItem;
        public CategoryMealViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.categoryMealImage);
            tvCategoryMealName=itemView.findViewById(R.id.tvCategoryMealName);
            categoryMealItem=itemView.findViewById(R.id.categoryMealItem);

        }
    }
}
