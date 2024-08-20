package com.example.foodplanner.home.pojo.ingredients;

public class IngredientMeal {
    private String strDescription;
    private String strIngredient;
    private String idIngredient;
    private String strType;

    public String getStrThumb() {
        strThumb= "https://www.themealdb.com/images/ingredients/" + strIngredient + ".png";
        return strThumb;
    }

    public void setStrThumb(String strThumb) {
        this.strThumb = strThumb;
    }


    private String strThumb;

    public String getStrDescription() { return strDescription; }
    public void setStrDescription(String value) { this.strDescription = value; }

    public String getStrIngredient() { return strIngredient; }
    public void setStrIngredient(String value) { this.strIngredient = value; }

    public String getidIngredient() { return idIngredient; }
    public void setidIngredient(String value) { this.idIngredient = value; }

    public String getStrType() { return strType; }
    public void setStrType(String value) { this.strType = value; }
}
