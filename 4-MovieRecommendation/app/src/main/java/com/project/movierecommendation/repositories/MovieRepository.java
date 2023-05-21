package com.project.movierecommendation.repositories;

import androidx.lifecycle.LiveData;

import com.project.movierecommendation.models.Cast;
import com.project.movierecommendation.models.Crew;
import com.project.movierecommendation.models.Movie;
import com.project.movierecommendation.models.MovieModel;
import com.project.movierecommendation.models.Review;
import com.project.movierecommendation.request.MovieApiClient;

import java.util.List;

public class MovieRepository {
    //movie repo

    private static MovieRepository instance;

    private MovieApiClient movieApiClient;

    private String mQuery;
    private int mPageNumber;
    private int mMovieId;


    public static MovieRepository getInstance() {
        if (instance == null) {
            instance = new MovieRepository();
        }
        return instance;
    }

    private MovieRepository() {
        movieApiClient = MovieApiClient.getInstance();
    }

    public LiveData<List<MovieModel>> getMovies() {
        return movieApiClient.getMovies();
    }

    public LiveData<List<MovieModel>> getNowPlaying() {
        return movieApiClient.getNowPlaying();
    }

    public LiveData<List<MovieModel>> getSimilarMovies() {
        return movieApiClient.getSimilarMovies();
    }

    public LiveData<Movie> getSingleMovie() {
        return movieApiClient.getSingleMovie();
    }

    public LiveData<List<Cast>> getCasts() {
        return movieApiClient.getCastList();
    }

    public LiveData<List<Crew>> getDirector() {
        return movieApiClient.getCrewList();
    }

    public LiveData<List<Review>> getReviews() {
        return movieApiClient.getReviewList();
    }

    //calling the trending
    public void searchMovieApi(String query, int pageNumber) {
        mQuery = query;
        mPageNumber = pageNumber;
        movieApiClient.searchMoviesApi(query, pageNumber);
    }

    //calling the similar movies'
    public void searchSimilarMovies(int movieId, int pageNumber) {
        mMovieId = movieId;
        mPageNumber = pageNumber;
        movieApiClient.searchSimilarMovies(movieId, pageNumber);

    }

    //calling the Now Playing
    public void searchMovieApiNowPlaying(int pageNumber) {
        movieApiClient.searchMoviesNowPlaying(pageNumber);
    }

    //searching Single Movie
    public void searchMovieSingle(int movieId) {
        movieApiClient.searchSingleMovie(movieId);
    }

    //searching Casts
    public void searchCasts(int movie_Id) {
        movieApiClient.searchCast(movie_Id);
    }

    //searching Reviews
    public void searchReviews(int movie_id, int pageNumber) {
        movieApiClient.searchReview(movie_id, pageNumber);
    }

    //Pagination Support
    public void searchNextPage() {
        searchMovieApi(mQuery, mPageNumber + 1);
    }

    //pagination Support for Similar movies
    public void searchNextPageSimilar() {
        searchSimilarMovies(mMovieId, mPageNumber + 1);
    }

    //pagination Support for review
    public void searchNextPageReviews() {
        searchReviews(mMovieId, mPageNumber + 1);
    }

    //Clean the cast
    public void resetCast() {
        movieApiClient.resetCast();
    }

    //Clean the review
    public void resetReview() {
        movieApiClient.resetReview();
    }


    public LiveData<Boolean> getCastLoading() {
        return movieApiClient.castLoading;
    }

    public  LiveData<Boolean> getLoading(){
        return movieApiClient.movieLoading;
    }

}
