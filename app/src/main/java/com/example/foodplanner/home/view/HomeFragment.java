package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.Category;
import com.example.foodplanner.home.pojo.Meal;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.home.presenter.HomePresenterImpl;
import com.example.foodplanner.home.repository.HomeRepository;
import com.google.android.material.snackbar.Snackbar;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class HomeFragment extends Fragment implements HomeView, CategoryClick {
    FragmentHomeBinding binding;
    private HomePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        HomeRepository homeRepository = HomeRepository.getInstance(remoteDataSource);
        presenter = new HomePresenterImpl(this, homeRepository);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarUpButtonVisibility(false);
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.VISIBLE);

        String savedDate = LocalDataSource.getSavedDate(requireContext());
        String todayDate = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(new Date());

        if (savedDate != null && savedDate.equals(todayDate)) {
            String savedMealId = LocalDataSource.getSavedMealId(requireContext());
            presenter.getAllMealDetailsById(savedMealId);
        } else {
            presenter.getRandomMeal();
        }

        presenter.getAllCategories();
    }

    @Override
    public void showDailyRandomMealData(List<Meal> meals) {
       showMeal(meals);
    }

    @Override
    public void showMealsDetailsById(List<Meal> meals) {
       showMeal(meals);
    }

    private void showMeal(List<Meal> mealsDetails){
        Meal meal = mealsDetails.get(0);
        LocalDataSource.saveMealId(requireContext(), meal.getidMeal());
        Glide.with(requireContext()).load(meal.getStrMealThumb()).into(binding.imageView2);
        binding.strMeal.setText(meal.getStrMeal());
        binding.strCategory.setText(meal.getStrCategory());
        binding.strArea.setText(meal.getStrArea());
        binding.dailyMealCardView.setOnClickListener(v -> {
            NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToMealDetailsFragment(meal.getidMeal());
            Navigation.findNavController(v).navigate(navDirections);
        });
    }
    @Override
    public void showAllCategories(List<Category> categories) {
        setUpAllCategoriesRecyclerview(categories);
    }



    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);
    }

    private void setUpAllCategoriesRecyclerview(List<Category> categories) {
        AllCategoriesAdapter adapter = new AllCategoriesAdapter(categories, requireContext(), this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.allCategoriesRecyclerView.setAdapter(adapter);
        binding.allCategoriesRecyclerView.setLayoutManager(layoutManager);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
        adapter.setList(categories);
    }

    private void setActionBarUpButtonVisibility(boolean visible) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(visible);
            }
        }
    }

    @Override
    public void onCategoryClick(String categoryName) {
        NavDirections navDirections = HomeFragmentDirections.actionHomeFragmentToCategoryFragment(categoryName);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }
}
