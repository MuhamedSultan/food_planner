package com.example.foodplanner.api;

import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
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

    public void getAllCategories(NetworkCallback<AllCategories> callback) {
        mealsClient.getApiService().getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        allCategories -> {
                            callback.onSuccessResult(allCategories);
                        },
                        throwable -> callback.onFailureResult(throwable.getMessage())
                );
    }

    public void getMealsByCategory(NetworkCallback<CategoryMeals> callback, String categoryName) {
        mealsClient.getApiService().getMealsByCategory(categoryName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(allCategoryMeals -> {
                    callback.onSuccessResult(allCategoryMeals);
                }, throwable -> callback.onFailureResult(throwable.getMessage())
        );
    }

    public void getMealDetailsById(NetworkCallback<DailyRandomMeal> callback, String id) {
        mealsClient.getApiService().getMealDetailsById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(allMealsDetails -> {
                    callback.onSuccessResult(allMealsDetails);
                }, throwable -> callback.onFailureResult(throwable.getMessage())
        );
    }

    public void getAllCountries(NetworkCallback<AllCountries> callback) {
        mealsClient.getApiService().getAllCountries()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(allCountries -> {
                    callback.onSuccessResult(allCountries);
                }, throwable -> callback.onFailureResult(throwable.getMessage())
        );
    }
}
