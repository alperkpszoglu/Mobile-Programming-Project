package com.project.movierecommendation.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;
import com.project.movierecommendation.R;

public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    MaterialCardView movieCard;
    TextView movieTitle, movieLanguage, movieReleaseDate, movieRatingText;
    RatingBar movieRating;
    ImageView moviePoster;

    //on Click Listener
    OnMovieListener onMovieListener;

    public MovieViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        this.onMovieListener = onMovieListener;
        movieCard = itemView.findViewById(R.id.movie_item_card);
        movieTitle = itemView.findViewById(R.id.movie_item_title);
        movieLanguage = itemView.findViewById(R.id.movie_item_language);
        movieReleaseDate = itemView.findViewById(R.id.movie_item_release_date);
        movieRatingText = itemView.findViewById(R.id.movie_item_ratings_text);
        movieRating = itemView.findViewById(R.id.movie_item_ratings);
        moviePoster = itemView.findViewById(R.id.movie_item_poster);

        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClick(getAdapterPosition());
    }
}
