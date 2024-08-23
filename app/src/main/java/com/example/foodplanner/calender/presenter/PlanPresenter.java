package com.example.foodplanner.calender.presenter;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface PlanPresenter {

    Observable<List<Meal>> getMealOfPlan(String mealOfDay);
}
