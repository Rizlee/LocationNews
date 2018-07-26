package com.lnews.evgen.locationnews.features.base;

import android.content.Intent;
import android.support.annotation.StringRes;
import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseView extends MvpView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showActivity(Intent intent);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showToast(String message);

    @StateStrategyType(OneExecutionStateStrategy.class)
    void showToast(@StringRes int resId);
}
