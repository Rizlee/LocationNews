package com.lnews.evgen.locationnews.features.passrecovery;

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
import com.lnews.evgen.locationnews.features.base.BaseFragment;
import javax.inject.Inject;
import javax.inject.Provider;

public class PassRecoveryFragment extends BaseFragment implements PassRecoveryView{

    @BindView(R.id.edittext_passrecovery_email)
    EditText editTextEmail;

    @OnClick(R.id.button_passrecovery_reset)
    public void btnResetPassListener(){
        presenter.btnResetPassListener(editTextEmail.getText());
    }

    @InjectPresenter
    PassRecoveryPresenter presenter;
    @Inject
    Provider<PassRecoveryPresenter> presenterProvider;

    @ProvidePresenter
    PassRecoveryPresenter providePresenter(){
        return presenterProvider.get();
    }

    //todo исправить на new
    public static Fragment getInstance(){
        return new PassRecoveryFragment();
    }

    public PassRecoveryFragment(){

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_passrecovery, container, false);
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusPassRecoveryComponent().inject(this);
    }

    @Override public void hideKeyboard() {

    }
}
