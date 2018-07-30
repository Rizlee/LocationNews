package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

public class ResetPassUseCase extends CompletableUseCase<ResetPassUseCase.ResetPassData> {

    @Inject ResetPassUseCase(IRepository repository, ExecutionThread executionThread, PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override protected Completable buildUseCase(ResetPassUseCase.ResetPassData params) {
        return repository.sendResetPassRequest(params.getEmail());
    }

    public static final class ResetPassData {
        private final String email;

        public ResetPassData(String email) {
            this.email = email;
        }

        String getEmail() {
            return email;
        }
    }
}
