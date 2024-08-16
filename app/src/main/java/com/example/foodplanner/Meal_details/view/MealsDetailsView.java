package com.example.foodplanner.Meal_details.view;


import com.example.foodplanner.home.pojo.Meal;

import java.util.List;

public interface MealsDetailsView {
    void showMealsDetailsById(List<Meal> mealsDetails);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
}
