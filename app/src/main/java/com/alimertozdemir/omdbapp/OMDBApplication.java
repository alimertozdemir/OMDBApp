package com.alimertozdemir.omdbapp;

import android.app.Application;
import android.content.Context;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class OMDBApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = this.getApplicationContext();
    }

    public static Context getContext() {
        return context;
    }
}
