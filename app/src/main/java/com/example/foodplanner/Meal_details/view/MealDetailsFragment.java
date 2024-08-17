package com.example.foodplanner.Meal_details.view;

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
import com.example.foodplanner.Meal_details.pojo.IngredientItem;
import com.example.foodplanner.Meal_details.pojo.Ingredients;
import com.example.foodplanner.Meal_details.presenter.MealsDetailsPresenter;
import com.example.foodplanner.Meal_details.presenter.MealsDetailsPresenterImpl;
import com.example.foodplanner.Meal_details.repository.MealsDetailsRepository;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.databinding.FragmentMealDetailsBinding;
import com.example.foodplanner.home.pojo.randomMeal.Meal;
import com.google.android.material.snackbar.Snackbar;

import java.util.List;


public class MealDetailsFragment extends Fragment implements MealsDetailsView{
    private FragmentMealDetailsBinding binding;
    private MealsDetailsPresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource=new RemoteDataSource();
        MealsDetailsRepository repository=new MealsDetailsRepository(remoteDataSource);
        presenter=new MealsDetailsPresenterImpl(this,repository);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentMealDetailsBinding.inflate(getLayoutInflater());
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        MealDetailsFragmentArgs args = MealDetailsFragmentArgs.fromBundle(getArguments());
        String mealId = args.getMealID();


        presenter.getAllMealDetailsById(mealId);

    }

    private void setUpRecyclerview(List<Ingredients> ingredients) {
        IngredientsAdapter adapter = new IngredientsAdapter(ingredients, requireContext());
        binding.recyclerView.setAdapter(adapter);
        LinearLayoutManager layoutManager=new LinearLayoutManager(requireContext());
        binding.recyclerView.setLayoutManager(layoutManager);
        adapter.setList(ingredients);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
    }
    private void playYoutubeVideo(String url) {
        String videoId = "";
        if (!url.isEmpty()){
             videoId = url.split("v=")[1];
        }else {
            Snackbar.make(requireView(),"There is no video Available for this meal",Snackbar.LENGTH_LONG).show();
        }
        String embedUrl = "https://www.youtube.com/embed/" + videoId;


        binding.webView.getSettings().setJavaScriptEnabled(true);
        binding.webView.setWebChromeClient(new WebChromeClient());
        binding.webView.setWebViewClient(new WebViewClient());
        binding.webView.loadUrl(embedUrl);
        binding.webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                return false;
            }
        });
    }

    @Override
    public void showMealsDetailsById(List<Meal> mealsDetails) {
        Meal meal = mealsDetails.get(0);
        IngredientItem ingredientItem = new IngredientItem();
        Glide.with(requireContext()).load(meal.getStrMealThumb()).into(binding.imageView4);
        setUpRecyclerview(ingredientItem.getIngredients(meal));
        binding.tvInstructions.setText(meal.getStrInstructions());
        playYoutubeVideo(meal.getStrYoutube());
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
}