package com.lnews.evgen.locationnews.features.authorization;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseFragmentView;

@StateStrategyType(OneExecutionStateStrategy.class)
interface AuthView extends BaseFragmentView {
    void showForgotPass();

    void showRegistration();

    void onAuthSuccess();

    void showProgressBar();

    void hideProgressBar();
}
