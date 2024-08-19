package com.example.foodplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.airbnb.lottie.L;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public interface FavouritePresenter {
     LiveData<List<Meal>> getFavouriteMeals(String userId);
      LiveData<List<Meal>> getFavouriteMealsFromFirebase(String userId);
    void deleteMealFromFavourite(Meal meal);
    void deleteMealFromFavoritesFromFirebase(String userId,Meal meal);

}
