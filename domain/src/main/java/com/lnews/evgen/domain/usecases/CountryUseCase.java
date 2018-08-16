package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class CountryUseCase {
    private final IRepository repository;

    @Inject
    CountryUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute(String country) {
        repository.saveCountry(country);
    }
}
