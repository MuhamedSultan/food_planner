package com.example.foodplanner.api;

import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipes;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.ingredients.IngredientMeal;
import com.example.foodplanner.home.pojo.ingredients.IngredientsResponse;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class RemoteDataSource {
    private final MealsClient mealsClient;
    CompositeDisposable disposable = new CompositeDisposable();

    public RemoteDataSource() {
        this.mealsClient = MealsClient.getInstance();
    }

    public void getDailyMeals(NetworkCallback<DailyRandomMeal> callback) {
        disposable.add(mealsClient.getApiService().getDailyMeal()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        dailyRandomMeal -> callback.onSuccessResult(dailyRandomMeal),
                        throwable -> callback.onFailureResult(throwable.getMessage())
                ));
    }

    public void getAllCategories(NetworkCallback<AllCategories> callback) {
        disposable.add(mealsClient.getApiService().getAllCategories()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        allCategories -> {
                            callback.onSuccessResult(allCategories);
                        },
                        throwable -> callback.onFailureResult(throwable.getMessage())
                ));
    }

    public void getMealsByCategory(NetworkCallback<CategoryMeals> callback, String categoryName) {
        disposable.add(mealsClient.getApiService().getMealsByCategory(categoryName).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(allCategoryMeals -> {
                    callback.onSuccessResult(allCategoryMeals);
                }, throwable -> callback.onFailureResult(throwable.getMessage())
        ));
    }

    public void getMealDetailsById(NetworkCallback<DailyRandomMeal> callback, String id) {
        disposable.add(mealsClient.getApiService().getMealDetailsById(id).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(allMealsDetails -> {
                    callback.onSuccessResult(allMealsDetails);
                }, throwable -> callback.onFailureResult(throwable.getMessage())
        ));
    }

    public void getAllCountries(NetworkCallback<AllCountries> callback) {
        disposable.add(mealsClient.getApiService().getAllCountries()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(allCountries -> {
                            callback.onSuccessResult(allCountries);
                        }, throwable -> callback.onFailureResult(throwable.getMessage())
                ));
    }

    public void getCountryRecipes(NetworkCallback<CountryRecipes> callback, String countryName) {
        disposable.add(mealsClient.getApiService().getCountryRecipes(countryName)
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(countryRecipes -> {
                            callback.onSuccessResult(countryRecipes);
                        }, throwable -> callback.onFailureResult(throwable.getMessage())
                ));
    }

    public void getAllIngredients(NetworkCallback<IngredientsResponse> callback) {
        disposable.add(mealsClient.getApiService().getAllIngredients()
                .subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(ingredientsResponse -> callback.onSuccessResult(ingredientsResponse),
                        throwable -> callback.onFailureResult(throwable.getMessage())
                )
        );
    }
}
