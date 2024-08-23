// DailyRandomMeal.java

// YApi QuickType插件生成，具体参考文档:https://plugins.jetbrains.com/plugin/18847-yapi-quicktype/documentation

package com.example.foodplanner.home.pojo.randomMeal;

import androidx.room.TypeConverters;

import com.example.foodplanner.calender.pojo.MealPlan;

import java.util.List;
public class DailyRandomMeal {
    private List<Meal> meals;

    public List<Meal> getMeals() {
        return meals;
    }

    public void setMeals(List<Meal> value) {
        this.meals = value;
    }
}
