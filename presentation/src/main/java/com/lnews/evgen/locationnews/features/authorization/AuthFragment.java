package com.lnews.evgen.locationnews.features.authorization;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
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

public class AuthFragment extends BaseFragment implements AuthView{

    private AuthenticationEventListener authenticationEventListener;

    @BindView(R.id.edittext_auth_email)
    EditText editTextEmail;
    @BindView(R.id.edittext_auth_password)
    EditText editTextPassword;

    @InjectPresenter
    AuthPresenter presenter;
    @Inject
    Provider<AuthPresenter> presenterProvider;

    @ProvidePresenter
    AuthPresenter providePresenter(){
        return presenterProvider.get();
    }

    public static Fragment newInstance(){
        return new AuthFragment();
    }

    public AuthFragment(){

    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        authenticationEventListener = (AuthenticationEventListener) context;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @OnClick(R.id.button_auth_forgot_pass)
    public void btnForgotPassListener(){
        presenter.buttonForgotPassPressed();
        //showForgotPass();
    }

    @OnClick(R.id.button_auth_new_member)
    public void btnNewMemberListener(){
        presenter.buttonRegistrationPressed();
        //showRegistration();
    }

    @OnClick(R.id.button_auth_login)
    public void btnLoginListener(){
        presenter.btnLoginListener(editTextEmail.getText().toString(), editTextPassword.getText().toString());
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusAuthComponent().inject(this);
    }

    @Override
    public void showForgotPass() {
        authenticationEventListener.showForgotPassEvent();
    }

    @Override
    public void showRegistration() {
        authenticationEventListener.showRegistrationEvent();
    }

    @Override public void onAuthSuccess() {
        authenticationEventListener.authSuccessEvent();
    }
}
