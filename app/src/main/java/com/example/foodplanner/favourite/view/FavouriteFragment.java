package com.example.foodplanner.favourite.view;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.foodplanner.databinding.FragmentFavouriteBinding;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.favourite.presenter.FavouritePresenter;
import com.example.foodplanner.favourite.presenter.FavouritePresenterImpl;
import com.example.foodplanner.favourite.repository.FavouriteRepository;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;
import io.reactivex.rxjava3.disposables.CompositeDisposable;


public class FavouriteFragment extends Fragment implements FavouriteView ,FavouriteClick{
    private FragmentFavouriteBinding binding;
    private FavouritePresenter presenter;
    private CompositeDisposable disposable;
    FirebaseUser currentUser;
    FavouriteAdapter adapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        LocalDataSource localDataSource = new LocalDataSource(requireContext(), disposable);
        FavouriteRepository repository = FavouriteRepository.getInstance(localDataSource);
        presenter = new FavouritePresenterImpl(this, repository);
        currentUser= FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentFavouriteBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        if (currentUser != null) {
            presenter.getFavouriteMeals(currentUser.getUid()).observe(getViewLifecycleOwner(), this::setupFavourite);
            presenter.getFavouriteMealsFromFirebase(currentUser.getUid()).observe(getViewLifecycleOwner(), this::setupFavourite);
        } else {
            Snackbar.make(requireView(), "Error", Snackbar.LENGTH_LONG).show();
        }
    }

    private void setupFavourite(List<Meal> mealList) {
         adapter = new FavouriteAdapter(mealList, requireContext(),this);
        LinearLayoutManager layoutManager = new GridLayoutManager(requireContext(),2);
        binding.favouriteRecyclerview.setAdapter(adapter);
        binding.favouriteRecyclerview.setLayoutManager(layoutManager);
        adapter.setList(mealList);
        adapter.notifyDataSetChanged();
    }


    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    public void onFavouriteClick(Meal meal) {
        presenter.deleteMealFromFavourite(meal);
        presenter.deleteMealFromFavoritesFromFirebase(currentUser.getUid(),meal);
        adapter.mealList.remove(meal);
        adapter.notifyDataSetChanged();
    }

}