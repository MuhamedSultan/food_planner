package com.example.foodplanner.category_details.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.Meal_details.view.MealDetailsFragmentArgs;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.category_details.pojo.Meal;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenter;
import com.example.foodplanner.category_details.presenter.CategoryDetailsPresenterImpl;
import com.example.foodplanner.category_details.repository.CategoryDetailsRepository;
import com.example.foodplanner.databinding.FragmentCategoryBinding;
import com.example.foodplanner.home.pojo.Category;
import com.example.foodplanner.home.presenter.HomePresenterImpl;
import com.example.foodplanner.home.repository.HomeRepository;
import com.example.foodplanner.home.view.AllCategoriesAdapter;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;

public class CategoryFragment extends Fragment implements CategoryView {
    private FragmentCategoryBinding binding;
    private CategoryDetailsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        CategoryDetailsRepository repository = CategoryDetailsRepository.getInstance(remoteDataSource);
        presenter = new CategoryDetailsPresenterImpl(this, repository);

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
    }

    @Override
    public void showCategoryMealsList(List<Meal> categoryMealsList) {
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
        CategoryMealsAdapter adapter=new CategoryMealsAdapter(meals,requireContext());
        GridLayoutManager layoutManager=new GridLayoutManager(requireContext(),2);
        binding.allCategoryMealsRecyclerview.setAdapter(adapter);
        binding.allCategoryMealsRecyclerview.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }
}