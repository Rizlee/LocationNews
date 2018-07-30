package com.lnews.evgen.domain.repository;

import com.lnews.evgen.domain.entities.UserEntity;
import io.reactivex.Completable;
import io.reactivex.Single;

public interface IRepository {
    Single register(String email, String password);

    Single auth(String email, String password);

    Completable sendResetPassRequest(String email);

    boolean isAuth();

    boolean isFirstLaunch();

    void disableFirstLaunch();

    UserEntity getAuthUser();

    void setAuthUser(UserEntity user);
}
