package com.lnews.evgen.locationnews.features.authorization;

import com.arellomobile.mvp.viewstate.strategy.SingleStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseFragmentView;

interface AuthView extends BaseFragmentView {
    @StateStrategyType(SingleStateStrategy.class)
    void showForgotPass();

    void showRegistration();

    void onAuthSuccess();
}
