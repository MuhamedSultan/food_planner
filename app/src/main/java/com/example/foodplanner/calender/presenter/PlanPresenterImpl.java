package com.example.foodplanner.calender.presenter;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.calender.repository.PlanRepository;
import com.example.foodplanner.calender.view.PlanView;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.List;

import io.reactivex.rxjava3.core.Observable;

public class PlanPresenterImpl implements PlanPresenter {

    PlanView view;
    PlanRepository repository;
    public PlanPresenterImpl(PlanView view,PlanRepository repository){
        this.view=view;
        this.repository=repository;
    }
    @Override
    public Observable<List<Meal>> getMealOfPlan(String mealOfDay) {
       return repository.getMealOfPlan(mealOfDay);
    }
}
