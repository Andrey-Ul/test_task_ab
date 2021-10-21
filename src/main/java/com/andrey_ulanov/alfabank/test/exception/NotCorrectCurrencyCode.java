package com.andrey_ulanov.alfabank.test.exception;

public class NotCorrectCurrencyCode extends RuntimeException {

    public NotCorrectCurrencyCode(String message) {
        super(message);
    }

    public NotCorrectCurrencyCode(String message, Throwable cause) {
        super(message, cause);
    }

    public NotCorrectCurrencyCode(Throwable cause) {
        super(cause);
    }

    public NotCorrectCurrencyCode(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}
