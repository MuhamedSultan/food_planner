package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

public interface HomePresenter {
    void getRandomMeal();
    void getAllMealDetailsById(String id);
    void getAllCategories();
    void getAllCountries();
    void addMealToFavorites(Meal meal);

}
