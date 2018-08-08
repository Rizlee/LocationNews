package com.lnews.evgen.locationnews.features.newslist;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
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
import com.lnews.evgen.locationnews.features.newslist.adapter.NewsPagerAdapter;
import com.lnews.evgen.locationnews.features.newslist.dialog.CategoryDialog;
import com.lnews.evgen.locationnews.features.newslist.dialog.LocationDialog;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Provider;

public class NewsListActivity extends BaseActivity implements NewsListView, NavigationView.OnNavigationItemSelectedListener {
    private static final String START_TOOLBAR_TITLE = "";
    private static final String[] PERMISSIONS =
        { Manifest.permission.ACCESS_FINE_LOCATION, Manifest.permission.ACCESS_COARSE_LOCATION };
    private static final int OFFSCREEN_PAGE_LIMIT = 4;
    private static final String LOCATION = "location";
    private static final String CATEGORY = "category";

    @BindView(R.id.toolbar_newslist)
    Toolbar toolbar;
    @BindView(R.id.drawerlayout_newslist)
    DrawerLayout drawerLayout;
    @BindView(R.id.navigationview_newslist_menu)
    NavigationView navigationView;
    @BindView(R.id.viewpager_newslist)
    ViewPager viewPager;
    @BindView(R.id.tablayout_newslist)
    TabLayout tabLayout;

    @InjectPresenter
    NewsListPresenter presenter;
    @Inject
    Provider<NewsListPresenter> presenterProvider;

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

        presenter.initPagerAdapter(getSupportFragmentManager());
        setupViewPager();

        presenter.checkLocationPermission(true);
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
            case android.R.id.home: {
                drawerLayout.openDrawer(GravityCompat.START);
                break;
            }

            case R.id.action_location: {
                presenter.changeLocationAction();
                break;
            }

            case R.id.action_add: {
                presenter.changeCategoryAction();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        drawerLayout.closeDrawers();
        switch (item.getItemId()) {
            case R.id.nav_manage_tab: {
                //TODO
                break;
            }

            case R.id.nav_logout: {
                presenter.logOutAction();
                break;
            }
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public void showRequestPermission(int permissionCode) {
        ActivityCompat.requestPermissions(this, PERMISSIONS, permissionCode);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String permissions[],
        @NonNull int[] grantResults) {
        presenter.onRequestPermissionsResult(requestCode, grantResults);
    }

    @Override
    public void showAddCategoryDialog() {
        CategoryDialog categoryDialog = new CategoryDialog();
        categoryDialog.show(getSupportFragmentManager(), CATEGORY);
        categoryDialog.setDialogResult(new OnCategoryDialogResult() {
            @Override
            public void categorySelectEvent(String category) {
                presenter.addTitleEvent(category);
            }
        });
    }

    @Override
    public void showLocationDialog() {
        LocationDialog locationDialog = new LocationDialog();
        locationDialog.show(getSupportFragmentManager(), LOCATION);
        locationDialog.setDialogResult(new OnLocationDialogResult() {
            @Override
            public void countrySelectEvent(String country, String countryCode) {
                presenter.countrySelectEvent(country, countryCode);
            }

            @Override
            public void currentLocationEvent() {
                presenter.checkLocationPermission(false);
            }
        });
    }

    @Override
    public void changeCategoryList() {

    }

    @Override
    public void setToolbarTitle(String title) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(title);
    }

    private void setupViewPager() {
        viewPager.setAdapter(presenter.getNewsPagerAdapter());
        viewPager.setOffscreenPageLimit(OFFSCREEN_PAGE_LIMIT);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setTitle(START_TOOLBAR_TITLE);
        }
    }

    private void setupMenu() {
        navigationView.setNavigationItemSelectedListener(this);
    }

}
