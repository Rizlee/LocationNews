package com.lnews.evgen.locationnews.features.authorization;

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

public class AuthFragment extends BaseFragment implements AuthView{

    private AuthenticationEventListener authenticationEventListener;

    @BindView(R.id.edittext_auth_email)
    EditText editTextEmail;
    @BindView(R.id.edittext_auth_password)
    EditText editTextPassword;

    @OnClick(R.id.button_auth_forgot_pass) //TODO
    public void btnForgotPassListener(){
       // presenter.btnChooseFragmentListener(R.id.button_forgot_pass);*/
        authenticationEventListener.buttonPressedEvent(R.id.button_auth_forgot_pass);
    }

    @OnClick(R.id.button_auth_new_member)
    public void btnNewMemberListener(){
        /*presenter.btnChooseFragmentListener(R.id.button_new_member);*/
        authenticationEventListener.buttonPressedEvent(R.id.button_auth_new_member);
    }

    @OnClick(R.id.button_auth_login)
    public void btnLoginListener(){
        presenter.btnLoginListener(editTextEmail.getText(), editTextPassword.getText());
    }

    @InjectPresenter
    AuthPresenter presenter;
    @Inject
    Provider<AuthPresenter> presenterProvider;

    @ProvidePresenter
    AuthPresenter providePresenter(){
        return presenterProvider.get();
    }

    public static Fragment getInstance(){
        return new AuthFragment();
    }

    public AuthFragment(){

    }

    //todo deprecated
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        authenticationEventListener = (AuthenticationEventListener) activity;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_auth, container, false);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusAuthComponent().inject(this);
    }

    //todo избавиться от id
    @Override
    public void sendEvent(int id){
        authenticationEventListener.buttonPressedEvent(id);
    }

    @Override public void onAuthSuccess() {
        authenticationEventListener.authSuccessEvent();
    }
}
