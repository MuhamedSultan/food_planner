package com.example.foodplanner.home.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.home.pojo.AllCategories;
import com.example.foodplanner.home.pojo.DailyRandomMeal;

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


    public void getDailyMeals(NetworkCallback<DailyRandomMeal> callback) {
         remoteDataSource.getDailyMeals(callback);
    }

    public void getAllCategories(NetworkCallback<AllCategories> callback){
        remoteDataSource.getAllCategories(callback);
    }
    public void getMealsDetailsById(NetworkCallback<DailyRandomMeal> callback, String id){
        remoteDataSource.getMealDetailsById(callback,id);
    }

}
