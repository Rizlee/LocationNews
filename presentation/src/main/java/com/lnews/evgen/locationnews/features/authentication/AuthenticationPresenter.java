package com.lnews.evgen.locationnews.features.authentication;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.R;
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

    @Inject
    AuthenticationPresenter(){

    }

    @Override
    public void attachView(AuthenticationView view) {
        super.attachView(view);
        getViewState().showFragment(AuthFragment.getInstance());
    }

    @Override protected void clearComponent() {
        Injector.getInstance().clearAuthenticationComponent();
    }

    public void btnPressedListener(int id){
        switch (id){
            case R.id.button_forgot_pass:{
                getViewState().showFragment(PassRecoveryFragment.getInstance());
                break;
            }
            case R.id.button_new_member:{
                getViewState().showFragment(RegistrationFragment.getInstance());
                break;
            }
        }
    }

    public void authSuccessListener(){
        getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
    }
}
