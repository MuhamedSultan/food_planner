package com.example.foodplanner.home.view;

import com.example.foodplanner.home.pojo.Category;
import com.example.foodplanner.home.pojo.Meal;

import java.util.List;

public interface HomeView {
    void showDailyRandomMealData(List<Meal> meals);
    void showAllCategories(List<Category> categories);
    void showMealsDetailsById(List<Meal> mealsDetails);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
}
