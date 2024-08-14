package com.example.foodplanner.home.pojo;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Meal implements Parcelable {
    private String strIngredient10;
    private String strIngredient12;
    private String strIngredient11;
    private String strIngredient14;
    private String strCategory;
    private String strIngredient13;
    private String strIngredient15;
    private String strArea;
    private String idMeal;
    private String strInstructions;
    private String strIngredient1;
    private String strIngredient3;
    private String strIngredient2;
    private String strIngredient5;
    private String strIngredient4;
    private String strIngredient7;
    private String strIngredient6;
    private String strIngredient9;
    private String strIngredient8;
    private String strMealThumb;
    private String strYoutube;
    private String strMeal;
    private String strMeasure12;
    private String strMeasure13;
    private String strMeasure10;
    private String strMeasure11;
    private String strSource;
    private String strMeasure9;
    private String strMeasure7;
    private String strMeasure8;
    private String strMeasure5;
    private String strMeasure6;
    private String strMeasure3;
    private String strMeasure4;
    private String strMeasure1;
    private String strMeasure2;
    private String strMeasure14;
    private String strMeasure15;

    protected Meal(Parcel in) {
        strIngredient10 = in.readString();
        strIngredient12 = in.readString();
        strIngredient11 = in.readString();
        strIngredient14 = in.readString();
        strCategory = in.readString();
        strIngredient13 = in.readString();
        strIngredient15 = in.readString();
        strArea = in.readString();
        idMeal = in.readString();
        strInstructions = in.readString();
        strIngredient1 = in.readString();
        strIngredient3 = in.readString();
        strIngredient2 = in.readString();
        strIngredient5 = in.readString();
        strIngredient4 = in.readString();
        strIngredient7 = in.readString();
        strIngredient6 = in.readString();
        strIngredient9 = in.readString();
        strIngredient8 = in.readString();
        strMealThumb = in.readString();
        strYoutube = in.readString();
        strMeal = in.readString();
        strMeasure12 = in.readString();
        strMeasure13 = in.readString();
        strMeasure10 = in.readString();
        strMeasure11 = in.readString();
        strSource = in.readString();
        strMeasure9 = in.readString();
        strMeasure7 = in.readString();
        strMeasure8 = in.readString();
        strMeasure5 = in.readString();
        strMeasure6 = in.readString();
        strMeasure3 = in.readString();
        strMeasure4 = in.readString();
        strMeasure1 = in.readString();
        strMeasure2 = in.readString();
        strMeasure14 = in.readString();
        strMeasure15 = in.readString();
    }

    public static final Creator<Meal> CREATOR = new Creator<Meal>() {
        @Override
        public Meal createFromParcel(Parcel in) {
            return new Meal(in);
        }

        @Override
        public Meal[] newArray(int size) {
            return new Meal[size];
        }
    };

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

    public String getidMeal() { return idMeal; }
    public void setidMeal(String value) { this.idMeal = value; }

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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(strIngredient10);
        dest.writeString(strIngredient12);
        dest.writeString(strIngredient11);
        dest.writeString(strIngredient14);
        dest.writeString(strCategory);
        dest.writeString(strIngredient13);
        dest.writeString(strIngredient15);
        dest.writeString(strArea);
        dest.writeString(idMeal);
        dest.writeString(strInstructions);
        dest.writeString(strIngredient1);
        dest.writeString(strIngredient3);
        dest.writeString(strIngredient2);
        dest.writeString(strIngredient5);
        dest.writeString(strIngredient4);
        dest.writeString(strIngredient7);
        dest.writeString(strIngredient6);
        dest.writeString(strIngredient9);
        dest.writeString(strIngredient8);
        dest.writeString(strMealThumb);
        dest.writeString(strYoutube);
        dest.writeString(strMeal);
        dest.writeString(strMeasure12);
        dest.writeString(strMeasure13);
        dest.writeString(strMeasure10);
        dest.writeString(strMeasure11);
        dest.writeString(strSource);
        dest.writeString(strMeasure9);
        dest.writeString(strMeasure7);
        dest.writeString(strMeasure8);
        dest.writeString(strMeasure5);
        dest.writeString(strMeasure6);
        dest.writeString(strMeasure3);
        dest.writeString(strMeasure4);
        dest.writeString(strMeasure1);
        dest.writeString(strMeasure2);
        dest.writeString(strMeasure14);
        dest.writeString(strMeasure15);
    }
}

