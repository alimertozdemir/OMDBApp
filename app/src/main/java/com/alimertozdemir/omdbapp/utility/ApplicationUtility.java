package com.alimertozdemir.omdbapp.utility;

import android.app.Activity;
import android.view.View;
import android.view.inputmethod.InputMethodManager;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class ApplicationUtility {

    private static ApplicationUtility instance;

    private ApplicationUtility() {}

    public static ApplicationUtility getInstance() {
        if (instance == null) {
            instance = new ApplicationUtility();
        }
        return instance;
    }

    public void hideSoftKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        View view = activity.getCurrentFocus();

        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

}
