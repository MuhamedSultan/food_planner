package com.example.foodplanner.calender.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.example.foodplanner.R;
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.calender.presenter.PlanPresenter;
import com.example.foodplanner.calender.presenter.PlanPresenterImpl;
import com.example.foodplanner.calender.repository.PlanRepository;
import com.example.foodplanner.databinding.FragmentCalenderBinding;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;


public class CalenderFragment extends Fragment implements PlanView,WeekdaysAdapter.OnWeekdayClickListener ,PlanMealClick{

    private RecyclerView rvWeekdays;
    private WeekdaysAdapter weekdaysAdapter;
    private LocalDataSource localDataSource;
    private PlanRepository repository;
    private PlanPresenter presenter;
    private CompositeDisposable disposable ;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        localDataSource = new LocalDataSource(getContext(), disposable);
        repository=new PlanRepository(localDataSource);
        presenter=new PlanPresenterImpl(this,repository);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_calender, container, false);
        rvWeekdays = view.findViewById(R.id.planRecyclerview);
        rvWeekdays.setLayoutManager(new LinearLayoutManager(getContext()));

        weekdaysAdapter = new WeekdaysAdapter(getWeekdays(), this,this);
        rvWeekdays.setAdapter(weekdaysAdapter);
        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    private List<String> getWeekdays() {
        return Arrays.asList("Saturday", "Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday");
    }

    @Override
    public void onWeekdayClick(String weekday) {
        disposable.add(presenter.getMealOfPlan(weekday)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mealPlans -> {
                    if (mealPlans != null && !mealPlans.isEmpty()) {
                        weekdaysAdapter.updateMeals(weekday, mealPlans);
                    } else {
                        weekdaysAdapter.updateMeals(weekday, new ArrayList<>());
                    }
                }, throwable -> {
                    Log.e("CalenderFragment", "Error fetching meals", throwable);
                }));
    }

    @Override
    public void onClickPlanMeal(String mealId) {
        NavDirections navDirections=CalenderFragmentDirections.actionCalenderFragmentToMealDetailsFragment(mealId);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    @Override
    public void onClickDeleteMeal(MealPlan meal) {
        presenter.deleteMealFromPlan(meal);
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
}
