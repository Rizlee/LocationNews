package com.lnews.evgen.domain.usecases.base;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import io.reactivex.Observable;

public abstract class ObservableUseCase<InParam, OutParam> extends BaseUseCase {

    protected ObservableUseCase(IRepository repository, ExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        super(repository, threadExecutor, postExecutionThread);
    }

    protected abstract Observable<OutParam> buildUseCase(InParam param);

    public Observable<OutParam> execute(InParam param) {
        return buildUseCase(param)
                .subscribeOn(executionThread.getScheduler())
                .observeOn(postExecutionThread.getScheduler());
    }

}
