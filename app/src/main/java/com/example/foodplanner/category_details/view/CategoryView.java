package com.example.foodplanner.category_details.view;

import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.category_details.pojo.Meal;

import java.util.List;

public interface CategoryView {
    void showCategoryMealsList(List<Meal> categoryMealsList);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
}
