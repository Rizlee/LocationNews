package com.lnews.evgen.data.repository;

import android.content.Context;
import android.location.Address;
import android.location.Location;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.google.firebase.auth.AuthResult;
import com.lnews.evgen.data.local.Cache;
import com.lnews.evgen.data.local.db.Storage;
import com.lnews.evgen.data.network.LocationService;
import com.lnews.evgen.data.network.RestApiService;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;
import org.reactivestreams.Subscriber;

public class Repository implements IRepository {
    private final RestApiService networkRepository;
    private final Cache cache;
    private final LocationService locationRepository;
    private final Storage storage;
    private final Context context;

    @Inject
    Repository(RestApiService networkRepository, Cache cache, LocationService locationRepository,
        Storage storage, Context context) {
        this.networkRepository = networkRepository;
        this.cache = cache;
        this.locationRepository = locationRepository;
        this.storage = storage;
        this.context = context;
    }

    @Override
    public Single<AuthResult> register(String email, String password) {
        return networkRepository.register(email, password).toSingle();
    }

    @Override
    public Single auth(String email, String password) {
        return networkRepository.auth(email, password).toSingle();
    }

    @Override
    public Completable sendResetPassRequest(String email) {
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
    public void disableTutorialNeed() {
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
    public Observable<Address> getAddressFromLocation(
        com.lnews.evgen.domain.entities.Location location) {
        return locationRepository.getAddressFromLocation((Location) location.getLocation())
            .toObservable();
    }

    @Override
    public Single<List<Category>> getCategoriesOffline() {
        return storage.getCategories();
    }

    @Override
    public Completable insertCategoriesOffline(List<Category> categories) {
        return storage.insertCategories(categories);
    }

    @Override
    public Completable removeCategoryOffline(Category category) {
        return storage.removeCategory(category);
    }

    @Override
    public Completable insertCategoryOffline(Category category) {
        return storage.insertCategory(category);
    }

    @Override
    public Completable removeNewsByCategoryFromDB(String category) {
        return storage.clearDescriptionByKey(category);
    }

    @Override
    public Completable insertNewsByCategoryInDB(String category, List<Article> articles) {
        return storage.insertDescriptions(category, articles);
    }

    @Override
    public Single<RootObject> getNewsByCategory(String country, String category) {
        if (isOnline()) {
            return networkRepository.getNewsByCategory(country, category);
        } else {
            return storage.getDescriptionsByKey(category);
        }
    }

    @Override
    public void saveCountry(String country) {
        cache.setCountry(country);
    }

    @Override
    public String getCountry() {
        return cache.getCountry();
    }

    @Override
    public void saveCountryCode(String countryCode) {
        cache.setCountryCode(countryCode);
    }

    @Override
    public String getCountryCode() {
        return cache.getCountryCode();
    }

    @Override
    public void resetToken() {
        cache.resetToken();
    }

    private boolean isOnline() {
        try {
            ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);
            assert connectivityManager != null;
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            return networkInfo != null && networkInfo.isAvailable() && networkInfo.isConnected();
        } catch (Exception ignored) {
        }
        return false;
    }
}
