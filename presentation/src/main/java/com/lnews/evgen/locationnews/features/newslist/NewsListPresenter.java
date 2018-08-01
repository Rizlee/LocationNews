package com.lnews.evgen.locationnews.features.newslist;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import javax.inject.Inject;

@InjectViewState
@PerActivity(NewsListActivity.class)
public class NewsListPresenter extends BasePresenter<NewsListView> {

    @Inject
    NewsListPresenter(){

    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearNewsListComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }
}
