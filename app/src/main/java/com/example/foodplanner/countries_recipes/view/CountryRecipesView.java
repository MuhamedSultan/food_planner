package com.example.foodplanner.countries_recipes.view;

import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipesMeal;

import java.util.List;

public interface CountryRecipesView {
    void showCountryRecipesList(List<CountryRecipesMeal> countryRecipesMeals);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
}
