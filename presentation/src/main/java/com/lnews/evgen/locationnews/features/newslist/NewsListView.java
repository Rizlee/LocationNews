package com.lnews.evgen.locationnews.features.newslist;

import android.support.v4.app.FragmentManager;
import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseActivityView;
import com.lnews.evgen.locationnews.features.base.BaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface NewsListView extends BaseActivityView {
    void showAddCategoryDialog();

    void showLocationDialog();

    void changeCategoryList();

    void showRequestPermission(int permissionCode);

    void setToolbarTitle(String text);
}
