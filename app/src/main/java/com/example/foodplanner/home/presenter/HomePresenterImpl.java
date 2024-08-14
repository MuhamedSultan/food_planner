package com.example.foodplanner.home.presenter;

import com.example.foodplanner.home.pojo.Meal;
import com.example.foodplanner.home.repository.HomeRepository;
import com.example.foodplanner.home.view.HomeView;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class HomePresenterImpl implements HomePresenter {
    private final HomeView view;
    private final HomeRepository homeRepository;
    private final CompositeDisposable disposables = new CompositeDisposable();

    public HomePresenterImpl(HomeView view, HomeRepository homeRepository) {
        this.view = view;
        this.homeRepository = homeRepository;
    }

    @Override
    public void getRandomMeal() {
        view.showLoading();
        disposables.add(
        homeRepository.getDailyMeals()
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(
                                mealResponse -> {
                                    view.showData(mealResponse.getMeals());
                                    view.hideLoading();
                                },
                                throwable -> {
                                    view.showErrorMessage(throwable.getMessage());
                                }
                        )
        );


    }

    @Override
    public void addMealToFavourite(Meal meal) {
        // Implement the functionality to add a meal to the favourites
    }
}
