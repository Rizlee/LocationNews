package com.lnews.evgen.locationnews.features.newslist;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface NewsListView extends BaseView {
    void showAddCategoryDialog();

    void showLocationDialog();

    void changeCategoryList();

    void showRequestPermission(int permissionCode);

    void setToolbarTitle(String text);

    void setupViewPager();
}
