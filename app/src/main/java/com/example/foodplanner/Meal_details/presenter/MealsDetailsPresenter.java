package com.example.foodplanner.Meal_details.presenter;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface MealsDetailsPresenter {
    void getAllMealDetailsById(String id);

    void addMealToFavorites(Meal meal);

    void deleteMealToFavorites(Meal meal);

    void addMealToFavoritesToFirebase(String userId, Meal meal);

    void deleteMealFromFavoritesFromFirebase(String userId, Meal meal);

    void addMealToPlan(MealPlan meal);




}
