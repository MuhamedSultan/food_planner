package com.example.foodplanner.search.presenter;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.home.pojo.categories.AllCategories;
import com.example.foodplanner.home.pojo.categories.Category;
import com.example.foodplanner.home.pojo.countries.AllCountries;
import com.example.foodplanner.home.pojo.countries.CountryMeal;
import com.example.foodplanner.home.pojo.ingredients.IngredientMeal;
import com.example.foodplanner.home.pojo.ingredients.IngredientsResponse;
import com.example.foodplanner.search.repository.SearchRepository;
import com.example.foodplanner.search.view.SearchView;

import java.util.List;
import java.util.stream.Collectors;

public class SearchPresenterImpl implements SearchPresenter {

    SearchView view;
    SearchRepository repository;

    public SearchPresenterImpl(SearchView view, SearchRepository repository) {
        this.view = view;
        this.repository = repository;
    }

    @Override
    public void getAllIngredients() {
        view.showLoading();
        repository.getAllIngredients(new NetworkCallback<IngredientsResponse>() {
            @Override
            public void onSuccessResult(IngredientsResponse result) {
                List<IngredientMeal> mealList = result.getMeals();
                if (mealList != null && !mealList.isEmpty()) {
                    view.showAllIngredients(mealList);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showMessage(message);
                view.hideLoading();
            }
        });
    }

    @Override
    public void getAllCountries() {
        view.showLoading();
        repository.getAllCountries(new NetworkCallback<AllCountries>() {
            @Override
            public void onSuccessResult(AllCountries result) {
                List<CountryMeal> countryMeals = result.getMeals();
                if (countryMeals != null && !countryMeals.isEmpty()) {
                    view.showAllCountries(countryMeals);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showMessage(message);
                view.hideLoading();
            }
        });
    }

    @Override
    public void getAllCategories() {
        view.showLoading();
        repository.getAllCategories(new NetworkCallback<AllCategories>() {
            @Override
            public void onSuccessResult(AllCategories result) {
                List<Category> categories = result.getCategories();
                if (categories != null && !categories.isEmpty()) {
                    view.showAllCategories(categories);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showMessage(message);
                view.hideLoading();
            }
        });
    }

}
