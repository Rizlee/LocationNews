package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class GetTokenUseCase {
    private final IRepository repository;

    @Inject
    GetTokenUseCase(IRepository repository) {
        this.repository = repository;
    }

    public String execute() {
        return repository.getToken();
    }
}
