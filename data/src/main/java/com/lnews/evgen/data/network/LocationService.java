package com.lnews.evgen.data.network;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Location;
import android.support.v4.app.ActivityCompat;
import com.patloew.rxlocation.RxLocation;
import io.reactivex.Maybe;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocationService {

    private final RxLocation rxLocation;

    private final Context context;

    @Inject
    LocationService(RxLocation rxLocation, Context context) {
        this.rxLocation = rxLocation;
        this.context = context;
    }

    @SuppressWarnings({"MissingPermission"})
    public Maybe<Location> getLastLocation() {
        return rxLocation.location().lastLocation();
    }

    public Maybe<Address> getAddressFromLocation(Location location){
        return rxLocation.geocoding().fromLocation(location);
    }


}
