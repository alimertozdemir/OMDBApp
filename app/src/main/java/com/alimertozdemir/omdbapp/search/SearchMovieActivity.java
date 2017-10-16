package com.alimertozdemir.omdbapp.search;

import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.alimertozdemir.omdbapp.R;
import com.alimertozdemir.omdbapp.moviedetail.MovieDetailActivity;
import com.alimertozdemir.omdbapp.search.model.Movie;
import com.alimertozdemir.omdbapp.view.RecyclerItemDecoration;

import java.util.ArrayList;
import java.util.List;

public class SearchMovieActivity extends AppCompatActivity implements SearchMovieContract.View {

    private String TAG = SearchMovieActivity.class.getCanonicalName();
    private RecyclerView rvSearchResult;
    private SearchView searchView;
    private SearchMovieResultAdapter searchMovieResultAdapter;
    private List<Movie> movies = new ArrayList<>();
    private SearchMoviePresenter mSearchMoviePresenter;
    private final int searchKeyMinimumLength = 2;

    private RelativeLayout rlPlaceholder;
    private ImageView ivPlaceholder;
    private TextView tvPlaceholder;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        rlPlaceholder = (RelativeLayout) findViewById(R.id.rl_placeholder_container);
        ivPlaceholder = rlPlaceholder.findViewById(R.id.iv_placeholder);
        tvPlaceholder = rlPlaceholder.findViewById(R.id.tv_placeholder);

        rvSearchResult = (RecyclerView) findViewById(R.id.rv_search_result);
        rvSearchResult.addItemDecoration(new RecyclerItemDecoration(this));
        searchMovieResultAdapter = new SearchMovieResultAdapter(movies);
        rvSearchResult.setAdapter(searchMovieResultAdapter);

        searchMovieResultAdapter.setOnItemClickListener(new SearchMovieResultAdapter.OnItemClickListener() {
            @Override
            public void onClick(Movie item) {
                goToMovieDetail(item);
            }
        });

        mSearchMoviePresenter = new SearchMoviePresenter(this);
    }

    @Override
    public void setMovieResult(List<Movie> movies) {
        this.movies = movies;
        searchMovieResultAdapter.updateMovies(movies);

        if (this.movies == null) {
            // No result found. Set no result image and text here
            ivPlaceholder.setImageResource(R.drawable.img_no_result_placeholder);
            tvPlaceholder.setText(getString(R.string.no_result));

            rlPlaceholder.setVisibility(View.VISIBLE);
            rvSearchResult.setVisibility(View.GONE);
            return;
        }

        if (this.movies.size() == 0) {
            ivPlaceholder.setImageResource(R.drawable.img_search_placeholder);
            tvPlaceholder.setText(getString(R.string.search_placeholder));
            rlPlaceholder.setVisibility(View.VISIBLE);
            rvSearchResult.setVisibility(View.GONE);
        } else {
            rlPlaceholder.setVisibility(View.GONE);
            rvSearchResult.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void showErrorMessage(String errorMessage) {
        // Error occured. Set no result image and text here
        ivPlaceholder.setImageResource(R.drawable.img_empty_movie);
        tvPlaceholder.setText(errorMessage);
        rvSearchResult.setVisibility(View.GONE);
        rlPlaceholder.setVisibility(View.VISIBLE);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_search, menu);

        final MenuItem searchMenuItem = menu.findItem(R.id.action_search);
        searchView = (SearchView) searchMenuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                if (s.equals("") || s.length() < searchKeyMinimumLength) {
                    ivPlaceholder.setImageResource(R.drawable.img_search_placeholder);
                    tvPlaceholder.setText(getString(R.string.search_placeholder));
                    rlPlaceholder.setVisibility(View.VISIBLE);
                    rvSearchResult.setVisibility(View.GONE);
                    return false;
                }

                mSearchMoviePresenter.getSearchResultFromApi(s);
                return false;
            }
        });
        return true;
    }

    @Override
    public void setThumbImage(Bitmap thumbImage) {
    }

    @Override
    public void goToMovieDetail(Movie movie) {
        Intent intent = new Intent(SearchMovieActivity.this, MovieDetailActivity.class);
        intent.putExtra("movie", movie);
        startActivity(intent);
    }
}