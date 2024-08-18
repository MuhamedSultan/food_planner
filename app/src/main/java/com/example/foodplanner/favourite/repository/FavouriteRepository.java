package com.example.foodplanner.favourite.repository;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

public class FavouriteRepository {

   private LocalDataSource localDataSource;
    private static FavouriteRepository instance = null;

    public FavouriteRepository(LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
    }

    public static FavouriteRepository getInstance(LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new FavouriteRepository(localDataSource);
        }
        return instance;
    }

    public LiveData<List<Meal>> getFavouriteMeals() {
        return localDataSource.getFavouriteMeals();
    }
}
