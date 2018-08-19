package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.AuthorizationUseCase;
import com.lnews.evgen.domain.usecases.ClearDBUseCase;
import com.lnews.evgen.domain.usecases.ClearPreferencesUseCase;
import com.lnews.evgen.domain.usecases.GetTokenUseCase;
import com.lnews.evgen.domain.usecases.RegistrationUseCase;
import com.lnews.evgen.domain.usecases.ResetPassUseCase;
import com.lnews.evgen.domain.usecases.TokenUseCase;
import io.reactivex.Completable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

public class AuthorizationInteractor extends BaseInteractor {
    private final RegistrationUseCase registrationUseCase;
    private final AuthorizationUseCase authorizationUseCase;
    private final ResetPassUseCase resetPassUseCase;
    private final TokenUseCase tokenUseCase;
    private final GetTokenUseCase getTokenUseCase;
    private final ClearPreferencesUseCase clearPreferencesUseCase;
    private final ClearDBUseCase clearDBUseCase;

    @Inject
    AuthorizationInteractor(RegistrationUseCase registrationUseCase,
        AuthorizationUseCase authorizationUseCase, ResetPassUseCase resetPassUseCase,
        TokenUseCase tokenUseCase, ClearPreferencesUseCase clearPreferencesUseCase,
        ClearDBUseCase clearDBUseCase, GetTokenUseCase getTokenUseCase) {
        this.registrationUseCase = registrationUseCase;
        this.authorizationUseCase = authorizationUseCase;
        this.resetPassUseCase = resetPassUseCase;
        this.tokenUseCase = tokenUseCase;
        this.getTokenUseCase = getTokenUseCase;
        this.clearPreferencesUseCase = clearPreferencesUseCase;
        this.clearDBUseCase = clearDBUseCase;
    }

    public void register(String email, String password, DisposableSingleObserver observer) {
        execute(registrationUseCase, new RegistrationUseCase.RegistrationData(email, password),
            observer);
    }

    public void auth(String email, String password, DisposableSingleObserver observer) {
        execute(authorizationUseCase, new AuthorizationUseCase.AuthorizationData(email, password),
            observer);
    }

    public void resetPassRequest(String email, DisposableCompletableObserver observer) {
        execute(resetPassUseCase, new ResetPassUseCase.ResetPassData(email), observer);
    }

    public void saveToken() {
        tokenUseCase.execute();
    }

    public String getToken() {
        return getTokenUseCase.execute();
    }

    public void clearPreferences() {
        clearPreferencesUseCase.execute();
    }

    public void clearDB(DisposableCompletableObserver observer){
        execute(clearDBUseCase, 1, observer);
    }
}
