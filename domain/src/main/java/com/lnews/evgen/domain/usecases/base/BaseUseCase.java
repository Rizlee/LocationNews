package com.lnews.evgen.domain.usecases.base;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;

abstract class BaseUseCase {

    protected final IRepository repository;
    final ExecutionThread executionThread;
    final PostExecutionThread postExecutionThread;

    BaseUseCase(IRepository repository, ExecutionThread threadExecutor, PostExecutionThread postExecutionThread) {
        this.repository = repository;
        this.executionThread = threadExecutor;
        this.postExecutionThread = postExecutionThread;
    }
}
