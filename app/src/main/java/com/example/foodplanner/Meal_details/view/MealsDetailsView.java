package com.example.foodplanner.Meal_details.view;


import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public interface MealsDetailsView {
    void showMealsDetailsById(List<Meal> mealsDetails);
    void showMessage(String message);
    void showLoading();
    void hideLoading();
}
