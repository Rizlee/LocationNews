package com.lnews.evgen.data.network;

import com.lnews.evgen.data.local.Cache;
import com.lnews.evgen.domain.entities.LoginBody;
import com.lnews.evgen.domain.entities.UserEntity;
import io.reactivex.Single;
import javax.inject.Inject;
import javax.inject.Singleton;
import retrofit2.Response;

@Singleton
public class RestApiService{

    private final RestApi restApi;

    private final Cache cache;

    @Inject
    RestApiService(RestApi restRestApi, Cache cache) {
        this.restApi = restRestApi;
        this.cache = cache;
    }

    public Single<UserEntity> login(String email, String password){
        return restApi.logIn(new LoginBody(email, password));
    }
}
