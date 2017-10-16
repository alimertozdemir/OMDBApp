package com.alimertozdemir.omdbapp;

import android.content.Intent;
import android.support.test.rule.ActivityTestRule;

import java.io.Serializable;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class TestUtility {

    public static void wait(int seconds) {
        try {
            long milliseconds = seconds * 1000;
            Thread.sleep(milliseconds);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void startActivity(ActivityTestRule rule, String name, Serializable object) {
        Intent intent = new Intent();
        intent.putExtra(name, object);
        rule.launchActivity(intent);
    }
}
