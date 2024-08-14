package com.example.foodplanner.api;

import retrofit2.Retrofit;
import retrofit2.adapter.rxjava3.RxJava3CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

public class MealsClient {
    public static final String API_BASE_URL = "https://www.themealdb.com/";
    private static MealsClient mealsClient = null;
    private final ApiService apiService;

    private MealsClient() {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(API_BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJava3CallAdapterFactory.create())
                .build();
        apiService = retrofit.create(ApiService.class);
    }

    public static MealsClient getInstance() {
        if (mealsClient == null) {
            mealsClient = new MealsClient();
        }
        return mealsClient;
    }

    public ApiService getApiService() {
        return apiService;
    }
}
