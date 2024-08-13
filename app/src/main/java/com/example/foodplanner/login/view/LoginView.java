package com.example.foodplanner.login.view;

public interface LoginView {
    void successfulLogin();

    void failureLogin(String message);

    void showLoading();

    void hideLoading();
}
