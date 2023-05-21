package com.project.movierecommendation.adapters;

import android.view.View;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.movierecommendation.R;

public class SimilarMoviesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    ImageView moviePoster;
    OnMovieListener onMovieListener;

    public SimilarMoviesViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        moviePoster = itemView.findViewById(R.id.similar_movie_item_poster);

        this.onMovieListener = onMovieListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
