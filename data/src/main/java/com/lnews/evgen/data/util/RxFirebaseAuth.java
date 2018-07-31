package com.lnews.evgen.data.util;

import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.lnews.evgen.data.util.RxHandler;
import io.reactivex.Completable;
import io.reactivex.CompletableEmitter;
import io.reactivex.CompletableOnSubscribe;
import io.reactivex.Maybe;

public class RxFirebaseAuth {

    public static Maybe<AuthResult> createUserWithEmailAndPassword(final FirebaseAuth firebaseAuth,
        final String email, final String password) {
        return Maybe.create(emitter -> RxHandler.assignOnTask(emitter,
            firebaseAuth.createUserWithEmailAndPassword(email, password)));
    }

    public static Maybe<AuthResult> signInWithEmailAndPassword(final FirebaseAuth firebaseAuth,
        final String email, final String password) {
        return Maybe.create(emitter -> RxHandler.assignOnTask(emitter,
            firebaseAuth.signInWithEmailAndPassword(email, password)));
    }

    public static Completable sendPasswordResetEmail(final FirebaseAuth firebaseAuth, final String email) {
        return Completable.create(
            emitter -> RxCompletableHandler.assignOnTask(emitter, firebaseAuth.sendPasswordResetEmail(email)));
    }
}

