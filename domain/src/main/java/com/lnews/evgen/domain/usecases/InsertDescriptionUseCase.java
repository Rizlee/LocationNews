package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import java.util.List;
import javax.inject.Inject;

public class InsertDescriptionUseCase extends
    CompletableUseCase<InsertDescriptionUseCase.DescriptionData> {

    @Inject
    InsertDescriptionUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Completable buildUseCase(DescriptionData descriptionData) {
        return repository.insertNewsByCategoryInDB(descriptionData.getCategory(), descriptionData.getArticles());
    }

    public static final class DescriptionData{
        String category;
        List<Article> articles;

        public DescriptionData(String category, List<Article> articles) {
            this.category = category;
            this.articles = articles;
        }

        public String getCategory() {
            return category;
        }

        public List<Article> getArticles() {
            return articles;
        }
    }
}