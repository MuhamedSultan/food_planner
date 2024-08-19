package com.example.foodplanner.db;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Completable;

@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    Completable addMealToFavourite(Meal meal);

    @Query("SELECT * FROM MEALS_TABLE where userId =:userId")
    LiveData<List<Meal>> getFavouriteMeals(String userId);

    @Delete
    Completable deleteMealFromFavourite(Meal meal);
}
