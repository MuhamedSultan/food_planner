package com.example.foodplanner.api;

import com.example.foodplanner.home.pojo.DailyRandomMeal;

import io.reactivex.rxjava3.core.Single;


public class RemoteDataSource {
    private final MealsClient mealsClient;

    public RemoteDataSource() {
        this.mealsClient = MealsClient.getInstance();
    }

    public Single<DailyRandomMeal> getDailyMeals() {
        return mealsClient.getApiService().getDailyMeal();
    }
}
