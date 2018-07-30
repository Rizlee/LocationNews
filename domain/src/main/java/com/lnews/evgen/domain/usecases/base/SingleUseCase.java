package com.lnews.evgen.domain.usecases.base;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Single;

public abstract class SingleUseCase<InParam, OutParam> extends BaseUseCase {

    protected SingleUseCase(IRepository repository, ExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    protected abstract Single<OutParam> buildUseCase(InParam param);

    public Single<OutParam> execute(InParam param) {
        return buildUseCase(param)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

}
