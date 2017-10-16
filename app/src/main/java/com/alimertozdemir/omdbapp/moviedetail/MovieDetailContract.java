package com.alimertozdemir.omdbapp.moviedetail;

import android.graphics.Bitmap;

import com.alimertozdemir.omdbapp.moviedetail.model.MovieDetail;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public interface MovieDetailContract {

    interface View {
        void setMovieDetail(MovieDetail movieDetail);
        void setBigThumbImage(Bitmap bigThumbImage);
    }

    interface Presenter {
        String getMovieDetailFromApi(String title);
        Bitmap getBigThumbImageFromURL(String url);
    }
}
