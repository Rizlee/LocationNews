package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.CompletableUseCase;
import io.reactivex.Completable;
import javax.inject.Inject;

public class AuthorizationUseCase extends
    CompletableUseCase<AuthorizationUseCase.AuthorizationData> {

    @Inject
    AuthorizationUseCase(IRepository repository, ExecutionThread executionThread, PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Completable buildUseCase(AuthorizationUseCase.AuthorizationData params) {
        return repository.login(params.getEmail(), params.getPassword());
    }

    public static final class AuthorizationData {
        private final String email;
        private final String password;

        public AuthorizationData(String email, String password) {
            this.email = email;
            this.password = password;
        }

        String getEmail() {
            return email;
        }

        String getPassword() {
            return password;
        }
    }
}
