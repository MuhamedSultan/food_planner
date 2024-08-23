package com.example.foodplanner.calender.view;

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
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public class PlanAdapter extends RecyclerView.Adapter<PlanAdapter.MealViewHolder> {

    private List<MealPlan> meals;
    private Context context;
    PlanMealClick planMealClick;

    public PlanAdapter(List<MealPlan> meals, Context context,PlanMealClick planMealClick) {
        this.meals = meals;
        this.context = context;
        this.planMealClick=planMealClick;
    }

    @NonNull
    @Override
    public MealViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.plan_item, parent, false);
        return new MealViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MealViewHolder holder, int position) {
        MealPlan meal = meals.get(position);
        Glide.with(context).load(meal.getStrMealThumb()).into(holder.mealImage);
        holder.tvMealName.setText(meal.getStrMeal());
        holder.mealImage.setOnClickListener(v->{
            planMealClick.onClickPlanMeal(meal.getIdMeal());
        });
        holder.deleteFromPlan.setOnClickListener(v->{
            planMealClick.onClickDeleteMeal(meal);
        });
    }

    @Override
    public int getItemCount() {
        return meals.size();
    }

    public static class MealViewHolder extends RecyclerView.ViewHolder {
        ImageView mealImage,deleteFromPlan;
        TextView tvMealName;

        public MealViewHolder(@NonNull View itemView) {
            super(itemView);
            mealImage = itemView.findViewById(R.id.planImage);
            deleteFromPlan = itemView.findViewById(R.id.deleteFromPlan);
            tvMealName = itemView.findViewById(R.id.tvPlan);

        }
    }
}
