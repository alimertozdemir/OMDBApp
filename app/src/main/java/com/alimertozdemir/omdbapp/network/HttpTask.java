package com.alimertozdemir.omdbapp.network;

/**
 * Created by alimertozdemir on 14.10.2017.
 */

public class HttpTask<T> {

    private HttpGetAsyncTask httpAsyncTask;
    private DownloadImageTask downloadImageTask;

    public HttpTask(Job job, String searchKey, MovieQuery queryType) {
        httpAsyncTask = new HttpGetAsyncTask(job, searchKey, queryType);
    }

    public HttpTask(Job job, String imageUrl) {
        downloadImageTask = new DownloadImageTask(job, imageUrl);
    }

    public void start() {
        httpAsyncTask.execute();
    }

    public void startImageDownload() {
        downloadImageTask.execute();
    }

    public interface Job<T> {
        public String onBegin() throws Exception ;
        public void onComplete(T result, ContentType type);
        public void onError(Exception e);
    }

    public enum ContentType {
        JSON, IMAGE
    }

    public enum MovieQuery {
        SEARCH_KEY, TITLE
    }

}
