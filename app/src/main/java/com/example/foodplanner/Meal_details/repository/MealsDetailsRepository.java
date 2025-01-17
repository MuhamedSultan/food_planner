package com.example.foodplanner.Meal_details.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.DailyRandomMeal;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class MealsDetailsRepository {

    RemoteDataSource remoteDataSource;
    LocalDataSource localDataSource;
    FirebaseFirestore firestore;
    MealsDetailsRepository instance = null;

    public MealsDetailsRepository(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        this.remoteDataSource = remoteDataSource;
        this.localDataSource = localDataSource;
        firestore = FirebaseFirestore.getInstance();
    }

    public MealsDetailsRepository getInstance(RemoteDataSource remoteDataSource, LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new MealsDetailsRepository(remoteDataSource, localDataSource);
        }
        return instance;
    }

    public void getMealsDetailsById(NetworkCallback<DailyRandomMeal> callback, String id) {
        remoteDataSource.getMealDetailsById(callback, id);
    }

    public void addMealToFavorites(Meal meal, String userId) {
        // Add to Room
        localDataSource.addMealToFavorites(meal);

        // Add to Firebase
        addMealToFavoritesToFirebase(userId, meal);
    }

    public void deleteMealFromFavorites(Meal meal, String userId) {
        // Delete from Room
        localDataSource.deleteMealFromFavourite(meal);

        // Delete from Firebase
        deleteMealFromFavoritesFromFirebase(userId, meal);
    }

    public void addMealToFavoritesToFirebase(String userId, Meal meal) {
        firestore.collection("users").document(userId)
                .collection("favorites").document(meal.getIdMeal())
                .set(meal);
    }

    public void deleteMealFromFavoritesFromFirebase(String userId, Meal meal) {
        firestore.collection("users").document(userId)
                .collection("favorites").document(meal.getIdMeal())
                .delete();
    }
    public void addMealToPlan(String userId,MealPlan meal){
        localDataSource.addMealToPlan(meal);
        firestore.collection("users").document(userId)
                .collection("week_plan").document(meal.getIdMeal())
                .set(meal);
    }

}
