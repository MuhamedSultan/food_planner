package com.example.foodplanner.home.repository;

import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.home.pojo.DailyRandomMeal;

import io.reactivex.rxjava3.core.Single;


public class HomeRepository {
    private final RemoteDataSource remoteDataSource;
    private static HomeRepository instance = null;

    public HomeRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static HomeRepository getInstance(RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new HomeRepository(remoteDataSource);
        }
        return instance;
    }


    public Single<DailyRandomMeal> getDailyMeals() {
        return remoteDataSource.getDailyMeals();
    }

}
