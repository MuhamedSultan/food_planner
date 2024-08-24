package com.example.foodplanner.Meal_details.presenter;

import com.example.foodplanner.Meal_details.repository.MealsDetailsRepository;
import com.example.foodplanner.Meal_details.view.MealsDetailsView;
import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealsDetailsPresenterImpl implements MealsDetailsPresenter {

    private MealsDetailsView view;
    private MealsDetailsRepository repository;

    public MealsDetailsPresenterImpl(MealsDetailsView view, MealsDetailsRepository repository) {
        this.view = view;
        this.repository = repository;
    }


    @Override
    public void getAllMealDetailsById(String id) {
        view.showLoading();
        repository.getMealsDetailsById(new NetworkCallback<DailyRandomMeal>() {
            @Override
            public void onSuccessResult(DailyRandomMeal result) {
                List<Meal> mealList = result.getMeals();
                if (mealList != null && !mealList.isEmpty()) {
                    view.showMealsDetailsById(mealList);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showMessage(message);
                view.hideLoading();
            }
        }, id);

    }


    @Override
    public void addMealToFavorites(Meal meal) {
        repository.addMealToFavorites(meal);
        view.showMessage("Added Successfully To Favourite");
    }

    @Override
    public void deleteMealToFavorites(Meal meal) {
        repository.deleteMealToFavorites(meal);
        view.showMessage("Deleted Successfully from Favourite");

    }

    @Override
    public void addMealToFavoritesToFirebase(String userId, Meal meal) {
        repository.addMealToFavoritesToFirebase(userId, meal);
    }

    @Override
    public void deleteMealFromFavoritesFromFirebase(String userId, Meal meal) {
        repository.deleteMealFromFavoritesFromFirebase(userId, meal);

    }

    @Override
    public void addMealToPlan(String userId, MealPlan meal) {
        repository.addMealToPlan(userId,meal);
    }

}
