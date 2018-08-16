package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class CountryCodeUseCase {
    private final IRepository repository;

    @Inject
    CountryCodeUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute(String countryCode) {
        repository.saveCountryCode(countryCode);
    }
}
