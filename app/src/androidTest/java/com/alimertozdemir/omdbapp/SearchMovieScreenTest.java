package com.alimertozdemir.omdbapp;

import android.support.test.espresso.contrib.RecyclerViewActions;
import android.support.test.rule.ActivityTestRule;
import android.support.test.runner.AndroidJUnit4;

import com.alimertozdemir.omdbapp.search.SearchMovieActivity;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.matcher.ViewMatchers.withId;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

@RunWith(AndroidJUnit4.class)
public class SearchMovieScreenTest {

    @Rule
    public ActivityTestRule<SearchMovieActivity> mActivityTestRule = new ActivityTestRule<>(SearchMovieActivity.class);

    @Test
    public void searchItemFromSearchView() {

        // Wait 2 seconds until activity onStart
        TestUtility.wait(2);

        // Click toolbar search image button
        onView(withId(R.id.action_search)).perform(click());

        // Type "Star Wars" and close keyboard
        String searchKey = "Star Wars";
        onView(withId(android.support.design.R.id.search_src_text)).perform(typeText(searchKey), closeSoftKeyboard());

    }

    @Test
    public void clickFirstItemOnSearchResults() {

        // Wait 3 seconds until RecyclerView lists results
        TestUtility.wait(3);

        // Select First Item of results
        onView(withId(R.id.rv_search_result)).perform(RecyclerViewActions.actionOnItemAtPosition(0, click()));
    }

}
