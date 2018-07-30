package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class FirstLaunchUseCase {
    private final IRepository repository;

    @Inject
    FirstLaunchUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.disableFirstLaunch();
    }
}
