package com.lnews.evgen.data.util.firebase;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import io.reactivex.Completable;
import io.reactivex.Maybe;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class RxFirebaseAuth {

    @Inject
    public RxFirebaseAuth() {
    }

    public Maybe<AuthResult> createUserWithEmailAndPassword(final FirebaseAuth firebaseAuth,
        final String email, final String password) {
        return Maybe.create(emitter -> RxHandler.assignOnTask(emitter,
            firebaseAuth.createUserWithEmailAndPassword(email, password)));
    }

    public Maybe<AuthResult> signInWithEmailAndPassword(final FirebaseAuth firebaseAuth,
        final String email, final String password) {
        return Maybe.create(emitter -> RxHandler.assignOnTask(emitter,
            firebaseAuth.signInWithEmailAndPassword(email, password)));
    }

    public Completable sendPasswordResetEmail(final FirebaseAuth firebaseAuth, final String email) {
        return Completable.create(
            emitter -> RxCompletableHandler.assignOnTask(emitter, firebaseAuth.sendPasswordResetEmail(email)));
    }
}

