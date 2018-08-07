package com.lnews.evgen.data.repository;

import android.location.Address;
import android.location.Location;
import com.google.firebase.auth.AuthResult;
import com.lnews.evgen.data.local.Cache;
import com.lnews.evgen.data.network.LocationService;
import com.lnews.evgen.data.network.RestApiService;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;
import javax.inject.Inject;

public class Repository implements IRepository {
    private final RestApiService networkRepository;
    private final Cache cache;
    private final LocationService locationRepository;

    @Inject
    Repository(RestApiService networkRepository, Cache cache, LocationService locationRepository) {
        this.networkRepository = networkRepository;
        this.cache = cache;
        this.locationRepository = locationRepository;
    }

    @Override
    public Single<AuthResult> register(String email, String password) {
        return networkRepository.register(email, password).toSingle();
    }

    @Override public Single auth(String email, String password) {
        return networkRepository.auth(email, password).toSingle();
    }

    @Override public Completable sendResetPassRequest(String email) {
        return networkRepository.sendResetRequest(email);
    }

    @Override
    public boolean isAuth() {
        return !cache.getToken().isEmpty();
    }

    @Override
    public boolean isTutorialNeed() {
        return cache.isTutorialNeed();
    }

    @Override
    public void disableTutorialNeed(){
        cache.setTutorialNeed(false);
    }

    @Override
    public void saveToken() {
        cache.setToken(networkRepository.getToken());
    }

    @Override
    public Single<Location> getLastLocation() {
        return locationRepository.getLastLocation().toSingle();
    }

    @Override
    public Observable<Address> getAddressFromLocation(com.lnews.evgen.domain.entities.Location location) {
        return locationRepository.getAddressFromLocation((Location)location.getLocation()).toObservable();
    }

    @Override
    public Single<RootObject> getNewsByCategory(String country, String category) {
        return networkRepository.getNewsByCategory(country, category);
    }

    @Override
    public void resetToken() {
        cache.resetToken();
    }
}
