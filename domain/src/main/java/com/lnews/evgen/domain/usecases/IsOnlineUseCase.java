package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class IsOnlineUseCase {
    private final IRepository repository;

    @Inject
    IsOnlineUseCase(IRepository repository) {
        this.repository = repository;
    }

    public boolean execute() {
        return repository.isOnline();
    }
}
