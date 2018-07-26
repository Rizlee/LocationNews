package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.IsAuthUseCase;
import javax.inject.Inject;

public class LaunchInteractor extends BaseInteractor {

    private final IsAuthUseCase isAuthUseCase;

    @Inject
    LaunchInteractor(IsAuthUseCase isAuthUseCase) {
        this.isAuthUseCase = isAuthUseCase;
    }

    public boolean isAuth() {
        return isAuthUseCase.execute();
    }

}
