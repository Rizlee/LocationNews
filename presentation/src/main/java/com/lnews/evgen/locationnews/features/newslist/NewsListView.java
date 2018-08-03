package com.lnews.evgen.locationnews.features.newslist;

import com.arellomobile.mvp.viewstate.strategy.OneExecutionStateStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.lnews.evgen.locationnews.features.base.BaseView;

@StateStrategyType(OneExecutionStateStrategy.class)
public interface NewsListView extends BaseView {
    void showList();

    void rebuildTabView();

    void showAddCategoryDialog();

    void showLocationDialog();

    void showSearchResult();

    void changeTheme();

    void rebuildNavigationDrawer();

    void showHelpScreen();

    void changeLocation();

    void changeCategoryList();

    void showRequestPermission(int permissionCode);

    void setToolbarTitle(String text);
}
