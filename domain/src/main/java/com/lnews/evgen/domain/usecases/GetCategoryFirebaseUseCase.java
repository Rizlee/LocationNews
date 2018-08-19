package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import javax.inject.Inject;

public class GetCategoryFirebaseUseCase extends SingleUseCase<Void, Single> {

    @Inject
    GetCategoryFirebaseUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Single buildUseCase(Void aVoid) {
        return repository.getCategoriesFirestore();
    }
}