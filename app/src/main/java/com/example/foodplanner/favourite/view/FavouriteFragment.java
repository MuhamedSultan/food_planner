package com.example.foodplanner.favourite.view;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;

import com.example.foodplanner.MainActivity;
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


public class FavouriteFragment extends Fragment implements FavouriteView, FavouriteClick {
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
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
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
        requireActivity().invalidateOptionsMenu();
        if (currentUser != null) {
            presenter.getFavouriteMealsFromFirebase(currentUser.getUid())
                    .observe(getViewLifecycleOwner(), this::setupFavourite);
            presenter.getFavouriteMeals(currentUser.getUid())
                    .observe(getViewLifecycleOwner(), this::setupFavourite);
        } else {
            Snackbar.make(requireView(), "Please log in to see favourite meals", Snackbar.LENGTH_LONG).show();
        }
    }

    private void setupFavourite(List<Meal> mealList) {
        if (mealList.isEmpty()){
            binding.imageView.setVisibility(View.VISIBLE);
            binding.tvDesc.setVisibility(View.VISIBLE);
            binding.tvDesc1.setVisibility(View.VISIBLE);
            binding.favouriteRecyclerview.setVisibility(View.GONE);
        }else {
            binding.imageView.setVisibility(View.GONE);
            binding.tvDesc.setVisibility(View.GONE);
            binding.tvDesc1.setVisibility(View.GONE);
            binding.favouriteRecyclerview.setVisibility(View.VISIBLE);
        }
        if (adapter == null) {
            adapter = new FavouriteAdapter(mealList, requireContext(), this);
            LinearLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
            binding.favouriteRecyclerview.setLayoutManager(layoutManager);
            binding.favouriteRecyclerview.setAdapter(adapter);
        } else {
            adapter.setList(mealList);
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void onFavouriteClick(Meal meal) {

        if (currentUser != null) {
            presenter.deleteMealFromFavoritesFromFirebase(currentUser.getUid(), meal);
            presenter.deleteMealFromFavourite(meal);
        }
        LocalDataSource.setMealFavoriteStatus(getContext(), meal.getIdMeal(), false);


        Snackbar.make(requireView(), "Removed From Favourites Successfully", Snackbar.LENGTH_SHORT).show();
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.VISIBLE);

    }
}

