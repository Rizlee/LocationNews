package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.CategoryUseCase;
import javax.inject.Inject;

public class CategoryInteractor extends BaseInteractor {
    private final CategoryUseCase categoryUseCase;

    @Inject
    CategoryInteractor(CategoryUseCase categoryUseCase) {
        this.categoryUseCase = categoryUseCase;
    }
}
