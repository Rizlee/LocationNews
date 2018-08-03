package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import javax.inject.Inject;

public class NewsUseCase extends SingleUseCase<NewsUseCase.NewsData, RootObject> {
    private static final String COUNTRY_KEY = "country=";
    private static final String CATEGORY_KEY = "category=";
    private static final String KEYWORD_KEY = "q=";
    private static final String AND_KEY = "&";

    @Inject
    NewsUseCase(IRepository repository, ExecutionThread threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    public String buildUrl(String country, String category, String keyWord) {
        StringBuilder stringBuilder = new StringBuilder();

        if (!country.isEmpty()) {
            stringBuilder.append(COUNTRY_KEY).append(country).append(AND_KEY);
        }

        if (!category.isEmpty()) {
            stringBuilder.append(CATEGORY_KEY).append(category).append(AND_KEY);
        }

        if (!keyWord.isEmpty()) {
            stringBuilder.append(KEYWORD_KEY).append(keyWord).append(AND_KEY);
        }

        return stringBuilder.toString();
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
