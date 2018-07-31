package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class IsTutorialNeedUseCase {
    private final IRepository repository;

    @Inject
    IsTutorialNeedUseCase(IRepository repository) {
        this.repository = repository;
    }

    public boolean execute() {
        return repository.isTutorialNeed();
    }
}
