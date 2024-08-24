package com.example.foodplanner.db;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.core.Scheduler;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.internal.operators.completable.CompletableSubscribeOn;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class LocalDataSource {

    private static final String PREFS_NAME = "FoodPlannerPrefs";
    private static final String KEY_MEAL_ID = "meal_id";
    private static final String KEY_DATE = "meal_date";
    private static final String KEY_EMAIL = "user_email";
    private static final String KEY_FAVORITES = "favorites";
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

    public static void setMealFavoriteStatus(Context context, String mealId, boolean isFavorite) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(KEY_FAVORITES + "_" + mealId, isFavorite);
        editor.apply();
    }

    public static boolean isMealFavorite(Context context, String mealId) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        boolean isFavorite = prefs.getBoolean(KEY_FAVORITES + "_" + mealId, false);
        Log.d("LocalDataSource", "Meal ID: " + mealId + " isFavorite: " + isFavorite);
        return isFavorite;
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
        disposable.add(mealsDao.deleteMealFromFavourite(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public void addMealToPlan(MealPlan meal) {
        disposable.add(mealsDao.addMealToPlan(meal).subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe());
    }

    public Observable<List<MealPlan>> getMealOfPlan(String userId,String mealOfDay) {
        return mealsDao.getMealOfPlan(userId,mealOfDay);
    }

   public Completable deleteMealFromPlan(MealPlan mealPlan){
        return mealsDao.deleteMealFromPlan(mealPlan);
    }

}
