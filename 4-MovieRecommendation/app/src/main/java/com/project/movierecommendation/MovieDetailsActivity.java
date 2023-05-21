package com.project.movierecommendation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.project.movierecommendation.adapters.OnMovieListener;
import com.project.movierecommendation.adapters.ProductionCompaniesAdapter;
import com.project.movierecommendation.adapters.SimilarMoviesAdapter;
import com.project.movierecommendation.models.Movie;
import com.project.movierecommendation.models.MovieModel;
import com.project.movierecommendation.models.ProductionCompany;
import com.project.movierecommendation.viewmodels.MovieListViewModel;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class MovieDetailsActivity extends AppCompatActivity implements OnMovieListener {

    TextView movie_title, movie_description, movie_language, movie_release_date, movie_release_status;
    ImageView movie_poster;
    RatingBar movie_rating;
    RecyclerView productionCycle, similarCycle;
    MovieListViewModel movieListViewModel;
    ArrayList<ProductionCompany> companyList = new ArrayList<>();
    ProductionCompaniesAdapter companiesAdapter;
    ArrayList<MovieModel> similarMovieList = new ArrayList<>();
    SimilarMoviesAdapter similarMoviesAdapter;
    Button cast_btn, review_btn;
    MovieModel movieModel;
    ImageView fav, back_icon;
    boolean fav_movie = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_details);

        movie_title = findViewById(R.id.movie_details_title);
        movie_description = findViewById(R.id.movie_details_synopsis);
        movie_language = findViewById(R.id.movie_details_language);
        movie_release_date = findViewById(R.id.movie_details_release_date);
        movie_release_status = findViewById(R.id.movie_details_release_status);
        movie_poster = findViewById(R.id.movie_detail_poster);
        movie_rating = findViewById(R.id.movie_detail_ratings);
        similarCycle = findViewById(R.id.similar_movies_recycler_view);
        productionCycle = findViewById(R.id.movie_details_production_house_recyclerview);
        productionCycle.setAdapter(companiesAdapter);
        similarCycle.setAdapter(similarMoviesAdapter);
        cast_btn = findViewById(R.id.cast_btn);
        review_btn = findViewById(R.id.review_btn);
        fav = findViewById(R.id.favourite_btn);
        fav.setBackgroundResource(R.drawable.no_heart);
        back_icon = findViewById(R.id.back_icon_details);

        //setting Companies recycler-view
        setUpRecyclerView();

        //view-model
        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);
        getDataFromIntent();

        //observe for production house
        observeSingleMovieChange();
        //observe for similar
        observeSimilarMovieChange();

        //further flow
        cast_btn.setOnClickListener(view -> {
            Intent castIntent = new Intent(MovieDetailsActivity.this, CastActivity.class);
            castIntent.putExtra("movie", movieModel);
            startActivity(castIntent);
        });

        review_btn.setOnClickListener(view -> {

            Intent reviewIntent = new Intent(MovieDetailsActivity.this, ReviewActivity.class);
            reviewIntent.putExtra("movie", movieModel);
            startActivity(reviewIntent);
        });

        fav.setOnClickListener(view -> {
            fav_movie = !fav_movie;
            if (fav_movie) {
                fav.setBackgroundResource(R.drawable.ic_heart_filled);
            } else {
                fav.setBackgroundResource(R.drawable.no_heart);
            }
        });
        back_icon.setOnClickListener(view -> {
            finish();
        });

    }

    private void setUpRecyclerView() {

        companiesAdapter = new ProductionCompaniesAdapter();
        productionCycle.setAdapter(companiesAdapter);


        similarMoviesAdapter = new SimilarMoviesAdapter(this);
        similarCycle.setAdapter(similarMoviesAdapter);

        //Pagination support
        similarCycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!similarCycle.canScrollHorizontally(1)) {
                    //here we need to get another page of data
                    movieListViewModel.searchNextPageSimilar();
                }
            }
        });

    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            movieModel = getIntent().getParcelableExtra("movie");
            movie_title.setText(movieModel.getTitle());
            movie_release_date.setText(movieModel.getRelease_date());
            movie_language.setText(getFullLanguageFormat(movieModel.getOriginal_language()));
            movie_description.setText(movieModel.getOverview());
            Glide.with(this)
                    .load("https://image.tmdb.org/t/p/w500/" + movieModel.getBackdrop_path())
                    .transition(DrawableTransitionOptions.withCrossFade())
                    .into(movie_poster);
            movie_rating.setRating(movieModel.getVote_average() / 2);
            searchSingleMovie(movieModel.getId());
            searchSimilarMovies(movieModel.getId(), 1);
        }
    }


    //data stuff
    private void searchSingleMovie(int movieId) {
        movieListViewModel.searchMovieApiSingle(movieId);
    }

    private void observeSingleMovieChange() {
        movieListViewModel.getSingleMovie().observe(this, new Observer<Movie>() {
            @Override
            public void onChanged(Movie movie) {
                if (movie != null) {
                    companiesAdapter.setCompanies(movie.getProductionCompanies());
                }
            }
        });
    }

    private void searchSimilarMovies(int movieId, int pageNumber) {
        movieListViewModel.searchMovieApiSimilar(movieId, pageNumber);
    }

    private void observeSimilarMovieChange() {
        movieListViewModel.getSimilarMovies().observe(this, new Observer<List<MovieModel>>() {
            @Override
            public void onChanged(List<MovieModel> movieModels) {
                if (movieModels != null) {
                    for (MovieModel movieModel : movieModels) {
                        similarMoviesAdapter.setSimilarMovies(movieModels);
                    }
                }
            }
        });
    }

    //utils
    private String getFullLanguageFormat(String language) {
        Locale loc = new Locale(language);
        return "Dil : " + loc.getDisplayLanguage(Locale.ENGLISH);
    }

    @Override
    public void onMovieClick(int position) {
        //sending data to detail intent
        finish();
        Intent detailIntent = new Intent(MovieDetailsActivity.this, MovieDetailsActivity.class);
        detailIntent.putExtra("movie", similarMoviesAdapter.getSelectedMovie(position));
        startActivity(detailIntent);


    }

    @Override
    public void onMovieClickNowPlaying(int position) {
        //nothing
    }
}