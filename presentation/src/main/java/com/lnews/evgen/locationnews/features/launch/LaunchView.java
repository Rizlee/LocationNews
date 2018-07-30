package com.lnews.evgen.locationnews.features.launch;

import android.content.Intent;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;
import com.lnews.evgen.locationnews.features.base.BaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
interface LaunchView extends BaseActivityView {

    void startNextActivity(Intent intent);

}
