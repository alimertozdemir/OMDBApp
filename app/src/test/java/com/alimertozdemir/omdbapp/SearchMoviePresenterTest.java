package com.alimertozdemir.omdbapp;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.alimertozdemir.omdbapp.search.SearchMovieContract;
import com.alimertozdemir.omdbapp.search.SearchMoviePresenter;
import com.alimertozdemir.omdbapp.search.model.Movie;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.verify;

/**
 * Created by alimertozdemir on 16.10.2017.
 */

public class SearchMoviePresenterTest {

    private static List<Movie> movies;

    private SearchMoviePresenter mSearchMoviePresenter;

    @Mock
    private SearchMovieContract.View mSearchMovieView;

    @Before
    public void setSearchMoviePresenter() {

        MockitoAnnotations.initMocks(this);
        mSearchMoviePresenter = new SearchMoviePresenter(mSearchMovieView);

        movies = new ArrayList<Movie>();
        Bitmap bitmap = BitmapFactory.decodeResource(OMDBApplication.getContext().getResources(), R.mipmap.ic_launcher);
        movies.add(new Movie(bitmap, "Star Wars: Episode IV - A New Hope", "1977", "tt0076759", "movie", "https://images-na.ssl-images-amazon.com/images/M/MV5BYTUwNTdiMzMtNThmNS00ODUzLThlMDMtMTM5Y2JkNWJjOGQ2XkEyXkFqcGdeQXVyNzQ1ODk3MTQ@._V1_SX300.jpg"));
        movies.add(new Movie(bitmap, "Star Wars: Episode V - The Empire Strikes Back", "1980", "tt0080684", "movie", "https://images-na.ssl-images-amazon.com/images/M/MV5BYmViY2M2MTYtY2MzOS00YjQ1LWIzYmEtOTBiNjhlMGM0NjZjXkEyXkFqcGdeQXVyNDYyMDk5MTU@._V1_SX300.jpgs"));
        movies.add(new Movie(bitmap, "Star Wars: Episode VI - Return of the Jedi", "1983", "tt0086190", "movie", "https://images-na.ssl-images-amazon.com/images/M/MV5BODllZjg2YjUtNWEzNy00ZGY2LTgyZmQtYTkxNDYyOWM3OTUyXkEyXkFqcGdeQXVyMTQxNzMzNDI@._V1_SX300.jpg"));
    }

    @Test
    public void getMoviesBySearchKey() {
        String searchKey = "Star Wars";
        mSearchMoviePresenter.getSearchResultFromApi(searchKey);
        verify(mSearchMovieView).setMovieResult(movies);
    }
}
