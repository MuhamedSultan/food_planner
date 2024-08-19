package com.example.foodplanner.home.presenter;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.categories.Category;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.countries.CountryMeal;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.example.foodplanner.home.repository.HomeRepository;
import com.example.foodplanner.home.view.HomeView;

import java.util.List;



public class HomePresenterImpl implements  HomePresenter {
    private final HomeView view;
    private final HomeRepository homeRepository;

    public HomePresenterImpl(HomeView view, HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void getRandomMeal() {
        view.showLoading();
        homeRepository.getDailyMeals(new NetworkCallback<DailyRandomMeal>(){

            @Override
            public void onSuccessResult(DailyRandomMeal result) {
                List<Meal> meals = result.getMeals();
                if (meals != null && !meals.isEmpty()) {
                    view.showDailyRandomMealData(meals);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();
            }
        });

    }

    @Override
    public void getAllCategories() {
        view.showLoading();
        homeRepository.getAllCategories(new NetworkCallback<AllCategories>() {
            @Override
            public void onSuccessResult(AllCategories result) {
               List<Category> categories=result.getCategories();
           if (categories!=null&&!categories.isEmpty()){
               view.showAllCategories(categories);
           }
           view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();
            }
        });
    }

    @Override
    public void getAllCountries() {
        view.showLoading();
        homeRepository.getAllCountries(new NetworkCallback<AllCountries>() {
            @Override
            public void onSuccessResult(AllCountries result) {
                List<CountryMeal> countryMeals=result.getMeals();
                if (countryMeals!=null&&!countryMeals.isEmpty()) {
                    view.showAllCountries(countryMeals);
                }
                view.hideLoading();
                }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();
            }
        });
    }

    @Override
    public void getAllMealDetailsById(String id) {
        view.showLoading();
        homeRepository.getMealsDetailsById(new NetworkCallback<DailyRandomMeal>() {
            @Override
            public void onSuccessResult(DailyRandomMeal result) {
                List<Meal> mealList=result.getMeals();
                if (mealList!=null&&!mealList.isEmpty()) {
                    view.showMealsDetailsById(mealList);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();
            }
        },id);

    }

    @Override
    public void addMealToFavorites(Meal meal) {
        homeRepository.addMealToFavorites(meal);
        view.showMessage("Added Successfully to Favourite");
    }

    @Override
    public void addMealToFavoritesToFirebase(String userId,Meal meal) {
        homeRepository.addMealToFavoritesToFirebase(userId,meal);
    }

    @Override
    public void deleteMealToFavorites(Meal meal) {
        homeRepository.deleteMealToFavorites(meal);
        view.showMessage("Deleted Successfully from Favourite");

    }

    @Override
    public void deleteMealFromFavoritesFromFirebase(String userId, Meal meal) {
        homeRepository.deleteMealFromFavoritesFromFirebase(userId,meal);
    }

}
