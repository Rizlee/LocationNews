package com.lnews.evgen.locationnews.features.description;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;
import com.lnews.evgen.locationnews.features.base.BaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface DescriptionView extends BaseActivityView{
    void showNews(String title, String date, String description, String imageUrl, String category);
}
