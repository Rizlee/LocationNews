package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import java.util.List;
import javax.inject.Inject;

public class InsertCategoriesUseCase extends CompletableUseCase<List<Category>> {

    @Inject
    InsertCategoriesUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Completable buildUseCase(List<Category> categories) {
        return  repository.insertCategories(categories);
    }
}
