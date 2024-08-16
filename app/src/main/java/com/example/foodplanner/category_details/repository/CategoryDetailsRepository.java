package com.example.foodplanner.category_details.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.category_details.pojo.CategoryMeals;

public class CategoryDetailsRepository {

    private RemoteDataSource remoteDataSource;
    private static CategoryDetailsRepository instance = null;

    public CategoryDetailsRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static CategoryDetailsRepository getInstance(RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new CategoryDetailsRepository(remoteDataSource);
        }
        return instance;
    }

    public void getMealByCategory(NetworkCallback<CategoryMeals> callback, String categoryName) {

        remoteDataSource.getMealsByCategory(callback, categoryName);
    }
}