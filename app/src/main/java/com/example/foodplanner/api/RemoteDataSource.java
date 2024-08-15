package com.example.foodplanner.api;

import android.annotation.SuppressLint;

import com.example.foodplanner.home.pojo.AllCategories;
import com.example.foodplanner.home.pojo.DailyRandomMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.core.Single;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RemoteDataSource {
    private final MealsClient mealsClient;

    public RemoteDataSource() {
        this.mealsClient = MealsClient.getInstance();
    }

    public void getDailyMeals(NetworkCallback<DailyRandomMeal> callback) {
        mealsClient.getApiService().getDailyMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dailyRandomMeal -> callback.onSuccessResult(dailyRandomMeal),
                        throwable -> callback.onFailureResult(throwable.getMessage())
                );
    }
    public void getAllCategories(NetworkCallback<AllCategories> callback){
        mealsClient.getApiService().getAllCategories().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        allCategories -> {callback.onSuccessResult(allCategories);},
                        throwable -> {callback.onFailureResult(throwable.getMessage());}
                );
    }
}
