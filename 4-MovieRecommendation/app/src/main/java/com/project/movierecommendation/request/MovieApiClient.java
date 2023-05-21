package com.project.movierecommendation.request;

import android.util.Log;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;

import com.project.movierecommendation.AppExecutors;
import com.project.movierecommendation.models.Cast;
import com.project.movierecommendation.models.Crew;
import com.project.movierecommendation.models.Movie;
import com.project.movierecommendation.models.MovieModel;
import com.project.movierecommendation.models.Review;
import com.project.movierecommendation.response.CastResponse;
import com.project.movierecommendation.response.MovieSearchResponse;
import com.project.movierecommendation.response.ReviewResponse;
import com.project.movierecommendation.utils.Credentials;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

import retrofit2.Call;
import retrofit2.Response;

public class MovieApiClient {
    //live data
    private MutableLiveData<List<MovieModel>> mMovies;
    private MutableLiveData<List<MovieModel>> mMoviesNowPlaying;
    private MutableLiveData<Movie> mSingleMovieModel;
    private MutableLiveData<List<MovieModel>> mSimilarMovieList;
    private MutableLiveData<List<Cast>> mCastList;
    private MutableLiveData<List<Review>> mReviewList;
    private MutableLiveData<List<Crew>> mCrewList;

    public MutableLiveData<Boolean> movieLoading;
    public MutableLiveData<Boolean> reviewLoading;
    public MutableLiveData<Boolean> castLoading;
    public MutableLiveData<Boolean> singleMovieLoading;
    public  MutableLiveData<Boolean> nowPlayingLoading;
    public MutableLiveData<Boolean> similarMovieLoading;


    private static MovieApiClient instance;

    //making Global Runnable
    private RetrieveMoviesRunnable retrieveMoviesRunnable;
    private RetrieveMoviesNowPlayingRunnable retrieveMoviesNowPlayingRunnable;
    private RetrieveSingleMovie retrieveSingleMovie;
    private RetrieveSimilarMovies retrieveSimilarMovies;
    private RetrieveCast retrieveCast;
    private RetrieveReview retrieveReview;


    public static MovieApiClient getInstance() {
        if (instance == null) {
            instance = new MovieApiClient();
        }
        return instance;
    }

    private MovieApiClient() {
        mMovies = new MutableLiveData<>();
        mMoviesNowPlaying = new MutableLiveData<>();
        mSingleMovieModel = new MutableLiveData<>();
        mSimilarMovieList = new MutableLiveData<>();
        mCastList = new MutableLiveData<>();
        mReviewList = new MutableLiveData<>();
        mCrewList = new MutableLiveData<>();

        movieLoading = new MutableLiveData<>(true);
        reviewLoading = new MutableLiveData<>(true);
        castLoading = new MutableLiveData<>(true);
        singleMovieLoading = new MutableLiveData<>(true);
        nowPlayingLoading = new MutableLiveData<>(true);
        similarMovieLoading = new MutableLiveData<>(true);

    }

    public MutableLiveData<List<Cast>> getCastList() {
        return mCastList;
    }

    public LiveData<List<MovieModel>> getMovies() {
        return mMovies;
    }

    public LiveData<List<MovieModel>> getNowPlaying() {
        return mMoviesNowPlaying;
    }

    public LiveData<Movie> getSingleMovie() {
        return mSingleMovieModel;
    }

    public LiveData<List<MovieModel>> getSimilarMovies() {
        return mSimilarMovieList;
    }

    public MutableLiveData<List<Review>> getReviewList() {
        return mReviewList;
    }

    public MutableLiveData<List<Crew>> getCrewList() {
        return mCrewList;
    }

    //handlers for runnable
    public void searchMoviesApi(String query, int pageNumber) {
        if (retrieveMoviesRunnable != null) {
            retrieveMoviesRunnable = null;
        }
        retrieveMoviesRunnable = new RetrieveMoviesRunnable(query, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveMoviesRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);

    }

    public void searchMoviesNowPlaying(int pageNumber) {

        if (retrieveMoviesNowPlayingRunnable != null) {
            retrieveMoviesNowPlayingRunnable = null;
        }

        retrieveMoviesNowPlayingRunnable = new RetrieveMoviesNowPlayingRunnable(pageNumber);

        final Future myHandler2 = AppExecutors.getInstance().networkIO().submit(retrieveMoviesNowPlayingRunnable);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                // Cancelling the retrofit call
                myHandler2.cancel(true);

            }
        }, 3000, TimeUnit.MILLISECONDS);


    }

    public void searchSingleMovie(int movieId) {

        if (retrieveSingleMovie != null) {
            retrieveSingleMovie = null;
        }
        retrieveSingleMovie = new RetrieveSingleMovie(movieId);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveSingleMovie);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                //cancelling the call
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);


    }

    public void searchSimilarMovies(int movieId, int pageNumber) {
        if (retrieveSimilarMovies != null) {
            retrieveSimilarMovies = null;
        }
        retrieveSimilarMovies = new RetrieveSimilarMovies(movieId, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveSimilarMovies);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);


    }

    public void searchCast(int movie_Id) {
        if (retrieveCast != null) {
            retrieveCast = null;
        }
        retrieveCast = new RetrieveCast(movie_Id);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveCast);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }

    public void searchReview(int movie_Id, int pageNumber) {
        if (retrieveReview != null) {
            retrieveReview = null;
        }
        retrieveReview = new RetrieveReview(movie_Id, pageNumber);

        final Future myHandler = AppExecutors.getInstance().networkIO().submit(retrieveReview);

        AppExecutors.getInstance().networkIO().schedule(new Runnable() {
            @Override
            public void run() {
                myHandler.cancel(true);
            }
        }, 3000, TimeUnit.MILLISECONDS);
    }


    //execution of retrieval of data from api in background thread

    private class RetrieveSingleMovie implements Runnable {

        private int movieId;
        boolean cancelRequest;

        public RetrieveSingleMovie(int movieId) {

            this.movieId = movieId;
            cancelRequest = false;
            singleMovieLoading.postValue(true);
        }

        @Override
        public void run() {
            //Getting the response
            {
                try {
                    singleMovieLoading.postValue(true);
                    Response response = getMovie(movieId).execute();
                    if (cancelRequest) {
                        singleMovieLoading.postValue(false);
                        return;
                    }
                    if (response.code() == 200) {
                        singleMovieLoading.postValue(false);
                        Movie model = (Movie) response.body();
                        mSingleMovieModel.postValue(model);
                    } else {
                        singleMovieLoading.postValue(false);
                        String error = response.errorBody().string();
                        Log.v("TAG", "Error " + error);
                        mSingleMovieModel.postValue(null);
                    }
                } catch (IOException e) {
                    singleMovieLoading.postValue(false);
                    e.printStackTrace();
                }
            }


        }

        //Single Movie Call
        private Call<Movie> getMovie(int movieId) {
            return Servicey.getMovieApi().getMovie(movieId,
                    Credentials.API_KEY);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }

    private class RetrieveMoviesNowPlayingRunnable implements Runnable {

        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesNowPlayingRunnable(int pageNumber) {
            nowPlayingLoading.postValue(true);
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response
            {
                try {
                    nowPlayingLoading.postValue(true);
                    Response response = getMovies(pageNumber).execute();
                    if (cancelRequest) {
                        nowPlayingLoading.postValue(false);
                        return;
                    }
                    if (response.code() == 200) {
                        nowPlayingLoading.postValue(false);
                        List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                        if (pageNumber == 1) {
                            //sending to live data
                            //post value is for background
                            //set value is for background
                            mMoviesNowPlaying.postValue(list);
                        } else {
                            nowPlayingLoading.postValue(false);
                            List<MovieModel> currentMovies = mMoviesNowPlaying.getValue();
                            currentMovies.addAll(list);
                            mMoviesNowPlaying.postValue(currentMovies);

                        }
                    } else {
                        nowPlayingLoading.postValue(false);
                        String error = response.errorBody().string();
                        Log.v("TAG", "Error " + error);
                        mMoviesNowPlaying.postValue(null);
                    }
                } catch (IOException e) {
                    nowPlayingLoading.postValue(false);
                    e.printStackTrace();
                }
            }


        }

        //Now Playing query
        private Call<MovieSearchResponse> getMovies(int pageNumber) {
            return Servicey.getMovieApi().getNowPlaying(
                    Credentials.API_KEY,
                    pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }

    private class RetrieveMoviesRunnable implements Runnable {

        private String query;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveMoviesRunnable(String query, int pageNumber) {
            movieLoading.postValue(true);
            this.query = query;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response
            {
                try {
                    movieLoading.postValue(true);
                    Response response = getMovies(query, pageNumber).execute();
                    if (cancelRequest) {
                        return;
                    }
                    if (response.code() == 200) {
                        movieLoading.postValue(false);
                        List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                        if (pageNumber == 1) {
                            //sending to live data
                            //post value is for background
                            //set value is for background
                            mMovies.postValue(list);
                        } else {
                            List<MovieModel> currentMovies = mMovies.getValue();
                            currentMovies.addAll(list);
                            mMovies.postValue(currentMovies);

                        }
                    } else {
                        movieLoading.postValue(false);
                        String error = response.errorBody().string();
                        Log.v("TAG", "Error " + error);
                        mMovies.postValue(null);
                    }
                } catch (IOException e) {
                    movieLoading.postValue(false);
                    e.printStackTrace();
                }
            }


        }

        //Trending Query
        private Call<MovieSearchResponse> getMovies(String query, int pageNumber) {
            return Servicey.getMovieApi().getTrending(
                    Credentials.API_KEY,
                    pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }

    private class RetrieveSimilarMovies implements Runnable {

        private int movieId;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveSimilarMovies(int movieId, int pageNumber) {
            similarMovieLoading.postValue(true);
            this.movieId = movieId;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            try {
                similarMovieLoading.postValue(true);
                Response response = getSimilarMovies(movieId, pageNumber).execute();
                if (response.code() == 200) {
                    similarMovieLoading.postValue(false);
                    List<MovieModel> list = new ArrayList<>(((MovieSearchResponse) response.body()).getMovies());
                    if (pageNumber == 1) {
                        //sending to live data
                        // post value is for background
                        //set value is for background
                        mSimilarMovieList.postValue(list);
                    } else {
                        similarMovieLoading.postValue(false);
                        List<MovieModel> currentMovies = mSimilarMovieList.getValue();
                        currentMovies.addAll(list);
                        mSimilarMovieList.postValue(currentMovies);
                    }
                }

            } catch (IOException e) {
                similarMovieLoading.postValue(false);
                e.printStackTrace();
            }

        }

        private Call<MovieSearchResponse> getSimilarMovies(int movieId, int pageNumber) {
            return Servicey.getMovieApi().getSimilarMovies(
                    movieId,
                    Credentials.API_KEY,
                    pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }

    private class RetrieveCast implements Runnable {

        int movie_id;
        boolean cancelRequest;

        public RetrieveCast(int movie_id) {
            castLoading.postValue(true);
            this.movie_id = movie_id;
            cancelRequest = false;
        }


        @Override
        public void run() {
            try {
                castLoading.postValue(true);
                Response response = getCasts(movie_id).execute();
                if (response.code() == 200) {
                    castLoading.postValue(false);
                    List<Cast> list = new ArrayList<>(((CastResponse) response.body()).getCast());
                    //getting the director alone
                    List<Crew> director = new ArrayList<>(((CastResponse) response.body()).getCrew());
                    mCastList.postValue(list);
                    mCrewList.postValue(director);
                } else {
                    mCastList.postValue(null);
                    mCrewList.postValue(null);
                    castLoading.postValue(false);
                }
            } catch (IOException e) {
                castLoading.postValue(false);
                mCastList.postValue(null);
                mCrewList.postValue(null);
                e.printStackTrace();
            }

        }

        private Call<CastResponse> getCasts(int movie_id) {
            return Servicey.getMovieApi().getCasts(movie_id,
                    Credentials.API_KEY);
        }


        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }

    private class RetrieveReview implements Runnable {

        private int movie_id;
        private int pageNumber;
        boolean cancelRequest;

        public RetrieveReview(int movie_id, int pageNumber) {
            reviewLoading.postValue(true);
            this.movie_id = movie_id;
            this.pageNumber = pageNumber;
            cancelRequest = false;
        }

        @Override
        public void run() {
            //Getting the response
            {
                try {
                    reviewLoading.postValue(true);
                    Response response = getReviews(movie_id, pageNumber).execute();
                    if (cancelRequest) {
                        return;
                    }
                    if (response.code() == 200) {
                        Log.d("REVIEWS", "run: " + response.body().toString());
                        reviewLoading.postValue(false);
                        List<Review> list = new ArrayList<>(((ReviewResponse) response.body()).getResults());
                        if (pageNumber == 1) {
                            if (list.size() == 0) {
                                mReviewList.postValue(null);
                            }
                            //sending to live data
                            //post value is for background
                            //set value is for background
                            mReviewList.postValue(list);
                        } else {
                            reviewLoading.postValue(false);
                            mReviewList.postValue(null);

                        }
                    } else {
                        reviewLoading.postValue(false);
                        String error = response.errorBody().string();
                        Log.v("TAG", "Error " + error);
                        mReviewList.postValue(null);
                    }
                } catch (IOException e) {
                    reviewLoading.postValue(false);
                    e.printStackTrace();
                }
            }


        }

        //Review query
        private Call<ReviewResponse> getReviews(int movie_id, int pageNumber) {
            return Servicey.getMovieApi().getReviews(
                    movie_id,
                    Credentials.API_KEY,
                    pageNumber);
        }

        private void cancelRequest() {
            Log.v("TAG", "Cancelling the Request");
            cancelRequest = true;
        }

    }


    public void resetCast() {
        mCastList.postValue(null);
        mCrewList.postValue(null);
    }

    public void resetReview() {
        mReviewList.postValue(null);
    }


}
