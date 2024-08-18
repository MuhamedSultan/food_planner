package com.example.foodplanner.db;

import android.content.Context;
import android.content.SharedPreferences;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class LocalDataSource {

    private static final String PREFS_NAME = "FoodPlannerPrefs";
    private static final String KEY_MEAL_ID = "meal_id";
    private static final String KEY_DATE = "meal_date";
    MealsDao mealsDao;
    Context context;
    public LocalDataSource(Context context){
        this.context=context;
        mealsDao=MealsDatabase.getInstance(context).mealsDao();

    }
    public static void saveMealId(Context context, String mealId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_MEAL_ID, mealId);
        editor.putString(KEY_DATE, new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date()));
        editor.apply();
    }

    public static String getSavedMealId(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_MEAL_ID, null);
    }

    public static String getSavedDate(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_DATE, null);
    }

    public void addMealToFavorites(Meal meal) {
        new Thread(() -> mealsDao.addMealToFavourite(meal)).start(); // Run on a background thread to avoid blocking the UI
    }

}
