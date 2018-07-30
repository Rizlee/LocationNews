package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class IsFirstLaunchUseCase {
    private final IRepository repository;

    @Inject
    IsFirstLaunchUseCase(IRepository repository) {
        this.repository = repository;
    }

    public boolean execute() {
        return repository.isFirstLaunch();
    }
}
