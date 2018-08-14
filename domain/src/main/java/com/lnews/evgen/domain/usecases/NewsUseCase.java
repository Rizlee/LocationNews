package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import java.util.List;
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

        public NewsData(String country, String category) {
            this.country = country;
            this.category = category;
        }

        public String getCountry() {
            return country;
        }

        public String getCategory() {
            return category;
        }
    }
}
