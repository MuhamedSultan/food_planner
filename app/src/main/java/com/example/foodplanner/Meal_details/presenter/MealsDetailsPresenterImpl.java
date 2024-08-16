package com.example.foodplanner.Meal_details.presenter;

import com.example.foodplanner.Meal_details.repository.MealsDetailsRepository;
import com.example.foodplanner.Meal_details.view.MealsDetailsView;
import com.example.foodplanner.api.NetworkCallback;
import com.example.foodplanner.home.pojo.DailyRandomMeal;
import com.example.foodplanner.home.pojo.Meal;

import java.util.List;

public class MealsDetailsPresenterImpl implements MealsDetailsPresenter{

   private MealsDetailsView view;
   private MealsDetailsRepository repository;
   public MealsDetailsPresenterImpl(MealsDetailsView view,MealsDetailsRepository repository){
       this.view=view;
       this.repository=repository;
   }



    @Override
    public void getAllMealDetailsById(String id) {
       view.showLoading();
       repository.getMealsDetailsById(new NetworkCallback<DailyRandomMeal>() {
           @Override
           public void onSuccessResult(DailyRandomMeal result) {
               List<Meal> mealList=result.getMeals();
               if (mealList!=null&&!mealList.isEmpty()) {
                   view.showMealsDetailsById(mealList);
               }
               view.hideLoading();
           }

           @Override
           public void onFailureResult(String message) {
               view.showErrorMessage(message);
               view.hideLoading();
           }
       },id);

    }
}