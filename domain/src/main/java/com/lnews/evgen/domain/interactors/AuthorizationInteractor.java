package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.AuthorizationUseCase;
import io.reactivex.observers.DisposableCompletableObserver;
import javax.inject.Inject;

public class AuthorizationInteractor extends BaseInteractor {
    private final AuthorizationUseCase authorizationUseCase;

    @Inject
    AuthorizationInteractor(AuthorizationUseCase authorizationUseCase) {
        this.authorizationUseCase = authorizationUseCase;
    }

    public void login(String email, String password, DisposableCompletableObserver observer) {
        execute(authorizationUseCase, new AuthorizationUseCase.AuthorizationData(email, password), observer);
    }
}
