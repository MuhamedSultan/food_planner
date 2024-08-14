package com.example.foodplanner.home.presenter;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.home.pojo.DailyRandomMeal;
import com.example.foodplanner.home.pojo.Meal;
import com.example.foodplanner.home.repository.HomeRepository;
import com.example.foodplanner.home.view.HomeView;

import java.util.List;



public class HomePresenterImpl implements NetworkCallback<DailyRandomMeal>, HomePresenter {
    private final HomeView view;
    private final HomeRepository homeRepository;

    public HomePresenterImpl(HomeView view, HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void getRandomMeal() {
        view.showLoading();
        homeRepository.getDailyMeals(this);

    }

    @Override
    public void addMealToFavourite(Meal meal) {
    }



    @Override
    public void onSuccessResult(DailyRandomMeal result) {
        List<Meal> meals = result.getMeals();
        if (meals != null && !meals.isEmpty()) {
            view.showData(meals);
        }
        view.hideLoading();
    }
    @Override
    public void onFailureResult(String message) {
        view.showErrorMessage(message);
        view.hideLoading();
    }
}
