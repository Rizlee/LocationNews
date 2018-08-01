package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class TokenUseCase {
    private final IRepository repository;

    @Inject
    TokenUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.saveToken();
    }
}
