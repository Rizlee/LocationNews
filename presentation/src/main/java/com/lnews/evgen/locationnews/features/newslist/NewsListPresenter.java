package com.lnews.evgen.locationnews.features.newslist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentManager;
import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.domain.interactors.LocationInteractor;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationActivity;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.newslist.adapter.NewsPagerAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;

@SuppressWarnings("ALL")
@InjectViewState
@PerActivity(NewsListActivity.class)
public class NewsListPresenter extends BasePresenter<NewsListView> {
    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    private final LocationInteractor locationInteractor;
    private final AuthorizationInteractor authorizationInteractor;
    private final NewsInteractor newsInteractor;

    private NewsPagerAdapter newsPagerAdapter;
    private List<String> titles;
    private String country = "";
    private String countryCode = "";

    @Inject
    NewsListPresenter(LocationInteractor locationInteractor,
        AuthorizationInteractor authorizationInteractor, NewsInteractor newsInteractor) {
        this.locationInteractor = locationInteractor;
        this.authorizationInteractor = authorizationInteractor;
        this.newsInteractor = newsInteractor;
        titles = new ArrayList<>();
        initTitles();
        initCountry();
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

    public void initPagerAdapter(FragmentManager fragmentManager) {
        newsPagerAdapter = new NewsPagerAdapter(fragmentManager, titles, countryCode);
    }

    private boolean isPermissionGranted() {
        return ActivityCompat.checkSelfPermission(context, Manifest.permission.ACCESS_FINE_LOCATION)
            == PackageManager.PERMISSION_GRANTED
            && ActivityCompat.checkSelfPermission(context,
            Manifest.permission.ACCESS_COARSE_LOCATION) == PackageManager.PERMISSION_GRANTED;
    }

    private void getAddressFromLocation(Location location) {
        locationInteractor.getAddressFromLocation(
            new com.lnews.evgen.domain.entities.Location(location))
            .subscribe(new Observer<Address>() {
                @Override
                public void onSubscribe(Disposable d) {

                }

                @Override
                public void onNext(Address address) {
                    countrySelectEvent(address.getCountryName(), address.getCountryCode());
                }

                @Override
                public void onError(Throwable e) {
                    getViewState().showToast(R.string.newslist_location_error);
                }

                @Override
                public void onComplete() {

                }
            });
    }

    private void initTitles() {
        newsInteractor.getCategories(new DisposableSingleObserver<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                for (int i = 0; i< categories.size(); i++){
                    titles.add(categories.get(i).categoryName);
                    newsPagerAdapter.notifyDataSetChanged();
                }
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(R.string.newslist_db_connection_error);
            }
        });
    }

    private void initCountry(){
        country = newsInteractor.getCountry();
        countryCode = newsInteractor.getCountryCode();
    }

    public void getLastLocation() {
        locationInteractor.getLastLocation().subscribe(new SingleObserver<Location>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Location location) {
                getAddressFromLocation(location);
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }

    public void checkLocationPermission(boolean isRestoreState) {
        if (!isRestoreState) {
            if (isPermissionGranted()) {
                getLastLocation();
            } else {
                getViewState().showRequestPermission(PERMISSIONS_REQUEST_LOCATION);
            }
        }else {
            getViewState().setToolbarTitle(country);
        }
    }

    public void onRequestPermissionsResult(int requestCode, int[] grantResults) {
        switch (requestCode) {
            case PERMISSIONS_REQUEST_LOCATION: {
                if (grantResults.length > 0
                    && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    getLastLocation();
                } else {
                    getViewState().showToast(R.string.all_permissions_not_granted_description);
                }
            }
        }
    }

    public void changeLocationAction() {
        getViewState().showLocationDialog();
    }

    public void changeCategoryAction() {
        getViewState().showAddCategoryDialog();
    }

    public void manageTabsAction(){
        getViewState().showManageCategoryDialog(titles.toArray(new String[0]));
    }

    public void countrySelectEvent(String country, String countryCode) {
        this.country = country;
        this.countryCode = countryCode;

        newsInteractor.saveCountry(country);
        newsInteractor.saveCountryCode(countryCode);

        getViewState().setToolbarTitle(country);
        newsPagerAdapter.updateCountryCode(countryCode);
    }

    public void addTitleEvent(String title) {
        newsInteractor.insertCategory(new Category(title), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });
        titles.add(title);
        newsPagerAdapter.notifyDataSetChanged();
    }

    public void deleteCategoryEvent(int id){
        newsInteractor.removeCategory(new Category(titles.get(id)), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        newsInteractor.removeDescription(titles.get(id), new DisposableCompletableObserver() {
            @Override
            public void onComplete() {

            }

            @Override
            public void onError(Throwable e) {

            }
        });

        titles.remove(id);
        newsPagerAdapter.notifyDataSetChanged();
    }

    public void logOutAction() {
        authorizationInteractor.resetToken();
        getViewState().showActivity(AuthenticationActivity.getActivityIntent(context));
        getViewState().finishActivity();
    }

    public NewsPagerAdapter getNewsPagerAdapter(){
        return newsPagerAdapter;
    }

    public void setupToolbarTitle(){
        getViewState().setToolbarTitle(country);
    }

    public void refreshNews(){
        newsPagerAdapter.notifyDataSetChanged();
    }
}
