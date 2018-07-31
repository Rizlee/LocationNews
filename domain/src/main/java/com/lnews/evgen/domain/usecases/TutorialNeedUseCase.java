package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class TutorialNeedUseCase {
    private final IRepository repository;

    @Inject
    TutorialNeedUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.disableTutorialNeed();
    }
}
