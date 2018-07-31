package com.lnews.evgen.locationnews.features.authorization;

import android.text.Editable;
import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.interactors.AuthorizationInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

@InjectViewState @PerFragment(AuthFragment.class) public class AuthPresenter
    extends BasePresenter<AuthView> {

    private final AuthorizationInteractor authorizationInteractor;

    @Inject AuthPresenter(AuthorizationInteractor authorizationInteractor) {
        this.authorizationInteractor = authorizationInteractor;
    }

    @Override protected void clearComponent() {
        Injector.getInstance().clearAuthComponent();
    }

    public void btnChooseFragmentListener(int id) {
        getViewState().sendEvent(id);
    }

    //todo убрать editable
    public void btnLoginListener(Editable email, Editable password) {
        if (email.length() == 0) {
            getViewState().showToast(R.string.auth_email_field_empty);
            return;
        }

        if (password.length() == 0) {
            getViewState().showToast(R.string.auth_password_field_empty);
            return;
        }

        authorizationInteractor.auth(email.toString(), password.toString(),
            new DisposableSingleObserver() {
                @Override public void onSuccess(Object o) {
                    getViewState().onAuthSuccess();
                }

                @Override public void onError(Throwable e) {
                    getViewState().showToast(e.getMessage());
                }
            });
    }
}
