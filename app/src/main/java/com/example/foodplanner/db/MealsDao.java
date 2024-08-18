package com.example.foodplanner.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;

import com.example.foodplanner.home.pojo.randomMeal.Meal;
@Dao
public interface MealsDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void addMealToFavourite(Meal meal);
}
