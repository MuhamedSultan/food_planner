package com.example.foodplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.favourite.repository.FavouriteRepository;
import com.example.foodplanner.favourite.view.FavouriteView;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.firebase.auth.FirebaseAuth;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class FavouritePresenterImpl implements FavouritePresenter {

    FavouriteView view;
    FavouriteRepository repository;

    public FavouritePresenterImpl(FavouriteView view, FavouriteRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public LiveData<List<Meal>> getFavouriteMeals(String userId) {
        return repository.getFavouriteMeals(userId);
    }

    @Override
    public LiveData<List<Meal>> getFavouriteMealsFromFirebase(String userId) {
        return repository.getFavouriteMealsFromFirebase(userId);
    }

    @Override
    public void deleteMealFromFavourite(Meal meal) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        repository.deleteMealFromFavorites(meal, userId);


    }

    @Override
    public void deleteMealFromFavoritesFromFirebase(String userId,Meal meal) {
        repository.deleteMealFromFavoritesFromFirebase(userId,meal);
    }
}
