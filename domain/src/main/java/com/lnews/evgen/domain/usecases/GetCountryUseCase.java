package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class GetCountryUseCase {
    private final IRepository repository;

    @Inject
    GetCountryUseCase(IRepository repository) {
        this.repository = repository;
    }

    public String execute() {
        return repository.getCountry();
    }
}
