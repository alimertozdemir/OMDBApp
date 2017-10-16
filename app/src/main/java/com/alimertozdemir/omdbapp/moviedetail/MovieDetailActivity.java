package com.alimertozdemir.omdbapp.moviedetail;

import android.graphics.Bitmap;
import android.os.Bundle;
import android.support.annotation.StringRes;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Spannable;
import android.text.SpannableStringBuilder;
import android.view.MenuItem;
import android.widget.TextView;

import com.alimertozdemir.omdbapp.R;
import com.alimertozdemir.omdbapp.moviedetail.model.MovieDetail;
import com.alimertozdemir.omdbapp.search.model.Movie;
import com.alimertozdemir.omdbapp.view.SquareImageView;


public class MovieDetailActivity extends AppCompatActivity implements MovieDetailContract.View {

    private String TAG = MovieDetailActivity.class.getCanonicalName();

    private CollapsingToolbarLayout collapsingToolbarLayout;
    private Toolbar toolbar;
    private SquareImageView ivPoster;
    private TextView tvReleaseDate;
    private TextView tvRated;
    private TextView tvRuntime;
    private TextView tvGenre;
    private TextView tvDirector;
    private TextView tvWriter;
    private TextView tvActors;
    private TextView tvPlot;
    private TextView tvLanguage;
    private TextView tvCountry;
    private TextView tvAwards;
    private MovieDetailPresenter mMovieDetailPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        mMovieDetailPresenter = new MovieDetailPresenter(this);

        collapsingToolbarLayout = (CollapsingToolbarLayout) findViewById(R.id.collapsing_toolbar);
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowTitleEnabled(true);

        ivPoster = (SquareImageView) findViewById(R.id.iv_poster);
        tvReleaseDate = (TextView) findViewById(R.id.tv_release_date);
        tvRated = (TextView) findViewById(R.id.tv_rated);
        tvRuntime = (TextView) findViewById(R.id.tv_runtime);
        tvGenre = (TextView) findViewById(R.id.tv_genre);
        tvDirector = (TextView) findViewById(R.id.tv_director);
        tvWriter = (TextView) findViewById(R.id.tv_writer);
        tvActors = (TextView) findViewById(R.id.tv_actors);
        tvPlot = (TextView) findViewById(R.id.tv_plot);
        tvLanguage = (TextView) findViewById(R.id.tv_language);
        tvCountry = (TextView) findViewById(R.id.tv_country);
        tvAwards = (TextView) findViewById(R.id.tv_awards);

        getMovieDetailFromTitle();
    }

    @Override
    public void setMovieDetail(MovieDetail movieDetail) {
        tvReleaseDate.setText(getFormattedString(R.string.release_date, movieDetail.getReleased()));
        tvRated.setText(getFormattedString(R.string.rated, movieDetail.getRated()));
        tvRuntime.setText(getFormattedString(R.string.runtime, movieDetail.getRuntime()));
        tvGenre.setText(getFormattedString(R.string.genre, movieDetail.getGenre()));
        tvDirector.setText(getFormattedString(R.string.director, movieDetail.getDirector()));
        tvWriter.setText(getFormattedString(R.string.writer, movieDetail.getWriter()));
        tvActors.setText(getFormattedString(R.string.actors, movieDetail.getActors()));
        tvPlot.setText(getFormattedString(R.string.plot, movieDetail.getPlot()));
        tvLanguage.setText(getFormattedString(R.string.language, movieDetail.getLanguage()));
        tvCountry.setText(getFormattedString(R.string.country, movieDetail.getCountry()));
        tvAwards.setText(getFormattedString(R.string.awards, movieDetail.getAwards()));
    }

    @Override
    public void setBigThumbImage(Bitmap bigThumbImage) {
        ivPoster.setImageBitmap(bigThumbImage);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        switch (id) {
            case android.R.id.home:
                onBackPressed();
                return true;
            default:
                break;
        }

        return super.onOptionsItemSelected(item);
    }

    public void getMovieDetailFromTitle() {
        Movie movie = (Movie) getIntent().getSerializableExtra("movie");

        if (movie != null) {
            String title = movie.getTitle();
            String id = movie.getImdbID();
            String poster = movie.getPoster();

            if (title != null) {
                collapsingToolbarLayout.setTitle(title);
            }

            if (id != null) {
                mMovieDetailPresenter.getMovieDetailFromApi(id);
            }

            if (poster != null) {
                mMovieDetailPresenter.getBigThumbImageFromURL(poster);
            }
        }
    }

    private SpannableStringBuilder getFormattedString(@StringRes int sResource, String content) {
        try {
            String formattedString = String.format(getString(sResource), content);
            int boldLength = formattedString.length() - content.length();
            SpannableStringBuilder result = new SpannableStringBuilder(formattedString);
            result.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,
                    boldLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return result;
        } catch (Exception e) {
            content = "N/A";
            String formattedString = String.format(getString(sResource), content);
            int boldLength = formattedString.length() - content.length();
            SpannableStringBuilder result = new SpannableStringBuilder(formattedString);
            result.setSpan(new android.text.style.StyleSpan(android.graphics.Typeface.BOLD), 0,
                    boldLength, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
            return result;
        }
    }
}
