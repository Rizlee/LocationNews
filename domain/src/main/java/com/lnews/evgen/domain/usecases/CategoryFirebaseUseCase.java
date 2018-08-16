package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.repository.IRepository;
import java.util.List;
import javax.inject.Inject;

public class CategoryFirebaseUseCase {
    private final IRepository repository;

    @Inject
    CategoryFirebaseUseCase(IRepository repository) {
        this.repository = repository;
    }

    public void execute(List<String> categories) {
        repository.saveCategoriesFirebase(categories);
    }
}
