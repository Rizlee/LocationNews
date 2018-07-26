package com.lnews.evgen.locationnews.features.launch;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lnews.evgen.domain.interactors.LaunchInteractor;
import com.lnews.evgen.locationnews.features.auth.AuthActivity;
import javax.inject.Inject;

@InjectViewState
public class LaunchPresenter extends MvpPresenter<LaunchView> {

    private final LaunchInteractor interactor;
    private final Context context;

    @Inject
    LaunchPresenter(LaunchInteractor interactor, Context context) {
        this.interactor = interactor;
        this.context = context;
    }

    @Override
    public void attachView(LaunchView view) {
        super.attachView(view);
        showNextActivity();
    }

    @Override
    public void onDestroy() {
        interactor.dispose();
        super.onDestroy();
    }


    private void showNextActivity() {
        getViewState().startNextActivity(AuthActivity.getActivityIntent(context));
    }
}
