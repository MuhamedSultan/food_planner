package com.example.foodplanner.api;

import com.example.foodplanner.home.pojo.AllCategories;
import com.example.foodplanner.home.pojo.DailyRandomMeal;

import io.reactivex.rxjava3.core.Single;
import retrofit2.http.GET;

public interface ApiService {
    @GET("api/json/v1/1/random.php")
    Single<DailyRandomMeal> getDailyMeal();
    @GET("api/json/v1/1/categories.php")
    Single<AllCategories> getAllCategories();
}
