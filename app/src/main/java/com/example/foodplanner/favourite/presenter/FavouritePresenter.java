package com.example.foodplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.airbnb.lottie.L;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public interface FavouritePresenter {
     LiveData<List<Meal>> getFavouriteMeals();
      LiveData<List<Meal>> getFavouriteMealsFromFirebase(String userId);
     }
