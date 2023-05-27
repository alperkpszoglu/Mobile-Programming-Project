package com.project.movierecommendation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.FitCenter;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.R;
import com.project.movierecommendation.models.Cast;

import java.util.List;

public class CastAdapters extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<Cast> castList;


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cast_item, parent, false);
        return new CastViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {//image using glide
        ((CastViewHolder) holder).loading.setVisibility(View.VISIBLE);
        ((CastViewHolder) holder).cast_name.setText(castList.get(position).getOriginalName());
        ((CastViewHolder) holder).cast_character.setText(castList.get(position).getCharacter());


        if (castList.get(position).getProfilePath() == null) {
            ((CastViewHolder) holder).loading.setVisibility(View.GONE);
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.ic_user)
                    .transform(new FitCenter(), new RoundedCorners(28))
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(((CastViewHolder) holder).cast_image);
            return;
        }

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + castList.get(position).getProfilePath())
                .transform(new FitCenter(), new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((CastViewHolder) holder).cast_image);


    }

    @Override
    public int getItemCount() {
        if (castList != null) {
            return castList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setCastList(List<Cast> castList) {
        this.castList = castList;
        notifyDataSetChanged();
    }
}
