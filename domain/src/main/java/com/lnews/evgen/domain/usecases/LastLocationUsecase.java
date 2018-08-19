package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Single;
import javax.inject.Inject;

public class LastLocationUsecase {
    private final IRepository repository;

    @Inject
    LastLocationUsecase(IRepository repository) {
        this.repository = repository;
    }

    public Single execute() {
        return repository.getLastLocation();
    }
}
