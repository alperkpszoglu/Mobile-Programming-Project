package com.project.movierecommendation;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.project.movierecommendation.adapters.CastAdapters;
import com.project.movierecommendation.models.Cast;
import com.project.movierecommendation.models.Crew;
import com.project.movierecommendation.models.MovieModel;
import com.project.movierecommendation.viewmodels.MovieListViewModel;

import java.util.List;

public class CastActivity extends AppCompatActivity {

    RecyclerView castCycle;
    CastAdapters castAdapter;
    MovieListViewModel movieListViewModel;
    ImageView back_icon;
    RelativeLayout loadingLayout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cast);
        castCycle = findViewById(R.id.cast_recyclerview);
        back_icon = findViewById(R.id.back_icon_cast);
        loadingLayout = findViewById(R.id.loading);

        movieListViewModel = new ViewModelProvider(this).get(MovieListViewModel.class);

        //setup RecycleView
        setupRecyclerView();

        //get data from intent
        getDataFromIntent();

        //observe the changes in view model
        observeChangesCast();

        back_icon.setOnClickListener(view -> {
            movieListViewModel.resetCast();
            finish();
        });

        movieListViewModel.getCastLoading().observe(this, loading -> {
            if (loading) {
                loadingLayout.setVisibility(View.VISIBLE);
            } else {
                loadingLayout.setVisibility(View.GONE);
            }
        });

    }

    private void getDataFromIntent() {
        if (getIntent().hasExtra("movie")) {
            MovieModel movieModel = getIntent().getParcelableExtra("movie");
            getCasts(movieModel.getId());
        }
    }

    private void observeChangesCast() {
        movieListViewModel.getCasts().observe(this, new Observer<List<Cast>>() {
            @Override
            public void onChanged(List<Cast> casts) {
                if (casts != null) {
                    for (Cast cast : casts) {
                        castAdapter.setCastList(casts);
                    }

                }
            }
        });

        movieListViewModel.getDirector().observe(this, new Observer<List<Crew>>() {
            @Override
            public void onChanged(List<Crew> crews) {
                if (crews != null) {
                    for (Crew crew : crews) {
                        if (crew.getJob().equals("Director")) {
                            //can add directors on the list
                        }
                    }
                }
            }
        });
    }

    public void setupRecyclerView() {
        castAdapter = new CastAdapters();
        castCycle.setAdapter(castAdapter);
        castCycle.setLayoutManager(new GridLayoutManager(this, 2));
    }

    private void getCasts(int movie_id) {
        movieListViewModel.searchMovieApiCast(movie_id);
    }

    @Override
    protected void onStop() {
        super.onStop();

    }
}