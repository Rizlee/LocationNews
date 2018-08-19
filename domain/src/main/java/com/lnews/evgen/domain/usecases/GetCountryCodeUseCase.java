package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class GetCountryCodeUseCase {
    private final IRepository repository;

    @Inject
    GetCountryCodeUseCase(IRepository repository) {
        this.repository = repository;
    }

    public String execute() {
        return repository.getCountryCode();
    }
}
