package com.example.foodplanner.category_details.presenter;

import com.airbnb.lottie.L;
import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.category_details.pojo.CategoryMeals;
import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.category_details.repository.CategoryDetailsRepository;
import com.example.foodplanner.category_details.view.CategoryView;
import com.example.foodplanner.home.repository.HomeRepository;
import com.example.foodplanner.home.view.HomeView;

import java.util.List;

public class CategoryDetailsPresenterImpl implements CategoryDetailsPresenter {


    private final CategoryView view;
    private final CategoryDetailsRepository repository;

    public CategoryDetailsPresenterImpl(CategoryView view, CategoryDetailsRepository repository) {
        this.view = view;
        this.repository = repository;
    }


    @Override
    public void getMealsByCategory(String categoryName) {
        view.showLoading();
        repository.getMealByCategory(new NetworkCallback<CategoryMeals>() {
            @Override
            public void onSuccessResult(CategoryMeals result) {
                List<Meal> categoryMealsList=result.getMeals();
                if (categoryMealsList != null && !categoryMealsList.isEmpty()) {
                    view.showCategoryMealsList(categoryMealsList);
                }
                view.hideLoading();

            }

            @Override
            public void onFailureResult(String message) {
                view.showErrorMessage(message);
                view.hideLoading();
            }
        },categoryName);
    }
}
