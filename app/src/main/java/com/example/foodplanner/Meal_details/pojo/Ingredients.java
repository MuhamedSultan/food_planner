package com.example.foodplanner.Meal_details.pojo;

public class Ingredients {
    private String name;
    private String imageUrl;
    String ingredientMeasure;

    public Ingredients(String ingredientName,String ingredientMeasure) {
        this.name = ingredientName;
        this.ingredientMeasure=ingredientMeasure;
        this.imageUrl = "https://www.themealdb.com/images/ingredients/" + ingredientName + ".png";
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }
    public String getIngredientMeasure() {
        return ingredientMeasure;
    }


}
