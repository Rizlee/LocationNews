package com.lnews.evgen.data.network;

import com.lnews.evgen.domain.entities.LoginBody;
import com.lnews.evgen.domain.entities.UserEntity;
import io.reactivex.Observable;
import io.reactivex.Single;
import retrofit2.Response;
import retrofit2.http.Body;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RestApi {
    @POST("api/account/login")
    Single<UserEntity> logIn(@Body LoginBody loginBody);

    @POST("api/account/login")
    Observable<String> login(@Query("email") String email, @Query("password") String password);
}
