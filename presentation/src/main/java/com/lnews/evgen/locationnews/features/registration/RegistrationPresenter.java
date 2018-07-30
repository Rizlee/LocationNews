package com.lnews.evgen.locationnews.features.registration;

import android.text.Editable;
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

    private final AuthorizationInteractor authorizationInteractor;

    @Inject RegistrationPresenter(AuthorizationInteractor authorizationInteractor) {
        this.authorizationInteractor = authorizationInteractor;
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearRegistrationComponent();
    }

    public void btnRegisterListener(Editable email, Editable password) {
        if (email.length() == 0) {
            getViewState().showToast(R.string.email_edittext_empty);
            return;
        }
        if (password.length() == 0) {
            getViewState().showToast(R.string.password_edittext_empty);
            return;
        }else if(password.length() < 6){
            getViewState().showToast(R.string.password_edittext_less_than_6);
            return;
        }

        authorizationInteractor.register(email.toString(), password.toString(),
            new DisposableSingleObserver() {
                @Override public void onSuccess(Object o) {
                    getViewState().onRegisterSuccess();
                }

                @Override public void onError(Throwable e) {
                    getViewState().showToast(e.getMessage());
                }
            });

    }
}
