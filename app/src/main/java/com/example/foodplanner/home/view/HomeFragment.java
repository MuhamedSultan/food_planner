package com.example.foodplanner.home.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.databinding.FragmentHomeBinding;
import com.example.foodplanner.home.pojo.Meal;
import com.example.foodplanner.home.presenter.HomePresenter;
import com.example.foodplanner.home.presenter.HomePresenterImpl;
import com.example.foodplanner.home.repository.HomeRepository;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class HomeFragment extends Fragment implements HomeView {
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        setActionBarUpButtonVisibility(false);
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.VISIBLE);

        presenter.getRandomMeal();
    }

    @Override
    public void showData(List<Meal> meals) {
            Meal meal = meals.get(0);
            Glide.with(requireContext()).load(meal.getStrMealThumb()).into(binding.imageView2);
            binding.strMeal.setText(meal.getStrMeal());
            binding.strCategory.setText(meal.getStrCategory());
            binding.strArea.setText(meal.getStrArea());


    }

    @Override
    public void showErrorMessage(String message) {
        Snackbar.make(requireView(),message,Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
        binding.progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideLoading() {
        binding.progressBar.setVisibility(View.GONE);

    }


    private void setActionBarUpButtonVisibility(boolean visible) {
        if (getActivity() != null && getActivity() instanceof AppCompatActivity) {
            AppCompatActivity activity = (AppCompatActivity) getActivity();
            if (activity.getSupportActionBar() != null) {
                activity.getSupportActionBar().setDisplayHomeAsUpEnabled(visible);
            }
        }
    }
}
