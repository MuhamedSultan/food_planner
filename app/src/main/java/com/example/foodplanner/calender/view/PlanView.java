package com.example.foodplanner.calender.view;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public interface PlanView {
    void showMessage(String message);
    void showLoading();
    void hideLoading();
}
