package com.lnews.evgen.data.network;

import com.google.firebase.auth.AuthResult;
import com.lnews.evgen.data.local.Cache;
import com.lnews.evgen.data.util.RxFirebaseAuth;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.google.firebase.auth.FirebaseAuth;

@Singleton
public class RestApiService {

    private final RestApi restApi;

    private final Cache cache;

    @Inject
    RestApiService(RestApi restRestApi, Cache cache) {
        this.restApi = restRestApi;
        this.cache = cache;
    }

    //todo убрать RxFirebaseAuth статику
    public Maybe<AuthResult> register(String email, String password){
        return RxFirebaseAuth.createUserWithEmailAndPassword(FirebaseAuth.getInstance(),email, password);
    }

    public Maybe<AuthResult> auth(String email, String password){
        return RxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), email, password);
    }

    public Completable sendResetRequest(String email){
        return RxFirebaseAuth.sendPasswordResetEmail(FirebaseAuth.getInstance(), email);
    }
}
