package com.lnews.evgen.locationnews.features.newslist;

import android.Manifest;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.LocationInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import io.reactivex.Observer;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import java.util.ArrayList;
import javax.inject.Inject;

@SuppressWarnings("ALL")
@InjectViewState
@PerActivity(NewsListActivity.class)
public class NewsListPresenter extends BasePresenter<NewsListView> {

    private static final int PERMISSIONS_REQUEST_LOCATION = 1;

    private final LocationInteractor locationInteractor;

    private ArrayList<NewsListTabFragment> newsListTabFragments;
    private ArrayList<String> titles;

    private Address lastAddress;
        //TODO возможно при каждом успешном определении сохранять в бд(вместе со списком новостей)

    @Inject
    NewsListPresenter(LocationInteractor locationInteractor) {
        this.locationInteractor = locationInteractor;
        initTitles();
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
                    lastAddress = address;
                    getViewState().setToolbarTitle(address.getCountryName());
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

    public void checkLocationPermission() {
        if (isPermissionGranted()) {
            getLastLocation();
        } else {
            getViewState().showRequestPermission(PERMISSIONS_REQUEST_LOCATION);
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

    public void initTitles() { //TODO категории(тайтлы) буду получать от firebase'a, если не доступен то с бд
        titles = new ArrayList<>();
        titles.add("technology");
        titles.add("science");
        titles.add("sports");
    }

    public ArrayList<String> getTitles() {
        return titles;
    }
}
