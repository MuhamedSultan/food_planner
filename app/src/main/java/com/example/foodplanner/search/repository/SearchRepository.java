package com.example.foodplanner.search.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.ingredients.IngredientsResponse;

public class SearchRepository {
    RemoteDataSource remoteDataSource;
    SearchRepository instance=null;
    public SearchRepository(RemoteDataSource remoteDataSource){
        this.remoteDataSource=remoteDataSource;
    }
    public SearchRepository getInstance(RemoteDataSource remoteDataSource){
        if (instance==null){
            instance=new SearchRepository(remoteDataSource);
        }
        return instance;
    }
    public void getAllIngredients(NetworkCallback<IngredientsResponse> callback){
        remoteDataSource.getAllIngredients(callback);
    }
    public void getAllCountries(NetworkCallback<AllCountries> callback) {
        remoteDataSource.getAllCountries(callback);
    }

    public void getAllCategories(NetworkCallback<AllCategories> callback) {
        remoteDataSource.getAllCategories(callback);
    }

}
