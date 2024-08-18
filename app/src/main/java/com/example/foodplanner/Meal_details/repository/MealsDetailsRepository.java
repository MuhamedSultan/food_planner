package com.example.foodplanner.Meal_details.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

public class MealsDetailsRepository {

    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;
    MealsDetailsRepository instance=null;

    public MealsDetailsRepository(RemoteDataSource remoteDataSource,LocalDataSource localDataSource){
        this.remoteDataSource=remoteDataSource;
        this.localDataSource=localDataSource;
    }

    public MealsDetailsRepository getInstance(RemoteDataSource remoteDataSource,LocalDataSource localDataSource){
        if (instance==null){
            instance=new MealsDetailsRepository(remoteDataSource,localDataSource);
        }
        return instance;
    }
    public void getMealsDetailsById(NetworkCallback<DailyRandomMeal> callback, String id){
        remoteDataSource.getMealDetailsById(callback,id);
    }

    public void addMealToFavorites(Meal meal) {
        localDataSource.addMealToFavorites(meal);
    }
}
