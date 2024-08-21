package com.example.foodplanner.Meal_details.pojo;

import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.ArrayList;
import java.util.List;

public class IngredientItem {
    public List<Ingredients> getIngredients(Meal meal) {
        List<Ingredients> ingredients = new ArrayList<>();
        addIngredient(ingredients, meal.getStrIngredient1(),meal.getStrMeasure1());
        addIngredient(ingredients, meal.getStrIngredient2(),meal.getStrMeasure2());
        addIngredient(ingredients, meal.getStrIngredient3(),meal.getStrMeasure3());
        addIngredient(ingredients, meal.getStrIngredient4(),meal.getStrMeasure4());
        addIngredient(ingredients, meal.getStrIngredient5(),meal.getStrMeasure5());
        addIngredient(ingredients, meal.getStrIngredient6(),meal.getStrMeasure6());
        addIngredient(ingredients, meal.getStrIngredient7(),meal.getStrMeasure7());
        addIngredient(ingredients, meal.getStrIngredient8(),meal.getStrMeasure8());
        addIngredient(ingredients, meal.getStrIngredient9(),meal.getStrMeasure9());
        addIngredient(ingredients, meal.getStrIngredient10(),meal.getStrMeasure10());
        addIngredient(ingredients, meal.getStrIngredient11(),meal.getStrMeasure11());
        addIngredient(ingredients, meal.getStrIngredient12(),meal.getStrMeasure12());
        addIngredient(ingredients, meal.getStrIngredient13(),meal.getStrMeasure13());
        addIngredient(ingredients, meal.getStrIngredient14(),meal.getStrMeasure14());
        addIngredient(ingredients, meal.getStrIngredient15(),meal.getStrMeasure15());
        return ingredients;
    }

    private void addIngredient(List<Ingredients> ingredients, String ingredientsName,String ingredientsMeasure) {
        if (ingredientsName != null && !ingredientsName.isEmpty()) {
            ingredients.add(new Ingredients(ingredientsName,ingredientsMeasure));
        }
    }


}
