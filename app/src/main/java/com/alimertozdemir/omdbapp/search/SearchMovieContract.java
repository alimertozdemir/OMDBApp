package com.alimertozdemir.omdbapp.search;

import android.graphics.Bitmap;

import com.alimertozdemir.omdbapp.search.model.Movie;

import java.util.List;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public interface SearchMovieContract {

    interface View {
        void setMovieResult(List<Movie> movies);
        void showErrorMessage(String errorMessage);
        void setThumbImage(Bitmap thumbImage);
        void goToMovieDetail(Movie movie);
    }

    interface Presenter {
        String getSearchResultFromApi(String searchKey);
        Bitmap getThumbImageFromURL(String url);
    }
}
