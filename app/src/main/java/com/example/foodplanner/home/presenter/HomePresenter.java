package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.pojo.Meal;

public interface HomePresenter {
    void getRandomMeal();
    void getAllMealDetailsById(String id);
    void getAllCategories();
    void addMealToFavourite(Meal meal);

}
