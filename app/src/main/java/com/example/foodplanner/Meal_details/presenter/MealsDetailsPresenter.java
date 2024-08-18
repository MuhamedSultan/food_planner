package com.example.foodplanner.Meal_details.presenter;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

public interface MealsDetailsPresenter {
    void getAllMealDetailsById(String id);
    void addMealToFavorites(Meal meal);

}
