package com.example.foodplanner.db;

import android.content.Context;
import android.content.SharedPreferences;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalDataSource {

    private static final String PREFS_NAME = "FoodPlannerPrefs";
    private static final String KEY_MEAL_ID = "meal_id";
    private static final String KEY_DATE = "meal_date";
    private static final String KEY_EMAIL = "user_email";
    MealsDao mealsDao;
    Context context;
    CompositeDisposable disposable;


    public LocalDataSource(Context context, CompositeDisposable disposable) {
        this.context = context;
        this.disposable = disposable;
        mealsDao = MealsDatabase.getInstance(context).mealsDao();

    }

    public static void saveUser(Context context, String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_EMAIL, email);
        editor.apply();
    }

    public static void clearUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.clear();
        editor.apply();
    }

    public static String getSavedUser(Context context) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_EMAIL, null);
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
        disposable.add(mealsDao.addMealToFavourite(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe()
        );
    }

    public LiveData<List<Meal>> getFavouriteMeals(String userId) {
        return mealsDao.getFavouriteMeals(userId);
    }

    public void deleteMealFromFavourite(Meal meal) {
         mealsDao.deleteMealFromFavourite(meal);
    }

}
