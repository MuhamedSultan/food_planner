package com.example.foodplanner.countries_recipes.presenter;

import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipes;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipesMeal;
import com.example.foodplanner.countries_recipes.repository.CountryRecipesRepository;
import com.example.foodplanner.countries_recipes.view.CountryRecipesView;

import java.util.List;

public class CountryRecipesPresenterImpl implements CountryRecipesPresenter{

    private CountryRecipesView view;
    private CountryRecipesRepository repository;

    public CountryRecipesPresenterImpl(CountryRecipesView view,CountryRecipesRepository repository){
        this.view=view;
        this.repository=repository;
    }

    @Override
    public void getCountryRecipes(String countryName) {
        view.showLoading();
        repository.getCountryRecipes(new NetworkCallback<CountryRecipes>() {
            @Override
            public void onSuccessResult(CountryRecipes result) {
                List<CountryRecipesMeal> meals=result.getMeals();
                if (meals!=null&&!meals.isEmpty()){
                    view.showCountryRecipesList(meals);
                }
                view.hideLoading();
            }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();

            }
        },countryName);
    }
}
