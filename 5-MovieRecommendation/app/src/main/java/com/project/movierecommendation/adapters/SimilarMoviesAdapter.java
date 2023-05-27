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
import com.project.movierecommendation.models.MovieModel;

import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    List<MovieModel> similarMovieList;
    OnMovieListener onMovieListener;

    public SimilarMoviesAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.similar_movie_item, parent, false);
        return new SimilarMoviesViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {

        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + similarMovieList.get(position).getPoster_path())
                .transform(new FitCenter(), new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((SimilarMoviesViewHolder) holder).moviePoster);
    }

    @Override
    public int getItemCount() {
        if (similarMovieList != null) {
            return similarMovieList.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setSimilarMovies(List<MovieModel> mMovies) {
        this.similarMovieList = mMovies;
        notifyDataSetChanged();
    }

    //Getting the id of the movie Clicked
    public MovieModel getSelectedMovie(int position) {
        if (similarMovieList.size() > 0) {
            return similarMovieList.get(position);
        }
        return null;
    }
}
