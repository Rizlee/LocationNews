package com.lnews.evgen.locationnews.features.newslisttab;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseFragmentView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface NewsListTabView extends BaseFragmentView {
    void showProgressBar();

    void hideProgressBar();
}
