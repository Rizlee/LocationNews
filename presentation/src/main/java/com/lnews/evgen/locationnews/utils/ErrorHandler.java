package com.lnews.evgen.locationnews.utils;

import android.content.Context;
import com.google.firebase.FirebaseNetworkException;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.lnews.evgen.locationnews.R;

public class ErrorHandler {
    private static final String ERROR_USER_NOT_FOUND = "ERROR_USER_NOT_FOUND";
    private static final String ERROR_INVALID_EMAIL = "ERROR_INVALID_EMAIL";
    private static final String ERROR_WRONG_PASSWORD = "ERROR_WRONG_PASSWORD";

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
        if (throwable instanceof FirebaseAuthInvalidCredentialsException) {
            onFireBaseAuthInvalidCredentials((FirebaseAuthInvalidCredentialsException) throwable);
        } else if (throwable instanceof FirebaseAuthInvalidUserException) {
            onFirebaseAuthInvalidUserException((FirebaseAuthInvalidUserException) throwable);
        } else if (throwable instanceof FirebaseNetworkException){
            onFirebaseNetworkException();
        }else {
            onException();
        }
    }

    private void onFireBaseAuthInvalidCredentials(
        FirebaseAuthInvalidCredentialsException throwable) {
        switch (throwable.getErrorCode()){
            case ERROR_WRONG_PASSWORD:{
                errorHandlerCallback.showMessage(context.getString(R.string.error_wrong_password));
                break;
            }

            case ERROR_INVALID_EMAIL: {
                errorHandlerCallback.showMessage(context.getString(R.string.error_invalid_email));
                break;
            }
        }
    }

    private void onFirebaseAuthInvalidUserException(FirebaseAuthInvalidUserException throwable) {
        switch (throwable.getErrorCode()) {
            case ERROR_USER_NOT_FOUND: {
                errorHandlerCallback.showMessage(context.getString(R.string.error_user_not_found));
                break;
            }

            case ERROR_INVALID_EMAIL: {
                errorHandlerCallback.showMessage(context.getString(R.string.error_invalid_email));
                break;
            }
        }
    }

    private void onFirebaseNetworkException(){
        errorHandlerCallback.showMessage(context.getString(R.string.error_connection_lost));
    }

    private void onException(){
        errorHandlerCallback.showMessage(context.getString(R.string.error_occurred));
    }
}