package com.lnews.evgen.locationnews.features.registration;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import butterknife.BindView;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationEventListener;
import com.lnews.evgen.locationnews.features.base.BaseFragment;
import javax.inject.Inject;
import javax.inject.Provider;

public class RegistrationFragment extends BaseFragment implements RegistrationView {
    private AuthenticationEventListener authenticationEventListener;

    @BindView(R.id.edittext_email)
    EditText editTextEmail;
    @BindView(R.id.edittext_password)
    EditText editTextPassword;

    @OnClick(R.id.button_register)
    public void btnRegisterListener(){
        presenter.btnRegisterListener(editTextEmail.getText(), editTextPassword.getText());
    }

    @InjectPresenter
    RegistrationPresenter presenter;
    @Inject
    Provider<RegistrationPresenter> presenterProvider;

    @ProvidePresenter
    RegistrationPresenter providePresenter(){
        return presenterProvider.get();
    }

    public static Fragment getInstance(){
        return new RegistrationFragment();
    }

    public RegistrationFragment(){
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        authenticationEventListener = (AuthenticationEventListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_registration, container, false);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusRegistrationComponent().inject(this);
    }

    @Override public void onRegisterSuccess() {
        authenticationEventListener.authSuccessEvent();
    }
}
