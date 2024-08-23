package com.example.foodplanner.calender.repository;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class PlanRepository {

    LocalDataSource localDataSource;
    PlanRepository instance=null;
    public PlanRepository(LocalDataSource localDataSource){
        this.localDataSource=localDataSource;
    }
    public PlanRepository getInstance(LocalDataSource localDataSource){
        if (instance==null){
            instance=new PlanRepository(localDataSource);
        }
        return instance;
    }

    public Observable<List<Meal>> getMealOfPlan(String mealOfDay){
        return localDataSource.getMealOfPlan(mealOfDay);
    }
}
