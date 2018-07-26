package com.lnews.evgen.locationnews.features.base;

import android.content.Context;
import com.arellomobile.mvp.MvpPresenter;
import com.lnews.evgen.locationnews.utils.ErrorHandler;
import javax.inject.Inject;

public abstract class BasePresenter<V extends BaseView> extends MvpPresenter<V> implements ErrorHandler.ErrorHandlerCallback {

    @Inject
    protected Context context;

    private ErrorHandler errorHandler;

    protected abstract void clearComponent();

    @Override
    protected void onFirstViewAttach() {
        super.onFirstViewAttach();
        errorHandler = new ErrorHandler(context, this);
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    @Override
    public void showMessage(String message) {
        getViewState().showToast(message);
    }

    protected void handleError(Throwable e) {
        errorHandler.handleError(e);
    }

}
