package com.example.foodplanner.home.view;

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
import com.example.foodplanner.home.pojo.Category;

import java.util.List;

public class AllCategoriesAdapter extends RecyclerView.Adapter<AllCategoriesAdapter.AllCategoriesViewHolder> {
    List<Category> categories;
    Context context;
    public AllCategoriesAdapter(List<Category> categories,Context context){
        this.categories=categories;
        this.context=context;
    }
    @NonNull
    @Override
    public AllCategoriesViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.all_categories_item,parent,false);
        return new AllCategoriesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCategoriesViewHolder holder, int position) {
        Category category=categories.get(position);
        Glide.with(context).load(category.getStrCategoryThumb()).into(holder.categoryImage);
        holder.tvCategory.setText(category.getStrCategory());
    }

    @Override
    public int getItemCount() {
        return categories.size();
    }
    public void setList(List<Category> categories){
        this.categories=categories;
        notifyDataSetChanged();
    }

    class AllCategoriesViewHolder extends RecyclerView.ViewHolder{
        ImageView categoryImage;
        TextView tvCategory;
        public AllCategoriesViewHolder(@NonNull View itemView) {
            super(itemView);
            categoryImage=itemView.findViewById(R.id.categoryImage);
            tvCategory=itemView.findViewById(R.id.tvCategoryName);
        }
    }
}
