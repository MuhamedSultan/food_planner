package com.example.foodplanner.countries_recipes.view;

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

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenter;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenterImpl;
import com.example.foodplanner.category_details.repository.CategoryDetailsRepository;
import com.example.foodplanner.category_details.view.CategoryMealsAdapter;
import com.example.foodplanner.countries_recipes.pojo.CountryRecipesMeal;
import com.example.foodplanner.countries_recipes.presenter.CountryRecipesPresenter;
import com.example.foodplanner.countries_recipes.presenter.CountryRecipesPresenterImpl;
import com.example.foodplanner.countries_recipes.repository.CountryRecipesRepository;
import com.example.foodplanner.databinding.FragmentCountryRecipesBinding;
import com.example.foodplanner.home.view.adapter.CountryClick;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class CountryRecipesFragment extends Fragment implements CountryRecipesView, CountryRecipesMealClick {
    private FragmentCountryRecipesBinding binding;
    private CountryRecipesPresenter presenter;
    List<CountryRecipesMeal> allMeals;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        CountryRecipesRepository repository = CountryRecipesRepository.getInstance(remoteDataSource);
        presenter = new CountryRecipesPresenterImpl(this, repository);
        allMeals=new ArrayList<>();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding=FragmentCountryRecipesBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        CountryRecipesFragmentArgs args=CountryRecipesFragmentArgs.fromBundle(getArguments());
        String countryName =args.getCountryName();
        presenter.getCountryRecipes(countryName);
        binding.tvMeals.setText(countryName+" Meals");
        setupSearch();
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.GONE);

    }


    @Override
    public void showCountryRecipesList(List<CountryRecipesMeal> countryRecipesMeals) {
        allMeals=countryRecipesMeals;
        setupCountryMealsRecyclerview(countryRecipesMeals);
    }


    @Override
    public void showErrorMessage(String message) {

    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }


    @SuppressLint("CheckResult")
    private void setupSearch() {
        Observable.create(emitter -> binding.edSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<CountryRecipesMeal> filteredMeals = new ArrayList<>();
                        for (CountryRecipesMeal meal : allMeals) {
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
                  setupCountryMealsRecyclerview((List<CountryRecipesMeal>) filteredMeals);
                });
    }

    private void setupCountryMealsRecyclerview(List<CountryRecipesMeal> meals){
        CountryRecipesAdapter adapter=new CountryRecipesAdapter(meals,requireContext(),this);
        GridLayoutManager layoutManager=new GridLayoutManager(requireContext(),2);
        binding.countryMealRecyclerview.setAdapter(adapter);
        binding.countryMealRecyclerview.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }


    @Override
    public void onCountryMealClick(String id) {
        NavDirections navDirections=CountryRecipesFragmentDirections.actionCountryRecipesFragmentToMealDetailsFragment(id);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }
}