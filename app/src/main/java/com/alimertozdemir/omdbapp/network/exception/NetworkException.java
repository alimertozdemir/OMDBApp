package com.alimertozdemir.omdbapp.network.exception;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class NetworkException extends Exception {

    public NetworkException(String message) {
        super(message);
    }

    public NetworkException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
