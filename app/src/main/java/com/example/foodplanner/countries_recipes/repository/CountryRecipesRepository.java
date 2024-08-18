package com.example.foodplanner.countries_recipes.repository;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipes;
import com.example.foodplanner.home.repository.HomeRepository;

public class CountryRecipesRepository {
    private final RemoteDataSource remoteDataSource;
    private static CountryRecipesRepository instance = null;

    public CountryRecipesRepository(RemoteDataSource remoteDataSource) {
        this.remoteDataSource = remoteDataSource;
    }

    public static CountryRecipesRepository getInstance(RemoteDataSource remoteDataSource) {
        if (instance == null) {
            instance = new CountryRecipesRepository(remoteDataSource);
        }
        return instance;
    }

    public void getCountryRecipes(NetworkCallback<CountryRecipes> callback,String countryName){
        remoteDataSource.getCountryRecipes(callback,countryName);
    }
}
