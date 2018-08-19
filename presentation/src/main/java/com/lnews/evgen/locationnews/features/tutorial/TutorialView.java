package com.lnews.evgen.locationnews.features.tutorial;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface TutorialView extends BaseActivityView {
    void showItem(int id);
}
