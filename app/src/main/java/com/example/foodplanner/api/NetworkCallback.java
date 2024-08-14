package com.example.foodplanner.api;


public interface NetworkCallback<T> {
    void onSuccessResult(T result);
    void onFailureResult(String message);
}
