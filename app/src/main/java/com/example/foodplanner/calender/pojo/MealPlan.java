package com.example.foodplanner.calender.pojo;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "plan_tables")
public class MealPlan {
    private String strCategory;

    private String strArea;
    @PrimaryKey
    @NonNull
    private String idMeal;

    private String strMealThumb;

    private String strMeal;
    public boolean isFavourite=false;
    private String userId;
    private String dayOfMeal;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }



    public String getStrArea() { return strArea; }
    public void setStrArea(String value) { this.strArea = value; }

    public String getIdMeal() { return idMeal; }
    public void setIdMeal(String value) { this.idMeal = value; }




    public String getStrMealThumb() { return strMealThumb; }
    public void setStrMealThumb(String value) { this.strMealThumb = value; }


    public String getStrMeal() { return strMeal; }
    public void setStrMeal(String value) { this.strMeal = value; }



    public String getDayOfMeal() {
        return dayOfMeal;
    }

    public void setDayOfMeal(String dayOfMeal) {
        this.dayOfMeal = dayOfMeal;
    }
}

