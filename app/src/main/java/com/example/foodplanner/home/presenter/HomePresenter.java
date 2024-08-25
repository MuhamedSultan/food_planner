package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import io.reactivex.rxjava3.core.Single;

public interface HomePresenter {
    void getRandomMeal();
    void getAllMealDetailsById(String id);
    void getAllCategories();
    void getAllCountries();
    void addMealToFavorites(String userId,Meal meal);
    void addMealToFavoritesToFirebase(String userId,Meal meal);
    void deleteMealToFavorites(String userId,Meal meal);
    void getMealsYouMightLike();
    void deleteMealFromFavoritesFromFirebase(String userId,Meal meal);


}
