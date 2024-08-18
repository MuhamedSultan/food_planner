package com.example.foodplanner.db;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.foodplanner.home.pojo.randomMeal.Meal;


@Database(entities = {Meal.class},version = 2)
public abstract class MealsDatabase extends RoomDatabase {

    public static MealsDatabase instance=null;
    public abstract MealsDao mealsDao();

    public static synchronized MealsDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(), MealsDatabase.class, "meals_database")
                    .fallbackToDestructiveMigration().build();
        }
        return instance;
    }
}

