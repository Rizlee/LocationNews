package com.lnews.evgen.data.repository;

import com.lnews.evgen.data.local.Cache;
import com.lnews.evgen.data.network.RestApiService;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Completable;
import javax.inject.Inject;

public class Repository implements IRepository {
    private final RestApiService networkRepository;
    private final Cache cache;

    @Inject
    Repository(RestApiService networkRepository, Cache cache) {
        this.networkRepository = networkRepository;
        this.cache = cache;
    }

    @Override
    public Completable login(final String email, final String password) {
        return networkRepository
            .login(email, password)
            .toCompletable();
    }

    @Override
    public boolean isAuth() {
        return true;
    }
}
