package com.project.movierecommendation.adapters;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.CenterCrop;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.R;
import com.project.movierecommendation.models.MovieModel;

import java.util.List;
import java.util.Locale;

public class MovieRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<MovieModel> mMovies;
    private OnMovieListener onMovieListener;

    public MovieRecyclerViewAdapter(OnMovieListener onMovieListener) {
        this.onMovieListener = onMovieListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
        return new MovieViewHolder(view, onMovieListener);
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ((MovieViewHolder) holder).movieTitle.setText(mMovies.get(position).getTitle());

        ((MovieViewHolder) holder).movieLanguage.setText(getFullLanguageFormat(mMovies.get(position).getOriginal_language()));
        ((MovieViewHolder) holder).movieRatingText.setText(String.valueOf(mMovies.get(position).getVote_average()));
        ((MovieViewHolder) holder).movieRating.setRating(mMovies.get(position).getVote_average() / 2);
        ((MovieViewHolder) holder).movieReleaseDate.setText(mMovies.get(position).getRelease_date());

        //image using glide
        Glide.with(holder.itemView.getContext())
                .load("https://image.tmdb.org/t/p/w500/" + mMovies.get(position).getPoster_path())
                .transform(new CenterCrop(), new RoundedCorners(28))
                .transition(DrawableTransitionOptions.withCrossFade())
                .into(((MovieViewHolder) holder).moviePoster);


    }

    //converting 'en' to 'English,
    private String getFullLanguageFormat(String language) {
        Locale loc = new Locale(language);
        return "Dil : " + loc.getDisplayLanguage(Locale.ENGLISH);
    }

    @Override
    public int getItemCount() {
        if (mMovies != null) {
            return mMovies.size();
        }
        return 0;
    }

    @SuppressLint("NotifyDataSetChanged")
    public void setmMovies(List<MovieModel> mMovies) {
        this.mMovies = mMovies;
        notifyDataSetChanged();
    }

    //Getting the id of the movie Clicked
    public MovieModel getSelectedMovie(int position) {
        if (mMovies.size() > 0) {
            return mMovies.get(position);
        }
        return null;
    }

}
