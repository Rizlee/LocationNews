package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.AuthorizationUseCase;
import com.lnews.evgen.domain.usecases.RegistrationUseCase;
import com.lnews.evgen.domain.usecases.ResetPassUseCase;
import com.lnews.evgen.domain.usecases.TokenUseCase;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

public class AuthorizationInteractor extends BaseInteractor {
    private final RegistrationUseCase registrationUseCase;
    private final AuthorizationUseCase authorizationUseCase;
    private final ResetPassUseCase resetPassUseCase;
    private final TokenUseCase tokenUseCase;

    @Inject
    AuthorizationInteractor(RegistrationUseCase registrationUseCase, AuthorizationUseCase authorizationUseCase, ResetPassUseCase resetPassUseCase, TokenUseCase tokenUseCase) {
        this.registrationUseCase = registrationUseCase;
        this.authorizationUseCase = authorizationUseCase;
        this.resetPassUseCase = resetPassUseCase;
        this.tokenUseCase = tokenUseCase;
    }

    public void register(String email, String password, DisposableSingleObserver observer) {
        execute(registrationUseCase, new RegistrationUseCase.RegistrationData(email, password), observer);
    }

    public void auth(String email, String password, DisposableSingleObserver observer){
        execute(authorizationUseCase, new AuthorizationUseCase.AuthorizationData(email, password), observer);
    }

    public void resetPassRequest(String email, DisposableCompletableObserver observer){
        execute(resetPassUseCase, new ResetPassUseCase.ResetPassData(email), observer);
    }

    public void saveToken(){
        tokenUseCase.execute();
    }
}
