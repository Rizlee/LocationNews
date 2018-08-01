package com.lnews.evgen.locationnews.features.tutorial;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.LaunchInteractor;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationActivity;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import javax.inject.Inject;

@InjectViewState
public class TutorialPresenter extends BasePresenter<TutorialView> {
    private final LaunchInteractor interactor;

    @Inject
    TutorialPresenter(LaunchInteractor interactor){
        this.interactor = interactor;
    }

    @Override protected void clearComponent() {
        Injector.getInstance().clearTutorialComponent();
    }

    @Override
    public void onDestroy() {
        interactor.dispose();
        clearComponent();
        super.onDestroy();
    }

    public void buttonSkipPressed(){
        interactor.disableTutorialNeed();
        getViewState().startNextActivity(AuthenticationActivity.getActivityIntent(context));
    }

    public void buttonNextPressed(int currentIdItem, int layoutsLength){
        if (currentIdItem < layoutsLength) {
            getViewState().showItem(currentIdItem);
        } else {
            buttonSkipPressed();
        }
    }
}
