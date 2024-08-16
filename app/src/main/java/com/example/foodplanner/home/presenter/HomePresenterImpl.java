package com.example.foodplanner.home.presenter;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.home.pojo.AllCategories;
import com.example.foodplanner.home.pojo.Category;
import com.example.foodplanner.home.pojo.DailyRandomMeal;
import com.example.foodplanner.home.pojo.Meal;
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
    public void addMealToFavourite(Meal meal) {
    }

}
