package com.lnews.evgen.locationnews.features.launch;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import javax.inject.Inject;
import javax.inject.Provider;

public class LaunchActivity extends BaseActivity implements LaunchView{

    @InjectPresenter
    LaunchPresenter presenter;
    @Inject
    Provider<LaunchPresenter> presenterProvider;

    @ProvidePresenter
    LaunchPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().getAppComponent().inject(this);
    }
}
