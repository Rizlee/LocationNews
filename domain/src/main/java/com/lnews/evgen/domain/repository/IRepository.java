package com.lnews.evgen.domain.repository;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.Location;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
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

    void resetToken();

    Single getLastLocation();

    Observable getAddressFromLocation(Location location);

    Single<RootObject> getNewsByCategory(String country, String category);

    Single<List<Category>> getCategories();

    Completable insertCategories(List<Category> categories);

    Completable removeCategory(Category category);

    Completable insertCategory(Category category);

    Single<List<Article>> getNewsFromDB(String category);

    Completable removeNewsByCategoryFromDB(String category);

    Completable insertNewsByCategoryInDB(String category, List<Article> articles);
}
