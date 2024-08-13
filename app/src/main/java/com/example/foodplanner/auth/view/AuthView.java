package com.example.foodplanner.auth.view;

public interface AuthView {
    void successSignIn();
    void failureSignIn(String message);
    void showLoading();
    void hideLoading();
}
