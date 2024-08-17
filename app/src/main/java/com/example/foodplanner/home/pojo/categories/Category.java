package com.example.foodplanner.home.pojo.categories;

import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

public class Category implements Parcelable {
    private String strCategory;
    private String strCategoryDescription;
    private String idCategory;
    private String strCategoryThumb;

    protected Category(Parcel in) {
        strCategory = in.readString();
        strCategoryDescription = in.readString();
        idCategory = in.readString();
        strCategoryThumb = in.readString();
    }

    public static final Creator<Category> CREATOR = new Creator<Category>() {
        @Override
        public Category createFromParcel(Parcel in) {
            return new Category(in);
        }

        @Override
        public Category[] newArray(int size) {
            return new Category[size];
        }
    };

    public String getStrCategory() { return strCategory; }
    public void setStrCategory(String value) { this.strCategory = value; }

    public String getStrCategoryDescription() { return strCategoryDescription; }
    public void setStrCategoryDescription(String value) { this.strCategoryDescription = value; }

    public String getidCategory() { return idCategory; }
    public void setidCategory(String value) { this.idCategory = value; }

    public String getStrCategoryThumb() { return strCategoryThumb; }
    public void setStrCategoryThumb(String value) { this.strCategoryThumb = value; }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel dest, int flags) {
        dest.writeString(strCategory);
        dest.writeString(strCategoryDescription);
        dest.writeString(idCategory);
        dest.writeString(strCategoryThumb);
    }
}

