package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class ClearPreferencesUseCase {
    private final IRepository repository;

    @Inject
    ClearPreferencesUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.clearPreferences();
    }
}
