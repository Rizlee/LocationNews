package com.lnews.evgen.locationnews.utils;

import android.content.Context;

public class ErrorHandler {
    private final Context context;
    private final ErrorHandlerCallback errorHandlerCallback;

    public interface ErrorHandlerCallback {
        void showMessage(String message);
    }

    public ErrorHandler(Context context, ErrorHandlerCallback errorHandlerCallback) {
        this.context = context;
        this.errorHandlerCallback = errorHandlerCallback;
    }

    public void handleError(Throwable throwable) {

    }
}
