package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

public class ClearDBUseCase extends CompletableUseCase<Integer> {

    @Inject
    ClearDBUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    public Completable execute() {
        return repository.clearDB();
    }

    @Override
    protected Completable buildUseCase(Integer i) {
        return repository.clearDB();
    }
}
