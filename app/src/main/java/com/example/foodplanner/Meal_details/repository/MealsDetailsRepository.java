package com.example.foodplanner.Meal_details.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.home.pojo.DailyRandomMeal;
import com.example.foodplanner.home.pojo.Meal;

public class MealsDetailsRepository {

    RemoteDataSource remoteDataSource;
    MealsDetailsRepository instance=null;

    public MealsDetailsRepository(RemoteDataSource remoteDataSource){
        this.remoteDataSource=remoteDataSource;
    }

    public MealsDetailsRepository getInstance(RemoteDataSource remoteDataSource){
        if (instance==null){
            instance=new MealsDetailsRepository(remoteDataSource);
        }
        return instance;
    }
    public void getMealsDetailsById(NetworkCallback<DailyRandomMeal> callback, String id){
        remoteDataSource.getMealDetailsById(callback,id);
    }




}
