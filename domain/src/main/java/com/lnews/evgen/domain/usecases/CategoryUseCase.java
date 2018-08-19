package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import java.util.List;
import javax.inject.Inject;

public class CategoryUseCase extends SingleUseCase<Void,List<Category>> {

    @Inject
    CategoryUseCase(IRepository repository, ExecutionThread threadExecutor,
        PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    @Override
    protected Single<List<Category>> buildUseCase(Void aVoid) {
        return repository.getCategoriesOffline();
    }
}
