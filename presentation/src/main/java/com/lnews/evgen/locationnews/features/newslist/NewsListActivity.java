package com.lnews.evgen.locationnews.features.newslist;

import android.os.Bundle;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.base.BaseActivity;

public class NewsListActivity extends BaseActivity implements NewsListView {

    @InjectPresenter NewsListPresenter newsListPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);
    }

    @Override
    protected void injectComponent() {

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
