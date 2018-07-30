package com.lnews.evgen.locationnews.features.newslist;

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

public class NewsListActivity extends BaseActivity implements NewsListView {

    @InjectPresenter
    NewsListPresenter presenter;
    @Inject Provider<NewsListPresenter> presenterProvider;

    @ProvidePresenter
    NewsListPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static Intent getActivityIntent(Context context){
        return new Intent(context, NewsListActivity.class);
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusNewsListComponent().inject(this);
    }

    @Override
    public void showList() {

    }

    @Override
    public void rebuildTabView() {

    }

    @Override
    public void showAddCategoryDialog() {

    }

    @Override
    public void showLocationDialog() {

    }

    @Override
    public void showSearchResult() {

    }

    @Override
    public void changeTheme() {

    }

    @Override
    public void rebuildNavigationDrawer() {

    }

    @Override
    public void showHelpScreen() {

    }

    @Override
    public void changeLocation() {

    }

    @Override
    public void changeCategoryList() {

    }
}
