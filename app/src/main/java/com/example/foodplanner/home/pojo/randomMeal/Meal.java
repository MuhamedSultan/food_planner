package com.example.foodplanner.home.pojo.randomMeal;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

@Entity(tableName = "meals_table")
public class Meal {
    @Ignore
    private String strIngredient10;
    @Ignore
    private String strIngredient12;
    @Ignore
    private String strIngredient11;
    @Ignore
    private String strIngredient14;
    private String strCategory;
    @Ignore
    private String strIngredient13;
    @Ignore
    private String strIngredient15;
    private String strArea;
    @PrimaryKey
    @NonNull
    private String idMeal;
    @Ignore
    private String strInstructions;
    @Ignore
    private String strIngredient1;
    @Ignore
    private String strIngredient3;
    @Ignore
    private String strIngredient2;
    @Ignore
    private String strIngredient5;
    @Ignore
    private String strIngredient4;
    @Ignore
    private String strIngredient7;
    @Ignore
    private String strIngredient6;
    @Ignore
    private String strIngredient9;
    @Ignore
    private String strIngredient8;
    private String strMealThumb;
    @Ignore
    private String strYoutube;
    private String strMeal;
    @Ignore
    private String strMeasure12;
    @Ignore
    private String strMeasure13;
    @Ignore
    private String strMeasure10;
    @Ignore
    private String strMeasure11;
    @Ignore
    private String strSource;
    @Ignore
    private String strMeasure9;
    @Ignore
    private String strMeasure7;
    @Ignore
    private String strMeasure8;
    @Ignore
    private String strMeasure5;
    @Ignore
    private String strMeasure6;
    @Ignore
    private String strMeasure3;
    @Ignore
    private String strMeasure4;
    @Ignore
    private String strMeasure1;
    @Ignore
    private String strMeasure2;
    @Ignore
    private String strMeasure14;
    @Ignore
    private String strMeasure15;
    public boolean isFavourite=false;
    private String userId;



    public String getMealOfDay() {
        return mealOfDay;
    }

    public void setMealOfDay(String mealOfDay) {
        this.mealOfDay = mealOfDay;
    }

    private String mealOfDay;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getStrIngredient10() { return strIngredient10; }
    public void setStrIngredient10(String value) { this.strIngredient10 = value; }

    public String getStrIngredient12() { return strIngredient12; }
    public void setStrIngredient12(String value) { this.strIngredient12 = value; }

    public String getStrIngredient11() { return strIngredient11; }
    public void setStrIngredient11(String value) { this.strIngredient11 = value; }

    public String getStrIngredient14() { return strIngredient14; }
    public void setStrIngredient14(String value) { this.strIngredient14 = value; }

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }

    public String getStrIngredient13() { return strIngredient13; }
    public void setStrIngredient13(String value) { this.strIngredient13 = value; }

    public String getStrIngredient15() { return strIngredient15; }
    public void setStrIngredient15(String value) { this.strIngredient15 = value; }

    public String getStrArea() { return strArea; }
    public void setStrArea(String value) { this.strArea = value; }

    public String getIdMeal() { return idMeal; }
    public void setIdMeal(String value) { this.idMeal = value; }

    public String getStrInstructions() { return strInstructions; }
    public void setStrInstructions(String value) { this.strInstructions = value; }

    public String getStrIngredient1() { return strIngredient1; }
    public void setStrIngredient1(String value) { this.strIngredient1 = value; }

    public String getStrIngredient3() { return strIngredient3; }
    public void setStrIngredient3(String value) { this.strIngredient3 = value; }

    public String getStrIngredient2() { return strIngredient2; }
    public void setStrIngredient2(String value) { this.strIngredient2 = value; }

    public String getStrIngredient5() { return strIngredient5; }
    public void setStrIngredient5(String value) { this.strIngredient5 = value; }

    public String getStrIngredient4() { return strIngredient4; }
    public void setStrIngredient4(String value) { this.strIngredient4 = value; }

    public String getStrIngredient7() { return strIngredient7; }
    public void setStrIngredient7(String value) { this.strIngredient7 = value; }

    public String getStrIngredient6() { return strIngredient6; }
    public void setStrIngredient6(String value) { this.strIngredient6 = value; }

    public String getStrIngredient9() { return strIngredient9; }
    public void setStrIngredient9(String value) { this.strIngredient9 = value; }

    public String getStrIngredient8() { return strIngredient8; }
    public void setStrIngredient8(String value) { this.strIngredient8 = value; }

    public String getStrMealThumb() { return strMealThumb; }
    public void setStrMealThumb(String value) { this.strMealThumb = value; }

    public String getStrYoutube() { return strYoutube; }
    public void setStrYoutube(String value) { this.strYoutube = value; }

    public String getStrMeal() { return strMeal; }
    public void setStrMeal(String value) { this.strMeal = value; }

    public String getStrMeasure12() { return strMeasure12; }
    public void setStrMeasure12(String value) { this.strMeasure12 = value; }

    public String getStrMeasure13() { return strMeasure13; }
    public void setStrMeasure13(String value) { this.strMeasure13 = value; }

    public String getStrMeasure10() { return strMeasure10; }
    public void setStrMeasure10(String value) { this.strMeasure10 = value; }

    public String getStrMeasure11() { return strMeasure11; }
    public void setStrMeasure11(String value) { this.strMeasure11 = value; }

    public String getStrSource() { return strSource; }
    public void setStrSource(String value) { this.strSource = value; }

    public String getStrMeasure9() { return strMeasure9; }
    public void setStrMeasure9(String value) { this.strMeasure9 = value; }

    public String getStrMeasure7() { return strMeasure7; }
    public void setStrMeasure7(String value) { this.strMeasure7 = value; }

    public String getStrMeasure8() { return strMeasure8; }
    public void setStrMeasure8(String value) { this.strMeasure8 = value; }

    public String getStrMeasure5() { return strMeasure5; }
    public void setStrMeasure5(String value) { this.strMeasure5 = value; }

    public String getStrMeasure6() { return strMeasure6; }
    public void setStrMeasure6(String value) { this.strMeasure6 = value; }

    public String getStrMeasure3() { return strMeasure3; }
    public void setStrMeasure3(String value) { this.strMeasure3 = value; }

    public String getStrMeasure4() { return strMeasure4; }
    public void setStrMeasure4(String value) { this.strMeasure4 = value; }

    public String getStrMeasure1() { return strMeasure1; }
    public void setStrMeasure1(String value) { this.strMeasure1 = value; }

    public String getStrMeasure2() { return strMeasure2; }
    public void setStrMeasure2(String value) { this.strMeasure2 = value; }

    public String getStrMeasure14() { return strMeasure14; }
    public void setStrMeasure14(String value) { this.strMeasure14 = value; }

    public String getStrMeasure15() { return strMeasure15; }
    public void setStrMeasure15(String value) { this.strMeasure15 = value; }

}
