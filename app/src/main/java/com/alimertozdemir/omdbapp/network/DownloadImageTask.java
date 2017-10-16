package com.alimertozdemir.omdbapp.network;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

import com.alimertozdemir.omdbapp.OMDBApplication;
import com.alimertozdemir.omdbapp.R;
import com.alimertozdemir.omdbapp.network.exception.NetworkException;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by alimertozdemir on 14.10.2017.
 */


public class DownloadImageTask extends AsyncTask<ImageView, Void, Bitmap> {

    private static final String TAG = HttpGetAsyncTask.class.getName();

    private String imageUrl;
    private HttpTask.Job job;
    private Exception exception;

    public DownloadImageTask(HttpTask.Job job, String imageUrl) {
        this.imageUrl = imageUrl;
        this.job = job;
    }

    @Override
    protected Bitmap doInBackground(ImageView... imageViews) {
        return downloadImage(imageUrl);
    }

    @Override
    protected void onPostExecute(Bitmap result) {

        if (exception == null) {
            job.onComplete(result, HttpTask.ContentType.IMAGE);
        } else {
            job.onError(exception);
        }
    }

    private Bitmap downloadImage(String url) {

        Bitmap bitmap = null;
        try{
            URL imageUrl = new URL(url);
            HttpURLConnection connection = (HttpURLConnection) imageUrl.openConnection();
            InputStream inputStream = connection.getInputStream();
            bitmap = BitmapFactory.decodeStream(inputStream);
            if (bitmap != null) {
                return bitmap;
            } else {
                String error = OMDBApplication.getContext().getResources().getString(R.string.bitmap_exception);
                exception = new NetworkException(error);
                throw exception;
            }
        } catch(Exception e){
            bitmap = BitmapFactory.decodeResource(OMDBApplication.getContext().getResources(), R.mipmap.ic_launcher);
        }
        return bitmap;
    }
}
