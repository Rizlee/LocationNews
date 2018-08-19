package com.lnews.evgen.locationnews.features.description;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import javax.inject.Inject;

@InjectViewState
@PerActivity(DescriptionActivity.class)
public class DescriptionPresenter extends BasePresenter<DescriptionView> {
    private static final String DATE_DELIMITER = "T";

    @Inject
    DescriptionPresenter() {

    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearDescriptionComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    public void incomeDataEvent(String title, String date, String description, String imageUrl,
        String category) {
        getViewState().showNews(title, date.substring(0,date.indexOf(DATE_DELIMITER)), description, imageUrl, category);
    }

    public void onBackPressedEvent() {
        getViewState().finishActivity();
    }
}
