package com.example.foodplanner.favourite.presenter;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.favourite.repository.FavouriteRepository;
import com.example.foodplanner.favourite.view.FavouriteView;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public class FavouritePresenterImpl implements FavouritePresenter{

    FavouriteView view;
    FavouriteRepository repository;

    public FavouritePresenterImpl(FavouriteView view,FavouriteRepository repository){
        this.view=view;
        this.repository=repository;
    }

    @Override
    public LiveData<List<Meal>> getFavouriteMeals(String userId) {
       return repository.getFavouriteMeals(userId);
    }

    @Override
    public LiveData<List<Meal>> getFavouriteMealsFromFirebase(String userId) {
        return repository.getFavouriteMealsFromFirebase(userId);
    }
}
