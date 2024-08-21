package com.example.foodplanner.category_details.view;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenter;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenterImpl;
import com.example.foodplanner.category_details.repository.CategoryDetailsRepository;
import com.example.foodplanner.databinding.FragmentCategoryBinding;
import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CategoryFragment extends Fragment implements CategoryView ,CategoryMealClick{
    private FragmentCategoryBinding binding;
    private CategoryDetailsPresenter presenter;
    private List<Meal> allMeals;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        CategoryDetailsRepository repository = CategoryDetailsRepository.getInstance(remoteDataSource);
        presenter = new CategoryDetailsPresenterImpl(this, repository);
        allMeals=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCategoryBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CategoryFragmentArgs args = CategoryFragmentArgs.fromBundle(getArguments());
         String categoryName = args.getCategoryName();
        presenter.getMealsByCategory(categoryName);
        setupSearch();

    }

    @SuppressLint("CheckResult")
    private void setupSearch() {
        Observable.create(emitter -> binding.edSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<Meal> filteredMeals = new ArrayList<>();
                        for (Meal meal : allMeals) {
                            if (meal.getStrMeal().toLowerCase().startsWith(s.toString().toLowerCase())) {
                                filteredMeals.add(meal);
                            }
                        }
                        emitter.onNext(filteredMeals);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                })).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredMeals -> {
                        updateRecyclerView((List<Meal>) filteredMeals);
                });
    }

    private void updateRecyclerView(List<Meal> meals) {
        CategoryMealsAdapter adapter = new CategoryMealsAdapter(meals, requireContext(), this);
        binding.allCategoryMealsRecyclerview.setAdapter(adapter);
        adapter.setList(meals);
    }






    @Override
    public void showCategoryMealsList(List<Meal> categoryMealsList) {
        allMeals=categoryMealsList;
        setupCategoryMealsRecyclerview(categoryMealsList);
    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }
    private void setupCategoryMealsRecyclerview(List<Meal> meals){
        CategoryMealsAdapter adapter=new CategoryMealsAdapter(meals,getContext(),this);
        GridLayoutManager layoutManager=new GridLayoutManager(requireContext(),2);
        binding.allCategoryMealsRecyclerview.setAdapter(adapter);
        binding.allCategoryMealsRecyclerview.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }

    @Override
    public void onCategoryMealClick(String id) {
        NavDirections navDirections=
                CategoryFragmentDirections.actionCategoryFragmentToMealDetailsFragment(id);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }
}