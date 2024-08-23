package com.example.foodplanner.calender.view;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

public interface PlanMealClick {
    void onClickPlanMeal(String mealId);
    void onClickDeleteMeal(MealPlan meal);
}
