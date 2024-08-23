package com.example.foodplanner.calender.view;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.foodplanner.R;
import com.example.foodplanner.calender.pojo.MealPlan;
import com.example.foodplanner.home.pojo.randomMeal.Meal;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class WeekdaysAdapter extends RecyclerView.Adapter<WeekdaysAdapter.WeekdayViewHolder> {

    private List<String> weekdays;
    private OnWeekdayClickListener listener;
    private String selectedDay = null;
    PlanMealClick planMealClick;
    private Map<String, List<MealPlan>> mealsMap = new HashMap<>();

    public WeekdaysAdapter(List<String> weekdays, OnWeekdayClickListener listener,PlanMealClick planMealClick) {
        this.weekdays = weekdays;
        this.listener = listener;
        this.planMealClick=planMealClick;
    }

    @NonNull
    @Override
    public WeekdayViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.week_item, parent, false);
        return new WeekdayViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull WeekdayViewHolder holder, int position) {
        String day = weekdays.get(position);
        holder.dayName.setText(day);

        if (!mealsMap.containsKey(day)) {
            mealsMap.put(day, new ArrayList<>());
        }
        PlanAdapter mealsAdapter = new PlanAdapter(mealsMap.get(day), holder.itemView.getContext(),planMealClick);
        holder.mealsRecyclerView.setAdapter(mealsAdapter);

        holder.itemView.setOnClickListener(v -> {
            if (selectedDay != null && selectedDay.equals(day)) {
                selectedDay = null;
                notifyDataSetChanged();
            } else {
                selectedDay = day;
                notifyDataSetChanged();
                if (listener != null) {
                    listener.onWeekdayClick(day);
                }
            }
        });

        if (day.equals(selectedDay)) {
            holder.mealsRecyclerView.setVisibility(View.VISIBLE);
            if (mealsMap.get(day).isEmpty()) {
                holder.noItemsTextView.setVisibility(View.VISIBLE);
            } else {
                holder.noItemsTextView.setVisibility(View.GONE);
            }
        } else {
            holder.mealsRecyclerView.setVisibility(View.GONE);
            holder.noItemsTextView.setVisibility(View.GONE);
        }
    }
    @Override
    public int getItemCount() {
        return weekdays.size();
    }

    public void updateMeals(String day, List<MealPlan> meals) {
        mealsMap.put(day, meals);
        notifyDataSetChanged();
    }

    public interface OnWeekdayClickListener {
        void onWeekdayClick(String weekday);
    }

    public static class WeekdayViewHolder extends RecyclerView.ViewHolder {
        TextView dayName;
        RecyclerView mealsRecyclerView;
        TextView noItemsTextView;

        public WeekdayViewHolder(@NonNull View itemView) {
            super(itemView);
            dayName = itemView.findViewById(R.id.dayName);
            mealsRecyclerView = itemView.findViewById(R.id.mealsRecyclerView);
            noItemsTextView = itemView.findViewById(R.id.noItemsTextView);
            mealsRecyclerView.setLayoutManager(new LinearLayoutManager(itemView.getContext()));
        }
    }
}
