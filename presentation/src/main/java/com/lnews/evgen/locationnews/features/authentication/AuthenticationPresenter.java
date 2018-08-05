package com.lnews.evgen.locationnews.features.authentication;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.authorization.AuthFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.newslist.NewsListActivity;
import com.lnews.evgen.locationnews.features.passrecovery.PassRecoveryFragment;
import com.lnews.evgen.locationnews.features.registration.RegistrationFragment;
import javax.inject.Inject;

@InjectViewState
@PerActivity(AuthenticationActivity.class)
public class AuthenticationPresenter extends BasePresenter<AuthenticationView> {

    private final AuthorizationInteractor authorizationInteractor;

    @Inject
    AuthenticationPresenter(AuthorizationInteractor authorizationInteractor) {
        this.authorizationInteractor = authorizationInteractor;
    }

    @Override
    public void attachView(AuthenticationView view) {
        super.attachView(view);
        getViewState().showFragment(AuthFragment.newInstance());
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearAuthenticationComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    public void showForgotPassEvent() {
        getViewState().showFragment(PassRecoveryFragment.newInstance());
    }

    public void showRegistrationEvent() {
        getViewState().showFragment(RegistrationFragment.newInstance());
    }

    public void authSuccessEvent() {
        authorizationInteractor.saveToken();
        getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
    }
}
