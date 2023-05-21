package com.project.movierecommendation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.project.movierecommendation.adapters.ReviewAdapter;
import com.project.movierecommendation.models.MovieModel;
import com.project.movierecommendation.models.Review;
import com.project.movierecommendation.viewmodels.MovieListViewModel;

import java.util.List;

public class ReviewActivity extends AppCompatActivity {
    RecyclerView reviewCycle;
    ReviewAdapter reviewAdapter;
    MovieListViewModel movieListViewModel;
    MovieModel movieModel;
    LinearLayout No_data;
    ImageView back_icon;
    boolean MANUAL_RESET = false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);
        reviewCycle = findViewById(R.id.review_recyclerview);
        No_data = findViewById(R.id.no_data_layout);
        back_icon = findViewById(R.id.back_icon_review);
        No_data.setVisibility(View.VISIBLE);


        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //setup recyclerview
        setUpRecyclerview();

        //observe the changes in view model
        observeReviewChanges();
        //getting id from the intent for reviews
        getDataFromIntent();

        back_icon.setOnClickListener(view -> {
            MANUAL_RESET = true;
            movieListViewModel.resetReview();
            finish();
        });
    }

    private void getDataFromIntent() {

        if (getIntent().hasExtra("movie")) {
            movieModel = getIntent().getParcelableExtra("movie");
            getReviews(movieModel.getId(), 1);
        }
    }

    private void getReviews(int movie_id, int pageNumber) {
        movieListViewModel.searchMovieApiReview(movie_id, pageNumber);
    }

    private void observeReviewChanges() {
        movieListViewModel.getReviews().observe(this, new Observer<List<Review>>() {
            @Override
            public void onChanged(List<Review> reviews) {
                if (!MANUAL_RESET) {
                    if (reviews == null) {
                        No_data.setVisibility(View.VISIBLE);
                        reviewCycle.setVisibility(View.GONE);
                        return;
                    }
                    if (reviews.size() == 0) {
                        No_data.setVisibility(View.VISIBLE);
                        reviewCycle.setVisibility(View.GONE);
                        return;
                    }
                    if (reviews != null) {
                        for (Review review : reviews) {

                            reviewAdapter.setReviewList(reviews);
                        }
                        reviewCycle.setVisibility(View.VISIBLE);
                        No_data.setVisibility(View.GONE);
                    }
                }

            }
        });


    }

    private void setUpRecyclerview() {
        reviewAdapter = new ReviewAdapter();
        reviewCycle.setAdapter(reviewAdapter);


        //Pagination support
        reviewCycle.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrollStateChanged(@NonNull RecyclerView recyclerView, int newState) {
                super.onScrollStateChanged(recyclerView, newState);
                if (!reviewCycle.canScrollVertically(1)) {
                    //here we need to get another page of data
                    movieListViewModel.searchNextPageReviews();
                }
            }
        });
    }


}