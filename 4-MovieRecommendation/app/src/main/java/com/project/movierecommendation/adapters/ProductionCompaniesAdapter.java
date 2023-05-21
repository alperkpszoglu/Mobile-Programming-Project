package com.project.movierecommendation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.R;
import com.project.movierecommendation.models.ProductionCompany;

import java.util.List;

public class ProductionCompaniesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ProductionCompany> companyList;



    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.production_companies_item, parent, false);
        return new ProductionCompaniesViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        holder.setIsRecyclable(false);

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + companyList.get(position).getLogoPath())
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((ProductionCompaniesViewHolder) holder).company_logo);
        if (companyList.get(position).getLogoPath() == null) {
            ((ProductionCompaniesViewHolder) holder).company_name.setText(companyList.get(position).getName());
        }
    }

    @Override
    public int getItemCount() {
        if (companyList != null) {
            return companyList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCompanies(List<ProductionCompany> companies) {
        this.companyList = companies;
        notifyDataSetChanged();
    }
}
