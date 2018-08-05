package com.lnews.evgen.locationnews.features.registration;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

@InjectViewState
@PerFragment(RegistrationFragment.class)
public class RegistrationPresenter extends BasePresenter<RegistrationView> {

    private static final int MIN_PASSWORD_LENGTH = 6;

    private final AuthorizationInteractor authorizationInteractor;

    @Inject
    RegistrationPresenter(AuthorizationInteractor authorizationInteractor) {
        this.authorizationInteractor = authorizationInteractor;
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearRegistrationComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    public void btnRegisterListener(String email, String password) {
        if (email.isEmpty()) {
            getViewState().showToast(R.string.auth_email_field_empty);
            return;
        }
        if (password.isEmpty()) {
            getViewState().showToast(R.string.auth_password_field_empty);
            return;
        } else if (password.length() < MIN_PASSWORD_LENGTH) {
            getViewState().showToast(R.string.auth_password_less_than_6);
            return;
        }

        authorizationInteractor.register(email, password, new DisposableSingleObserver() {
            @Override
            public void onSuccess(Object o) {
                getViewState().onRegisterSuccess();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }
}
