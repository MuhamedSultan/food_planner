package com.example.foodplanner.Meal_details.pojo;

public class Ingredients {
    private String name;
    private String imageUrl;

    public Ingredients(String name) {
        this.name = name;
        this.imageUrl = "https://www.themealdb.com/images/ingredients/" + name + ".png";
    }

    public String getName() {
        return name;
    }

    public String getImageUrl() {
        return imageUrl;
    }


}
