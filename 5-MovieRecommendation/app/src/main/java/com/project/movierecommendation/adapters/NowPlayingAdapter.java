package com.project.movierecommendation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.R;
import com.project.movierecommendation.models.MovieModel;

import java.util.List;

public class NowPlayingAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    List<MovieModel> mMovieList;
    private OnMovieListener onMovieListener;

    public NowPlayingAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_view_pager_item, parent, false);
        return new NowPlayingViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        //image using glide
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + mMovieList.get(position).getBackdrop_path())
                .transform(new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((NowPlayingViewHolder) holder).poster);

        ((NowPlayingViewHolder) holder).title.setText(mMovieList.get(position).getTitle());
    }

    @Override
    public int getItemCount() {
        if (mMovieList != null) {
            return mMovieList.size();
        }
        return 0;
    }

    //Getting the id of the movie Clicked
    public MovieModel getSelectedMovie(int position) {
        if (mMovieList.size() > 0) {
            return mMovieList.get(position);
        }
        return null;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setNowPlayingMovies(List<MovieModel> mMovies) {
        this.mMovieList = mMovies;
        notifyDataSetChanged();
    }

}
