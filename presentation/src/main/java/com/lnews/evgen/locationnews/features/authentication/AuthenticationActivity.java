package com.lnews.evgen.locationnews.features.authentication;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import javax.inject.Inject;
import javax.inject.Provider;

public class AuthenticationActivity extends BaseActivity implements AuthenticationView,
    AuthenticationEventListener {

    @InjectPresenter
    AuthenticationPresenter presenter;
    @Inject
    Provider<AuthenticationPresenter> presenterProvider;

    @ProvidePresenter
    AuthenticationPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static Intent getActivityIntent(Context context){
        return new Intent(context, AuthenticationActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusAuthenticationComponent().inject(this);
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1){
            finish();
        }
        else {
            super.onBackPressed();
        }
    }

    @Override
    public void showFragment(Fragment fragment) {
        String backStateName =  fragment.getClass().getName();

        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate (backStateName, 0);

        if (!fragmentPopped && manager.findFragmentByTag(backStateName) == null){
            FragmentTransaction ft = manager.beginTransaction();
            ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN);
            ft.setCustomAnimations(R.anim.slide_left, R.anim.slide_right);
            ft.replace(R.id.framelayout_auth, fragment, backStateName);
            ft.addToBackStack(backStateName);
            ft.commit();
        }
    }

    @Override
    public void showForgotPassEvent() {
        presenter.showForgotPassEvent();
    }

    @Override
    public void showRegistrationEvent() {
        presenter.showRegistrationEvent();
    }

    @Override public void authSuccessEvent() {
        presenter.authSuccessEvent();
    }
}
