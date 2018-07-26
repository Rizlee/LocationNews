package com.lnews.evgen.locationnews.features.auth;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import javax.inject.Inject;

@InjectViewState
public class AuthPresenter extends BasePresenter<AuthView> {

    private final AuthorizationInteractor interactor;

    @Inject
    AuthPresenter(AuthorizationInteractor interactor){
        this.interactor = interactor;
    }

    @Override protected void clearComponent() {
    }

    @Override
    public void onDestroy() {
        interactor.dispose();
        super.onDestroy();
    }
}
