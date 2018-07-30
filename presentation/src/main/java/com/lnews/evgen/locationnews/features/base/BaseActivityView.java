package com.lnews.evgen.locationnews.features.base;

import android.content.Intent;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

public interface BaseActivityView extends BaseView {

    @StateStrategyType(OneExecutionStateStrategy.class)
    void finishActivity();

    @StateStrategyType(OneExecutionStateStrategy.class)
    void startNextActivity(Intent intent);
}
