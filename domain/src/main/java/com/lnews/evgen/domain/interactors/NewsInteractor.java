package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.NewsUseCase;
import javax.inject.Inject;

public class NewsInteractor extends BaseInteractor {
    private final NewsUseCase newsUseCase;

    @Inject
    NewsInteractor(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }
}
