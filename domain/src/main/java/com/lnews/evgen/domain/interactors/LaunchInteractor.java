package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.TutorialNeedUseCase;
import com.lnews.evgen.domain.usecases.IsAuthUseCase;
import com.lnews.evgen.domain.usecases.IsTutorialNeedUseCase;
import javax.inject.Inject;

public class LaunchInteractor extends BaseInteractor {

    private final IsAuthUseCase isAuthUseCase;
    private final IsTutorialNeedUseCase isTutorialNeedUseCase;
    private final TutorialNeedUseCase tutorialNeedUseCase;

    @Inject
    LaunchInteractor(IsAuthUseCase isAuthUseCase,IsTutorialNeedUseCase isTutorialNeedUseCase, TutorialNeedUseCase tutorialNeedUseCase) {
        this.isAuthUseCase = isAuthUseCase;
        this.isTutorialNeedUseCase = isTutorialNeedUseCase;
        this.tutorialNeedUseCase = tutorialNeedUseCase;
    }

    public boolean isAuth() {
        return isAuthUseCase.execute();
    }

    public boolean isTutorialNeed(){
        return isTutorialNeedUseCase.execute();
    }

    public void disableTutorialNeed(){
       tutorialNeedUseCase.execute();
    }

}
