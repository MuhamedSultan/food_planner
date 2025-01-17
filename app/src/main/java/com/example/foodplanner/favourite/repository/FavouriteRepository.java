package com.example.foodplanner.favourite.repository;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.rxjava3.core.Completable;

public class FavouriteRepository {

   private LocalDataSource localDataSource;
   FirebaseFirestore firestore;
    private static FavouriteRepository instance = null;

    public FavouriteRepository(LocalDataSource localDataSource) {
        this.localDataSource = localDataSource;
        firestore=FirebaseFirestore.getInstance();
    }

    public static FavouriteRepository getInstance(LocalDataSource localDataSource) {
        if (instance == null) {
            instance = new FavouriteRepository(localDataSource);
        }
        return instance;
    }

    public LiveData<List<Meal>> getFavouriteMeals(String userId) {
        return localDataSource.getFavouriteMeals(userId);
    }

    public LiveData<List<Meal>> getFavouriteMealsFromFirebase(String userId) {
        MutableLiveData<List<Meal>> liveData = new MutableLiveData<>();
        firestore.collection("users")
                .document(userId)
                .collection("favorites")
                .get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.getResult() != null) {
                        List<Meal> mealList = new ArrayList<>();
                        QuerySnapshot querySnapshot = task.getResult();
                        for (QueryDocumentSnapshot document : querySnapshot) {
                            Meal meal = document.toObject(Meal.class);
                            mealList.add(meal);
                        }
                        liveData.setValue(mealList);
                    } else {
                        Log.e("FavouriteRepository", "Error getting documents: ", task.getException());
                        liveData.setValue(new ArrayList<>());
                    }
                });
        return liveData;
    }

    public void addMealToFavorites(Meal meal) {
        localDataSource.addMealToFavorites(meal);
    }

    public void deleteMealFromFavorites( Meal meal,String userId) {

         localDataSource.deleteMealFromFavourite(meal);
         deleteMealFromFavoritesFromFirebase(userId,meal);
    }
    public void deleteMealFromFavoritesFromFirebase(String userId,Meal meal) {
        firestore.collection("users").document(userId)
                .collection("favorites").document(meal.getIdMeal())
                .delete();
    }

}
