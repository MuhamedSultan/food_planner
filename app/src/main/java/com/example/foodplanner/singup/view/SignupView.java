package com.example.foodplanner.singup.view;

public interface SignupView {
    void successfulSignup();

    void failureSignup(String message);

    void showLoading();

    void hideLoading();
}
