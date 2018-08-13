package com.lnews.evgen.data.repository;

import android.location.Address;
import android.location.Location;
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
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class Repository implements IRepository {
    private final RestApiService networkRepository;
    private final Cache cache;
    private final LocationService locationRepository;
    private final Storage storage;

    @Inject
    Repository(RestApiService networkRepository, Cache cache, LocationService locationRepository,
        Storage storage) {
        this.networkRepository = networkRepository;
        this.cache = cache;
        this.locationRepository = locationRepository;
        this.storage = storage;
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
    public Single<RootObject> getNewsByCategory(String country, String category) {
        return networkRepository.getNewsByCategory(country, category);
    }

    @Override
    public Single<List<Category>> getCategories() {
        return storage.getCategories();
    }

    @Override
    public Completable insertCategories(List<Category> categories) {
       return storage.insertCategories(categories);
    }

    @Override
    public Completable removeCategory(Category category) {
        return storage.removeCategory(category);
    }

    @Override
    public Completable insertCategory(Category category) {
        return storage.insertCategory(category);
    }

    //todo сделать оффлайн режим через репозиторий
    @Override
    public Single<List<Article>> getNewsFromDB(String category) {
        return storage.getDescriptionsByKey(category);
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
    public void resetToken() {
        cache.resetToken();
    }
}
