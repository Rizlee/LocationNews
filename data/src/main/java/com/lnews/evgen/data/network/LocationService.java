package com.lnews.evgen.data.network;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import com.patloew.rxlocation.RxLocation;
import io.reactivex.Maybe;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class LocationService {
    private final RxLocation rxLocation;

    @Inject
    LocationService(RxLocation rxLocation) {
        this.rxLocation = rxLocation;
    }

    @SuppressWarnings({"MissingPermission"})
    public Maybe<Location> getLastLocation() {
        return rxLocation.location().lastLocation();
    }

    public Maybe<Address> getAddressFromLocation(Location location){
        return rxLocation.geocoding().fromLocation(location);
    }
}
