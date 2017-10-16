package com.alimertozdemir.omdbapp;

import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.alimertozdemir.omdbapp.search.SearchMovieActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

@RunWith(AndroidJUnit4.class)
public class MovieDetailScreenTest {

    @Rule
    public ActivityTestRule<SearchMovieActivity> mActivityTestRule = new ActivityTestRule<SearchMovieActivity>(SearchMovieActivity.class);

    @Test
    public void scrollDownOnView() {

        SearchMovieScreenTest searchMovieScreenTest = new SearchMovieScreenTest();
        searchMovieScreenTest.searchItemFromSearchView();
        searchMovieScreenTest.clickFirstItemOnSearchResults();

        // Wait 3 seconds until MovieDetailActicity opens
        TestUtility.wait(3);

        // Press back button
        onView(withId(R.id.toolbar)).perform(click());

        // Wait 2 seconds before test finish
        TestUtility.wait(2);

    }

}