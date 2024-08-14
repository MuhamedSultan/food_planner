package com.example.foodplanner.home.view;

import com.example.foodplanner.home.pojo.Meal;

import java.util.List;

public interface HomeView {
    void showData(List<Meal> meals);
    void showErrorMessage(String message);
    void showLoading();
    void hideLoading();
}
