package com.lnews.evgen.domain.repository;

import com.lnews.evgen.domain.entities.Location;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
import io.reactivex.Observable;
import io.reactivex.Single;

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
}
