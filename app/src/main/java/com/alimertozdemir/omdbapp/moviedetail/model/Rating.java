package com.alimertozdemir.omdbapp.moviedetail.model;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class Rating {

    public String source;
    public String value;

    public String getSource() {
        return source;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return "Rating{" +
                "source='" + source + '\'' +
                ", value='" + value + '\'' +
                '}';
    }
}
