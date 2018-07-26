package com.lnews.evgen.domain.usecases.base;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Flowable;

public abstract class FlowableUseCase<InParam, OutParam> extends BaseUseCase {

    protected FlowableUseCase(IRepository repository, ExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    protected abstract Flowable<OutParam> buildUseCase(InParam param);

    public Flowable<OutParam> execute(InParam param) {
        return buildUseCase(param)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }
}
