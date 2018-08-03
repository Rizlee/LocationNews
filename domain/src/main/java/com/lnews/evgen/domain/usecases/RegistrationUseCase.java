package com.lnews.evgen.domain.usecases;

import com.lnews.evgen.domain.executor.ExecutionThread;
import com.lnews.evgen.domain.executor.PostExecutionThread;
import com.lnews.evgen.domain.repository.IRepository;
import com.lnews.evgen.domain.usecases.base.SingleUseCase;
import io.reactivex.Single;
import javax.inject.Inject;

public class RegistrationUseCase
    extends SingleUseCase<RegistrationUseCase.RegistrationData, Single> {

    @Inject
    RegistrationUseCase(IRepository repository, ExecutionThread executionThread,
        PostExecutionThread postExecutionThread) {
        super(repository, executionThread, postExecutionThread);
    }

    @Override
    protected Single buildUseCase(RegistrationUseCase.RegistrationData params) {
        return repository.register(params.getEmail(), params.getPassword());
    }

    public static final class RegistrationData {
        private final String email;
        private final String password;

        public RegistrationData(String email, String password) {
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
