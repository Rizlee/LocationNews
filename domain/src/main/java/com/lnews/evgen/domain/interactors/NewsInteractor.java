package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.InsertDescriptionUseCase;
import com.lnews.evgen.domain.usecases.IsOnlineUseCase;
import com.lnews.evgen.domain.usecases.NewsUseCase;
import com.lnews.evgen.domain.usecases.RemoveDescriptionUseCase;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import javax.inject.Inject;

public class NewsInteractor extends BaseInteractor {
    private final NewsUseCase newsUseCase;
    private final InsertDescriptionUseCase insertDescriptionUseCase;
    private final RemoveDescriptionUseCase removeDescriptionUseCase;
    private final IsOnlineUseCase isOnlineUseCase;

    @Inject
    NewsInteractor(NewsUseCase newsUseCase, InsertDescriptionUseCase insertDescriptionUseCase,
        RemoveDescriptionUseCase removeDescriptionUseCase, IsOnlineUseCase isOnlineUseCase) {
        this.newsUseCase = newsUseCase;
        this.insertDescriptionUseCase = insertDescriptionUseCase;
        this.removeDescriptionUseCase = removeDescriptionUseCase;
        this.isOnlineUseCase = isOnlineUseCase;
    }

    public void getNews(String country, String category,
        DisposableSingleObserver<RootObject> observer) {
        execute(newsUseCase, new NewsUseCase.NewsData(country, category), observer);
    }

    public void insertDescription(String category, List<Article> articles,
        DisposableCompletableObserver observer) {
        execute(insertDescriptionUseCase,
            new InsertDescriptionUseCase.DescriptionData(category, articles), observer);
    }

    public void removeDescription(String category, DisposableCompletableObserver observer) {
        execute(removeDescriptionUseCase, category, observer);
    }

    public boolean isOnline(){
        return isOnlineUseCase.execute();
    }
}
