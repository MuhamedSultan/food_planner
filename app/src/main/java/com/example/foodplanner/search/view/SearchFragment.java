package com.example.foodplanner.search.view;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.GridLayoutManager;

import com.example.foodplanner.MainActivity;
import com.example.foodplanner.R;
import com.example.foodplanner.api.RemoteDataSource;
import com.example.foodplanner.databinding.FragmentSearchBinding;
import com.example.foodplanner.home.pojo.categories.Category;
import com.example.foodplanner.home.pojo.countries.CountryMeal;
import com.example.foodplanner.home.pojo.ingredients.IngredientMeal;
import com.example.foodplanner.home.view.adapter.AllCategoriesAdapter;
import com.example.foodplanner.home.view.adapter.AllCountriesAdapter;
import com.example.foodplanner.home.view.adapter.CategoryClick;
import com.example.foodplanner.home.view.adapter.CountryClick;
import com.example.foodplanner.search.presenter.SearchPresenter;
import com.example.foodplanner.search.presenter.SearchPresenterImpl;
import com.example.foodplanner.search.repository.SearchRepository;
import com.example.foodplanner.util.NetworkUtil;
import com.google.android.material.chip.Chip;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers;
import io.reactivex.rxjava3.core.Observable;
import io.reactivex.rxjava3.disposables.CompositeDisposable;
import io.reactivex.rxjava3.schedulers.Schedulers;

public class SearchFragment extends Fragment implements SearchView, CountryClick, CategoryClick {

    private FragmentSearchBinding binding;
    private SearchPresenter presenter;
    private List<IngredientMeal> ingredientMeals;
    private List<Category> categoryMeals;
    private List<CountryMeal> countryMeals;
    CompositeDisposable disposable=new CompositeDisposable();
    private String typeList = "i";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        RemoteDataSource remoteDataSource = new RemoteDataSource();
        SearchRepository repository = new SearchRepository(remoteDataSource);
        presenter = new SearchPresenterImpl(this, repository);

        ingredientMeals = new ArrayList<>();
        countryMeals = new ArrayList<>();
        categoryMeals = new ArrayList<>();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentSearchBinding.inflate(inflater, container, false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ((MainActivity) requireActivity()).binding.bottomNavigationView.setVisibility(View.VISIBLE);
        checkInterNetConnection();
        presenter.getAllIngredients();
        setupChipListeners();

        disposable.add(Observable.create(emitter -> binding.edSearch.addTextChangedListener(new TextWatcher() {
                    @Override
                    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                    }

                    @Override
                    public void onTextChanged(CharSequence s, int start, int before, int count) {
                        List<?> filteredList;
                        String searchText = s.toString().toLowerCase();
                        switch (typeList) {
                            case "i":
                                filteredList = filterIngredientMeals(searchText);
                                break;
                            case "a":
                                filteredList = filterCountryMeals(searchText);
                                break;
                            case "c":
                                filteredList = filterCategoryMeals(searchText);
                                break;
                            default:
                                filteredList = new ArrayList<>();
                                break;
                        }
                        emitter.onNext(filteredList);
                    }

                    @Override
                    public void afterTextChanged(Editable s) {
                    }
                })).debounce(500, TimeUnit.MILLISECONDS)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(filteredList -> updateRecyclerView((List<?>) filteredList),
                        Throwable::printStackTrace));
    }

    private void checkInterNetConnection() {
        disposable.add(
                NetworkUtil.observeNetworkConnectivity(requireContext())
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(isConnected -> {
                            if (isConnected) {
                                showData();
                            } else {
                                showNoInternetAnimation();
                            }
                        }, throwable -> {
                            Log.e("HomeFragment", "Error In network connection", throwable);
                        })
        );
    }


    private void showNoInternetAnimation() {
        binding.lottieAnimationView.setVisibility(View.VISIBLE);
        binding.tvDesc.setVisibility(View.VISIBLE);
        binding.searchRecycler.setVisibility(View.GONE);
        binding.edSearch.setVisibility(View.GONE);
        binding.categoriesChip.setVisibility(View.GONE);
        binding.countriesChip.setVisibility(View.GONE);
        binding.ingredientChip.setVisibility(View.GONE);
    }

    private void showData() {
        binding.lottieAnimationView.setVisibility(View.GONE);
        binding.tvDesc.setVisibility(View.GONE);
        binding.searchRecycler.setVisibility(View.VISIBLE);
        binding.categoriesChip.setVisibility(View.VISIBLE);
        binding.countriesChip.setVisibility(View.VISIBLE);
        binding.ingredientChip.setVisibility(View.VISIBLE);
    }


    private List<IngredientMeal> filterIngredientMeals(String searchText) {
        List<IngredientMeal> filteredMeals = new ArrayList<>();
        for (IngredientMeal meal : ingredientMeals) {
            if (meal.getStrIngredient().toLowerCase().startsWith(searchText)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private List<CountryMeal> filterCountryMeals(String searchText) {
        List<CountryMeal> filteredMeals = new ArrayList<>();
        for (CountryMeal meal : countryMeals) {
            if (meal.getStrArea().toLowerCase().startsWith(searchText)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private List<Category> filterCategoryMeals(String searchText) {
        List<Category> filteredMeals = new ArrayList<>();
        for (Category meal : categoryMeals) {
            if (meal.getStrCategory().toLowerCase().startsWith(searchText)) {
                filteredMeals.add(meal);
            }
        }
        return filteredMeals;
    }

    private void setupChipListeners() {
        Chip ingredientChip = binding.ingredientChip;
        Chip countriesChip = binding.countriesChip;
        Chip categoriesChip = binding.categoriesChip;

        selectChip(ingredientChip);

        ingredientChip.setOnClickListener(v -> {
            if (!ingredientChip.isSelected()) {
                selectChip(ingredientChip);
                deselectChip(countriesChip);
                deselectChip(categoriesChip);
                presenter.getAllIngredients();
                typeList = "i";
            }
        });

        countriesChip.setOnClickListener(v -> {
            if (!countriesChip.isSelected()) {
                selectChip(countriesChip);
                deselectChip(ingredientChip);
                deselectChip(categoriesChip);
                presenter.getAllCountries();
                typeList = "a";
            }
        });

        categoriesChip.setOnClickListener(v -> {
            if (!categoriesChip.isSelected()) {
                selectChip(categoriesChip);
                deselectChip(ingredientChip);
                deselectChip(countriesChip);
                presenter.getAllCategories();
                typeList = "c";
            }
        });
    }

    private void updateRecyclerView(List<?> filteredList) {
        if (typeList.equals("i")) {
            setupIngredientsRecycler((List<IngredientMeal>) filteredList);
        } else if (typeList.equals("a")) {
            setupCountriesRecycler((List<CountryMeal>) filteredList);
        } else {
            setupCategoriesRecycler((List<Category>) filteredList);
        }
    }

    @Override
    public void showAllCountries(List<CountryMeal> countryMeals) {
        this.countryMeals = countryMeals;
        setupCountriesRecycler(countryMeals);
    }

    @Override
    public void showAllCategories(List<Category> categoryMeals) {
        this.categoryMeals = categoryMeals;
        setupCategoriesRecycler(categoryMeals);
    }

    @Override
    public void showAllIngredients(List<IngredientMeal> ingredientMeals) {
        this.ingredientMeals = ingredientMeals;
        setupIngredientsRecycler(ingredientMeals);
    }

    @Override
    public void showMessage(String message) {
    }

    @Override
    public void showLoading() {
    }

    @Override
    public void hideLoading() {
    }

    private void setupIngredientsRecycler(List<IngredientMeal> meals) {
        IngredientsAdapter adapter = new IngredientsAdapter(meals, getContext());
        GridLayoutManager layoutManager = new GridLayoutManager(getContext(), 2);
        binding.searchRecycler.setAdapter(adapter);
        binding.searchRecycler.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }

    private void setupCountriesRecycler(List<CountryMeal> meals) {
        AllCountriesAdapter adapter = new AllCountriesAdapter(meals, requireContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.searchRecycler.setAdapter(adapter);
        binding.searchRecycler.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }

    private void setupCategoriesRecycler(List<Category> meals) {
        AllCategoriesAdapter adapter = new AllCategoriesAdapter(meals, requireContext(), this);
        GridLayoutManager layoutManager = new GridLayoutManager(requireContext(), 2);
        binding.searchRecycler.setAdapter(adapter);
        binding.searchRecycler.setLayoutManager(layoutManager);
        adapter.setList(meals);
    }

    @Override
    public void onCountryClick(String countyName) {
        NavDirections navDirections = SearchFragmentDirections.actionSearchFragmentToCountryRecipesFragment(countyName);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    @Override
    public void onCategoryClick(String categoryName) {
        NavDirections navDirections = SearchFragmentDirections.actionSearchFragmentToCategoryFragment(categoryName);
        Navigation.findNavController(requireView()).navigate(navDirections);
    }

    private void selectChip(Chip chip) {
        chip.setChipBackgroundColorResource(R.color.primary_color);
        chip.setTextColor(getResources().getColor(R.color.white));
        chip.setSelected(true);
    }

    private void deselectChip(Chip chip) {
        chip.setChipBackgroundColorResource(R.color.white);
        chip.setTextColor(getResources().getColor(R.color.black));
        chip.setSelected(false);
    }


    }

