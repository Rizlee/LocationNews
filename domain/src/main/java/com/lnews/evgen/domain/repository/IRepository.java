package com.lnews.evgen.domain.repository;

import io.reactivex.Completable;
import io.reactivex.Single;

public interface IRepository {
    Single register(String email, String password);

    Single auth(String email, String password);

    Completable sendResetPassRequest(String email);

    boolean isAuth();

    boolean isTutorialNeed();

    void disableTutorialNeed();

    void saveToken();
}
