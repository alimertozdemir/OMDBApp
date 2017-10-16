package com.alimertozdemir.omdbapp.network.exception;

/**
 * Created by alimertozdemir on 15.10.2017.
 */

public class BusinessException extends Exception {

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable throwable) {
        super(message, throwable);
    }
}
