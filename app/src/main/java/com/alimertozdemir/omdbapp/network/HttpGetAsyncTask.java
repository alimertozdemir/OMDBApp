package com.alimertozdemir.omdbapp.network;

import android.net.Uri;
import android.os.AsyncTask;

import com.alimertozdemir.omdbapp.OMDBApplication;
import com.alimertozdemir.omdbapp.R;
import com.alimertozdemir.omdbapp.network.exception.BusinessException;
import com.alimertozdemir.omdbapp.network.exception.NetworkException;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class HttpGetAsyncTask extends AsyncTask<String, Void, String> {

    private final String API_KEY = "b29eb00d";

    private static final String TAG = HttpGetAsyncTask.class.getName();
    private final String REQUEST_TYPE = "GET";
    private HttpTask.Job job;
    private String searchKey;
    private HttpTask.MovieQuery queryType;
    private Exception exception;

    private static HttpURLConnection urlConnection;

    public HttpGetAsyncTask(HttpTask.Job job, String searchKey, HttpTask.MovieQuery queryType) {
        this.job = job;
        this.searchKey = searchKey;
        this.queryType = queryType;
    }

    protected String doInBackground(String... args) {

        try {
            Uri.Builder builder = new Uri.Builder();
            builder.scheme("http")
                    .authority("www.omdbapi.com")
                    .appendQueryParameter("apikey", API_KEY)
                    .appendQueryParameter("plot", "short")
                    .appendQueryParameter("type", "movie")
                    .appendQueryParameter("r", "json");
            if (queryType == HttpTask.MovieQuery.SEARCH_KEY) {
                builder.appendQueryParameter("s", searchKey);
            } else if (queryType == HttpTask.MovieQuery.TITLE) {
                builder.appendQueryParameter("i", searchKey);
            }

            URL url = new URL(builder.build().toString());
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(15000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod(REQUEST_TYPE);
            urlConnection.setDoInput(true);
            urlConnection.setDoOutput(true);

            int responseCode = urlConnection.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {

                String result = readStream(urlConnection.getInputStream());
                return result;

            } else {
                String error = OMDBApplication.getContext().getResources().getString(R.string.business_exception);
                exception = new BusinessException(error);
                return null;
            }
        } catch (ProtocolException e) {
            e.printStackTrace();
            exception = new NetworkException(e.getLocalizedMessage());
        } catch (MalformedURLException e) {
            e.printStackTrace();
            exception = new NetworkException(e.getLocalizedMessage());
        } catch (IOException e) {
            e.printStackTrace();
            exception = new NetworkException(e.getLocalizedMessage());
        } finally {
            urlConnection.disconnect();
        }

        return null;
    }

    @Override
    protected void onPostExecute(String result) {

        if (exception == null) {
            job.onComplete(result, HttpTask.ContentType.JSON);
        } else {
            job.onError(exception);
        }

    }

    private String readStream(InputStream in) {
        BufferedReader reader = null;
        StringBuffer response = new StringBuffer();
        try {
            reader = new BufferedReader(new InputStreamReader(in));
            String line = "";
            while ((line = reader.readLine()) != null) {
                response.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
            exception = new NetworkException(e.getLocalizedMessage());
        } finally {
            if (reader != null) {
                try {
                    reader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                    exception = new NetworkException(e.getLocalizedMessage());
                }
            }
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
                exception = new NetworkException(e.getLocalizedMessage());
            }
        }
        return response.toString();
    }
}
