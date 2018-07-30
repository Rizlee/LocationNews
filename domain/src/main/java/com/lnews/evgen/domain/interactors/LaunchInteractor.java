package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.FirstLaunchUseCase;
import com.lnews.evgen.domain.usecases.IsAuthUseCase;
import com.lnews.evgen.domain.usecases.IsFirstLaunchUseCase;
import javax.inject.Inject;

public class LaunchInteractor extends BaseInteractor {

    private final IsAuthUseCase isAuthUseCase;
    private final IsFirstLaunchUseCase isFirstLaunchUseCase;
    private final FirstLaunchUseCase firstLaunchUseCase;

    @Inject
    LaunchInteractor(IsAuthUseCase isAuthUseCase,IsFirstLaunchUseCase isFirstLaunchUseCase, FirstLaunchUseCase firstLaunchUseCase) {
        this.isAuthUseCase = isAuthUseCase;
        this.isFirstLaunchUseCase = isFirstLaunchUseCase;
        this.firstLaunchUseCase = firstLaunchUseCase;
    }

    public boolean isAuth() {
        return isAuthUseCase.execute();
    }

    public boolean isFirstLaunch(){
        return isFirstLaunchUseCase.execute();
    }

    public void disableFirstLaunch(){
       firstLaunchUseCase.execute();
    }

}
