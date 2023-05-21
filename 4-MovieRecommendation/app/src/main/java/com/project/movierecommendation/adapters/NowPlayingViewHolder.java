package com.project.movierecommendation.adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.project.movierecommendation.R;

public class NowPlayingViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
    //on Click Listener
    OnMovieListener onMovieListener;

    ImageView poster;
    TextView title;

    public NowPlayingViewHolder(@NonNull View itemView, OnMovieListener onMovieListener) {
        super(itemView);
        poster = itemView.findViewById(R.id.view_pager_movie_poster);
        title = itemView.findViewById(R.id.view_pager_movie_title);
        this.onMovieListener = onMovieListener;
        itemView.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        onMovieListener.onMovieClickNowPlaying(getAdapterPosition());
    }
}
