package com.example.foodplanner.api;

import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipes;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.ingredients.IngredientsResponse;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {
    @GET("api/json/v1/1/random.php")
    Single<DailyRandomMeal> getDailyMeal();
    @GET("api/json/v1/1/categories.php")
    Single<AllCategories> getAllCategories();
    @GET("api/json/v1/1/filter.php")
    Single<CategoryMeals> getMealsByCategory(@Query("c") String categoryName);
    @GET("api/json/v1/1/lookup.php")
    Single<DailyRandomMeal> getMealDetailsById(@Query("i") String id);
    @GET("api/json/v1/1/list.php?a=list")
    Single<AllCountries> getAllCountries();
    @GET("api/json/v1/1/filter.php")
    Single<CountryRecipes> getCountryRecipes(@Query("a") String CountryName);
    @GET("api/json/v1/1/list.php?i=list")
    Single<IngredientsResponse> getAllIngredients();
    @GET("api/json/v1/1/search.php?s=a")
    Single<DailyRandomMeal> getMealYouMightLike();
}
