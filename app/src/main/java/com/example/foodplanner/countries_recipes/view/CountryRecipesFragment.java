package com.example.foodplanner.countries_recipes.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

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

import java.util.List;

public class CountryRecipesFragment extends Fragment implements CountryRecipesView, CountryRecipesMealClick {
    private FragmentCountryRecipesBinding binding;
    private CountryRecipesPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        CountryRecipesRepository repository = CountryRecipesRepository.getInstance(remoteDataSource);
        presenter = new CountryRecipesPresenterImpl(this, repository);
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
    }


    @Override
    public void showCountryRecipesList(List<CountryRecipesMeal> countryRecipesMeals) {
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