package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import javax.inject.Inject;

public class NewsUseCase extends SingleUseCase<NewsUseCase.NewsData, RootObject> {

    @Inject
    NewsUseCase(IRepository repository, ExecutionThread threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    @Override
    protected Single<RootObject> buildUseCase(NewsUseCase.NewsData params) {
        return repository.getNewsByCategory(params.getCountry(), params.getCategory());
    }

    public static final class NewsData {
        private String country;
        private String category;
        private String keyWord;

        public NewsData(String country, String category, String keyWord) {
            this.country = country;
            this.category = category;
            this.keyWord = keyWord;
        }

        public String getCountry() {
            return country;
        }

        public String getCategory() {
            return category;
        }

        public String getKeyWord() {
            return keyWord;
        }
    }
}
