package com.lnews.evgen.locationnews.features.description;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.base.BaseActivity;

public class DescriptionActivity extends BaseActivity implements DescriptionView{

    @InjectPresenter
    DescriptionPresenter descriptionPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);
    }

    @Override protected void injectComponent() {

    }

    @Override public void showNews() {

    }
}
