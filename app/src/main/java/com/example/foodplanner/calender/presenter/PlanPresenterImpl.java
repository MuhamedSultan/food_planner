package com.example.foodplanner.calender.presenter;

import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.calender.repository.PlanRepository;
import com.example.foodplanner.calender.view.PlanView;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Completable;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class PlanPresenterImpl implements PlanPresenter {

    PlanView view;
    PlanRepository repository;
    FirebaseFirestore firestore;

    public PlanPresenterImpl(PlanView view, PlanRepository repository) {
        this.view = view;
        this.repository = repository;
        firestore=FirebaseFirestore.getInstance();
    }

    @Override
    public Observable<List<MealPlan>> getMealOfPlan(String userId,String mealOfDay) {
        return repository.getMealOfPlan(userId,mealOfDay);
    }

    @Override
    public void deleteMealFromPlan(String userId,MealPlan mealPlan) {
         repository.deleteMealFromPlan(mealPlan)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(
                        () -> view.showMessage("Deleted From Plan Successfully"),
                        throwable -> view.showMessage(throwable.getMessage())
                );
        firestore.collection("users").document(userId)
                .collection("week_plan").document(mealPlan.getIdMeal())
                .delete();
    }

}
