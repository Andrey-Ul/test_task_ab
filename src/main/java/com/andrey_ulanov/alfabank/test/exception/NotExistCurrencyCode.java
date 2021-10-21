package com.andrey_ulanov.alfabank.test.exception;

public class NotExistCurrencyCode extends RuntimeException {

    public NotExistCurrencyCode(String message) {
        super(message);
    }

    public NotExistCurrencyCode(String message, Throwable cause) {
        super(message, cause);
    }

    public NotExistCurrencyCode(Throwable cause) {
        super(cause);
    }

    public NotExistCurrencyCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
