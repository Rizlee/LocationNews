package com.lnews.evgen.domain.usecases.base;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Completable;

public abstract class CompletableUseCase<InParam> extends BaseUseCase {

    protected CompletableUseCase(IRepository repository, ExecutionThread executionThread, PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    protected abstract Completable buildUseCase(InParam param);

    public Completable execute(InParam param) {
        return buildUseCase(param)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }
}
