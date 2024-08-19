package com.example.foodplanner.Meal_details.presenter;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

public interface MealsDetailsPresenter {
    void getAllMealDetailsById(String id);

    void addMealToFavorites(Meal meal);

    void deleteMealToFavorites(Meal meal);

    void addMealToFavoritesToFirebase(String userId, Meal meal);

    void deleteMealFromFavoritesFromFirebase(String userId, Meal meal);


}
