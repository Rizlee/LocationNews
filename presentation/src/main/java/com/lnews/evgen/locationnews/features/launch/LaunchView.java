package com.lnews.evgen.locationnews.features.launch;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;

@StateStrategyType(OneExecutionStateStrategy.class)
interface LaunchView extends BaseActivityView {
}
