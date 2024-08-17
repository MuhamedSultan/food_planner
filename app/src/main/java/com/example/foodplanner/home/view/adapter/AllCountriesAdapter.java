package com.example.foodplanner.home.view.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.foodplanner.R;
import com.example.foodplanner.home.pojo.countries.CountryMeal;

import java.util.List;

public class AllCountriesAdapter extends RecyclerView.Adapter<AllCountriesAdapter.CountriesViewHolder> {
    List<CountryMeal> countryMeals;
    Context context;
    CountryClick countryClick;

    public AllCountriesAdapter(List<CountryMeal> countryMeals, Context context, CountryClick countryClick){
    this.countryMeals=countryMeals;
        this.context=context;
        this.countryClick=countryClick;
    }


    @NonNull
    @Override
    public CountriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.countries_item, parent, false);
        return new CountriesViewHolder(view);

    }

    @Override
    public void onBindViewHolder(@NonNull CountriesViewHolder holder, int position) {
        CountryMeal meals=countryMeals.get(position);
        Glide.with(context).load(meals.getThumbArea()).into(holder.countryImage);
        holder.countryName.setText(meals.getStrArea());
        holder.countryImage.setOnClickListener(v->{
            countryClick.onCountryClick(meals.getStrArea());
        });
    }

    @Override
    public int getItemCount() {
        return countryMeals.size();
    }

    public void setList(List<CountryMeal> countryMeals ){
        this.countryMeals=countryMeals;
    }
    class CountriesViewHolder extends RecyclerView.ViewHolder {
        ImageView countryImage;
        TextView countryName;
        public CountriesViewHolder(@NonNull View itemView) {
            super(itemView);
            countryImage=itemView.findViewById(R.id.countryImage);
            countryName=itemView.findViewById(R.id.tvCountryName);
        }
    }
}
