package com.example.foodplanner.search.view;

import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.categories.Category;
import com.example.foodplanner.home.pojo.countries.CountryMeal;
import com.example.foodplanner.home.pojo.ingredients.IngredientMeal;

import java.util.List;

public interface SearchView {
            void showAllCountries(List<CountryMeal> countryMeals);
    void showAllCategories(List<Category> categoryMeals);
    void showAllIngredients(List<IngredientMeal> ingredientMeals);
    void showMessage(String message);
    void showLoading();
    void hideLoading();
}
