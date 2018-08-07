package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import javax.inject.Inject;

public class ResetTokenUseCase {
    private final IRepository repository;

    @Inject
    ResetTokenUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute() {
        repository.resetToken();
    }
}
