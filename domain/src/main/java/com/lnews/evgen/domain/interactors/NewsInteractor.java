package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.NewsUseCase;
import io.reactivex.Single;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

public class NewsInteractor extends BaseInteractor {
    private final NewsUseCase newsUseCase;

    @Inject
    NewsInteractor(NewsUseCase newsUseCase) {
        this.newsUseCase = newsUseCase;
    }

    public void getNews(String country, String category, String keyWord,DisposableSingleObserver<RootObject> observer) {
        execute(newsUseCase,new NewsUseCase.NewsData(country, category, keyWord), observer);
    }

}
