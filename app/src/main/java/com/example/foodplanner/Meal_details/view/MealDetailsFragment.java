package com.example.foodplanner.Meal_details.view;

import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.bumptech.glide.Glide;
import com.example.foodplanner.MainActivity;
import com.example.foodplanner.Meal_details.pojo.IngredientItem;
import com.example.foodplanner.Meal_details.pojo.Ingredients;
import com.example.foodplanner.Meal_details.presenter.MealsDetailsPresenter;
import com.example.foodplanner.Meal_details.presenter.MealsDetailsPresenterImpl;
import com.example.foodplanner.Meal_details.repository.MealsDetailsRepository;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.databinding.DaysOfPlanBinding;
import com.example.foodplanner.databinding.FragmentMealDetailsBinding;
import com.example.foodplanner.db.LocalDataSource;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.util.List;

import io.reactivex.rxjava3.disposables.CompositeDisposable;

public class MealDetailsFragment extends Fragment implements MealsDetailsView {
    private FragmentMealDetailsBinding binding;
    private MealsDetailsPresenter presenter;
    private CompositeDisposable disposable;
    private FirebaseUser currentUser;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        disposable = new CompositeDisposable();
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        LocalDataSource localDataSource = new LocalDataSource(requireContext(), disposable);
        MealsDetailsRepository repository = new MealsDetailsRepository(remoteDataSource, localDataSource);
        presenter = new MealsDetailsPresenterImpl(this, repository);
        currentUser = FirebaseAuth.getInstance().getCurrentUser();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        String mealId = args.getMealID();
        presenter.getAllMealDetailsById(mealId);
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.GONE);
    }

    private void setUpRecyclerview(List<Ingredients> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients, requireContext());
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager = new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter.setList(ingredients);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
    }

    private void playYoutubeVideo(String url) {
        if (url.isEmpty()) {
            Snackbar.make(requireView(), "There is no video available for this meal", Snackbar.LENGTH_LONG).show();
            return;
        }

        String videoId = url.split("v=")[1];
        String embedUrl = "https://www.youtube.com/embed/" + videoId;

        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
        binding.webView.loadUrl(embedUrl);
    }

    @Override
    public void showMealsDetailsById(List<Meal> mealsDetails) {
        Meal meal = mealsDetails.get(0);
        IngredientItem ingredientItem = new IngredientItem();
        Glide.with(getContext()).load(meal.getStrMealThumb()).into(binding.imageView4);
        setUpRecyclerview(ingredientItem.getIngredients(meal));
        binding.tvInstructions.setText(meal.getStrInstructions());
        binding.tvName.setText(meal.getStrMeal());
        binding.tvCountry.setText(meal.getStrArea()+" Meal");
        playYoutubeVideo(meal.getStrYoutube());

        boolean isFavorite = LocalDataSource.isMealFavorite(getContext(), meal.getIdMeal());
        meal.isFavourite = isFavorite;
        updateFavoriteIcon(isFavorite);

        binding.addToFavourite.setOnClickListener(v -> toggleFavoriteStatus(meal));
        binding.addToPlan.setOnClickListener(v -> {
            if (currentUser == null) {
                showMessage("Please log in to add meal to week plan.");
                return;
            }
            DaysOfPlanBinding dialogBinding = DaysOfPlanBinding.inflate(LayoutInflater.from(requireContext()));

            AlertDialog.Builder builder = new AlertDialog.Builder(requireContext());
            builder.setView(dialogBinding.getRoot());
            AlertDialog alertDialog = builder.create();
            alertDialog.show();

            dialogBinding.chipSaturday.setOnClickListener(view -> {
                handleChipClick("Saturday", meal, alertDialog);
            });
            dialogBinding.chipSunday.setOnClickListener(view -> {
                handleChipClick("Sunday", meal, alertDialog);
            });
            dialogBinding.chipMonday.setOnClickListener(view -> {
                handleChipClick("Monday", meal, alertDialog);
            });
            dialogBinding.chipTuesday.setOnClickListener(view -> {
                handleChipClick("Tuesday", meal, alertDialog);
            });
            dialogBinding.chipWednesday.setOnClickListener(view -> {
                handleChipClick("Wednesday", meal, alertDialog);
            });
            dialogBinding.chipThursday2.setOnClickListener(view -> {
                handleChipClick("Thursday", meal, alertDialog);
            });
            dialogBinding.chipFriday.setOnClickListener(view -> {
                handleChipClick("Friday", meal, alertDialog);
            });
        });
    }



    private void handleChipClick(String dayOfMeal, Meal meal, AlertDialog alertDialog) {
        MealPlan mealPlan = new MealPlan();
        mealPlan.setDayOfMeal(dayOfMeal);
        mealPlan.setIdMeal(meal.getIdMeal());
        mealPlan.setStrMeal(meal.getStrMeal());
        mealPlan.setStrMealThumb(meal.getStrMealThumb());
        mealPlan.setUserId(currentUser.getUid());
        presenter.addMealToPlan(currentUser.getUid(),mealPlan);
        showMessage("Meal added to your plan for " + dayOfMeal);
        alertDialog.dismiss();
    }


    private void toggleFavoriteStatus(Meal meal) {
        if (currentUser == null) {
            showMessage("Please log in to add meal to favorites.");
            return;
        }

        meal.isFavourite = !meal.isFavourite;
        updateFavoriteIcon(meal.isFavourite);

        if (meal.isFavourite) {
            addMealToFavorites(meal);
        } else {
            removeMealFromFavorites(meal);
        }
    }

    private void updateFavoriteIcon(boolean isFavorite) {
        binding.addToFavourite.setImageResource(isFavorite ? R.drawable.fill_favorite : R.drawable.favorite_ic);
    }

    private void addMealToFavorites(Meal meal) {
        if (currentUser != null) {
            meal.setUserId(currentUser.getUid());
            presenter.addMealToFavorites(meal);
            LocalDataSource.setMealFavoriteStatus(getContext(), meal.getIdMeal(), true);
        } else {
            showMessage("Please log in to add meal to favorites.");
        }
    }

    private void removeMealFromFavorites(Meal meal) {
        if (currentUser != null) {
            meal.setUserId(currentUser.getUid());
            presenter.deleteMealToFavorites(meal);
            LocalDataSource.setMealFavoriteStatus(getContext(), meal.getIdMeal(), false);
        } else {
            showMessage("Please log in to remove meal from favorites.");
        }
    }

    @Override
    public void showMessage(String message) {
        Snackbar.make(requireView(), message, Snackbar.LENGTH_LONG).show();
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }


}
