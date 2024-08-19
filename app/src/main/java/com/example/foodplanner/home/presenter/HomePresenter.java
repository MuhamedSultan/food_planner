package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

public interface HomePresenter {
    void getRandomMeal();
    void getAllMealDetailsById(String id);
    void getAllCategories();
    void getAllCountries();
    void addMealToFavorites(Meal meal);
    void addMealToFavoritesToFirebase(String userId,Meal meal);
    void deleteMealToFavorites(Meal meal);
    void deleteMealFromFavoritesFromFirebase(String userId,Meal meal);


}
