package com.lnews.evgen.domain.repository;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.Location;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
import io.reactivex.Flowable;
import io.reactivex.Observable;
import io.reactivex.Single;
import java.util.List;

public interface IRepository {
    Single register(String email, String password);

    Single auth(String email, String password);

    Completable sendResetPassRequest(String email);

    boolean isAuth();

    boolean isTutorialNeed();

    void disableTutorialNeed();

    void saveToken();

    void clearPreferences();

    boolean isOnline();

    Completable clearDB();

    String getToken();

    Single getLastLocation();

    Observable getAddressFromLocation(Location location);

    Single<List<Category>> getCategoriesOffline();

    Completable insertCategoriesOffline(List<Category> categories);

    Completable removeCategoryOffline(Category category);

    Completable insertCategoryOffline(Category category);

    Completable removeNewsByCategoryFromDB(String category);

    Completable insertNewsByCategoryInDB(String category, List<Article> articles);

    Single<RootObject> getNewsByCategory(String country, String category);

    void saveCategoriesFirebase(List<String> categories);

    void saveCountry(String country);

    String getCountry();

    void saveCountryCode(String countryCode);

    String getCountryCode();

    Single getCategoriesFirestore();
}
