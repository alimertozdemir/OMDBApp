package com.alimertozdemir.omdbapp.search.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class SearchMovieResult {

    @SerializedName("Search")
    public List<Movie> search = null;
    @SerializedName("TotalResults")
    public String totalResults;
    @SerializedName("Response")
    public String response;
    @SerializedName("Error")
    public String error;

    public List<Movie> getSearch() {
        return search;
    }

    public String getTotalResults() {
        return totalResults;
    }

    public String getResponse() {
        return response;
    }

    public String getError() {
        return error;
    }

    @Override
    public String toString() {
        return "SearchMovieResult{" +
                "search=" + search +
                ", totalResults='" + totalResults + '\'' +
                ", response='" + response + '\'' +
                ", error='" + error + '\'' +
                '}';
    }
}
