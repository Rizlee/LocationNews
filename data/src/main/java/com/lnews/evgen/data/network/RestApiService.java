package com.lnews.evgen.data.network;

import com.google.firebase.auth.AuthResult;
import com.lnews.evgen.data.util.firebase.RxFirebaseAuth;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import io.reactivex.Single;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Singleton;
import com.google.firebase.auth.FirebaseAuth;

@Singleton
public class RestApiService {

    private final RestApi restApi;
    private final RxFirebaseAuth rxFirebaseAuth;

    @Inject
    RestApiService(RestApi restRestApi, RxFirebaseAuth rxFirebaseAuth) {
        this.restApi = restRestApi;
        this.rxFirebaseAuth = rxFirebaseAuth;
    }

    public Maybe<AuthResult> register(String email, String password) {
        return rxFirebaseAuth.createUserWithEmailAndPassword(FirebaseAuth.getInstance(), email,
            password);
    }

    public Maybe<AuthResult> auth(String email, String password) {
        return rxFirebaseAuth.signInWithEmailAndPassword(FirebaseAuth.getInstance(), email, password);
    }

    public Completable sendResetRequest(String email) {
        return rxFirebaseAuth.sendPasswordResetEmail(FirebaseAuth.getInstance(), email);
    }

    public String getToken() {
        return Objects.requireNonNull(FirebaseAuth.getInstance().getCurrentUser()).getIdToken(true).toString();
    }

    public Single<RootObject> getNewsByCategory(String country, String category){
        return restApi.getNewsByCategory(country, category);
    }
}
