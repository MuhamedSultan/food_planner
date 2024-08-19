package com.example.foodplanner.home.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.firebase.firestore.FirebaseFirestore;

public class HomeRepository {
    private final RemoteDataSource remoteDataSource;
    private final LocalDataSource localDataSource;
    private final FirebaseFirestore firestore;
    private static HomeRepository instance = null;

    public HomeRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        firestore = FirebaseFirestore.getInstance();
    }

    public static HomeRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new HomeRepository(remoteDataSource, localDataSource);
        }
        return instance;
    }


    public void getDailyMeals(NetworkCallback<DailyRandomMeal> callback) {
        remoteDataSource.getDailyMeals(callback);
    }

    public void getAllCategories(NetworkCallback<AllCategories> callback) {
        remoteDataSource.getAllCategories(callback);
    }

    public void getMealsDetailsById(NetworkCallback<DailyRandomMeal> callback, String id) {
        remoteDataSource.getMealDetailsById(callback, id);
    }

    public void getAllCountries(NetworkCallback<AllCountries> callback) {
        remoteDataSource.getAllCountries(callback);
    }

    public void addMealToFavorites(Meal meal) {
        localDataSource.addMealToFavorites(meal);
    }

    public void deleteMealToFavorites(Meal meal) {
        localDataSource.deleteMealFromFavourite(meal);
    }


    public void addMealToFavoritesToFirebase(String userId, Meal meal) {
        localDataSource.addMealToFavorites(meal);
        firestore.collection("users").document(userId)
                .collection("favorites").document(meal.getIdMeal())
                .set(meal);
    }

    public void deleteMealFromFavoritesFromFirebase(String userId, Meal meal) {
        localDataSource.addMealToFavorites(meal);
        firestore.collection("users").document(userId)
                .collection("favorites").document(meal.getIdMeal())
                .delete();
    }
}
