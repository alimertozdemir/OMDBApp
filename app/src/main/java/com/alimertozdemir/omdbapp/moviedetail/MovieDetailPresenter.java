package com.alimertozdemir.omdbapp.moviedetail;

import android.graphics.Bitmap;
import android.util.Log;

import com.alimertozdemir.omdbapp.base.BasePresenter;
import com.alimertozdemir.omdbapp.moviedetail.model.MovieDetail;
import com.alimertozdemir.omdbapp.network.HttpTask;
import com.google.gson.Gson;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class MovieDetailPresenter extends BasePresenter implements MovieDetailContract.Presenter, HttpTask.Job<Object> {

    private MovieDetailContract.View view;

    public MovieDetailPresenter(MovieDetailContract.View view) {
        this.view = view;
    }

    @Override
    public String getMovieDetailFromApi(String id) {

        HttpTask task = new HttpTask(this, id, HttpTask.MovieQuery.TITLE);
        task.start();

        return null;
    }

    @Override
    public Bitmap getBigThumbImageFromURL(String url) {

        HttpTask task = new HttpTask(this, url);
        task.startImageDownload();

        return null;
    }


    @Override
    public String onBegin() throws Exception {
        return null;
    }

    @Override
    public void onComplete(Object result, HttpTask.ContentType type) {

        if(type == HttpTask.ContentType.JSON) {
            view.setMovieDetail(mapApiResultToMovieDetail(result.toString()));
        } else {
            Bitmap image = (Bitmap) result;
            view.setBigThumbImage(image);
        }
    }

    @Override
    public void onError(Exception e) {
        Log.d("MovieDetailPresenter", "ERROR: " + e.getLocalizedMessage());
    }

    private MovieDetail mapApiResultToMovieDetail(String result) {
        Gson gson = new Gson();
        MovieDetail movieDetail = gson.fromJson(result, MovieDetail.class);
        return movieDetail;
    }
}
