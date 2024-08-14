package com.example.foodplanner.Meal_details.pojo;

import com.example.foodplanner.home.pojo.Meal;

import java.util.ArrayList;
import java.util.List;

public class IngredientItem {
    public List<Ingredients> getIngredients(Meal meal) {
        List<Ingredients> ingredients = new ArrayList<>();
        addIngredient(ingredients, meal.getStrIngredient1());
        addIngredient(ingredients, meal.getStrIngredient2());
        addIngredient(ingredients, meal.getStrIngredient3());
        addIngredient(ingredients, meal.getStrIngredient4());
        addIngredient(ingredients, meal.getStrIngredient5());
        addIngredient(ingredients, meal.getStrIngredient6());
        addIngredient(ingredients, meal.getStrIngredient7());
        addIngredient(ingredients, meal.getStrIngredient8());
        addIngredient(ingredients, meal.getStrIngredient9());
        addIngredient(ingredients, meal.getStrIngredient10());
        addIngredient(ingredients, meal.getStrIngredient11());
        addIngredient(ingredients, meal.getStrIngredient12());
        addIngredient(ingredients, meal.getStrIngredient13());
        addIngredient(ingredients, meal.getStrIngredient14());
        addIngredient(ingredients, meal.getStrIngredient15());
        return ingredients;
    }

    private void addIngredient(List<Ingredients> ingredients, String name) {
        if (name != null && !name.isEmpty()) {
            ingredients.add(new Ingredients(name));
        }
    }


}
