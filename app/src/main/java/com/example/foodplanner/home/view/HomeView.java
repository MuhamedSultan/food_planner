package com.example.foodplanner.home.view;

import com.example.foodplanner.home.pojo.categories.Category;
import com.example.foodplanner.home.pojo.countries.CountryMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public interface HomeView {
    void showDailyRandomMealData(List<Meal> meals);
    void showAllCategories(List<Category> categories);
    void showMealsDetailsById(List<Meal> mealsDetails);
    void showAllCountries(List<CountryMeal> countryMeals);
    void showMealsYouMightLike(List<Meal> meals);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
    void showMessage(String message);
}
