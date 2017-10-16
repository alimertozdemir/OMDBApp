package com.alimertozdemir.omdbapp.search;

import android.graphics.Bitmap;
import android.util.Log;

import com.alimertozdemir.omdbapp.base.BasePresenter;
import com.alimertozdemir.omdbapp.network.HttpTask;
import com.alimertozdemir.omdbapp.search.model.SearchMovieResult;
import com.google.gson.Gson;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class SearchMoviePresenter extends BasePresenter implements SearchMovieContract.Presenter, HttpTask.Job<Object> {

    private SearchMovieContract.View view;

    public SearchMoviePresenter(SearchMovieContract.View view) {
        this.view = view;
    }

    @Override
    public String getSearchResultFromApi(String searchKey) {

        HttpTask task = new HttpTask(this, searchKey, HttpTask.MovieQuery.SEARCH_KEY);
        task.start();

        return null;
    }

    @Override
    public Bitmap getThumbImageFromURL(String url) {

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
            mapApiResultToMovies(result.toString());
        } else {
            Bitmap image = (Bitmap) result;
            view.setThumbImage(image);
        }
    }

    @Override
    public void onError(Exception e) {
        Log.d("SearchMoviePresenter", "ERROR: " + e.getLocalizedMessage());
    }

    private void mapApiResultToMovies(String result) {
        Gson gson = new Gson();
        SearchMovieResult movies = gson.fromJson(result, SearchMovieResult.class);
        if("True".equalsIgnoreCase(movies.getResponse())) {
            view.setMovieResult(movies.getSearch());
        } else {
            view.showErrorMessage(movies.getError());
        }
    }
}
