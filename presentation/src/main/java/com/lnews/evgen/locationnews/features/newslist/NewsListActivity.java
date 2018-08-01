package com.lnews.evgen.locationnews.features.newslist;

import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import java.util.List;
import java.util.Locale;
import javax.inject.Inject;
import javax.inject.Provider;

public class NewsListActivity extends BaseActivity implements NewsListView {

    @BindView(R.id.toolbar_newslist) Toolbar toolbar;
    @BindView(R.id.drawerlayout_newslist) DrawerLayout drawerLayout;
    @BindView(R.id.navigationview_newslist_menu) NavigationView navigationView;

    @InjectPresenter NewsListPresenter presenter;
    @Inject Provider<NewsListPresenter> presenterProvider;

    @ProvidePresenter
    NewsListPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, NewsListActivity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_newslist);

        setupToolbar();
        setupMenu();
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusNewsListComponent().inject(this);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_newslist_toolbar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                drawerLayout.openDrawer(GravityCompat.START);
                return true;
        }
        return super.onOptionsItemSelected(item);
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

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
        }
    }

    private void setupMenu() {
        //TODO
    }
}
