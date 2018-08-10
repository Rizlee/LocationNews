package com.lnews.evgen.locationnews.features.newslist;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface NewsListView extends BaseActivityView {
    void showAddCategoryDialog();

    void showLocationDialog();

    void showManageCategoryDialog(String[] tabs);

    void showRequestPermission(int permissionCode);

    void setToolbarTitle(String text);
}
