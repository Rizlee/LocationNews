package com.lnews.evgen.locationnews.features.auth;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import javax.inject.Inject;
import javax.inject.Provider;

public class AuthActivity extends BaseActivity implements AuthView {

    @InjectPresenter
    AuthPresenter authPresenter;
    @Inject
    Provider<AuthPresenter> presenterProvider;

    @ProvidePresenter
    AuthPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static Intent getActivityIntent(Context context){
        return new Intent(context, AuthActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusAuthComponent().inject(this);
    }
}
