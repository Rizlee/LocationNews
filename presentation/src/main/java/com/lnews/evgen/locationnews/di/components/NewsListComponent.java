package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.*;
import com.lnews.evgen.locationnews.di.modules.NewsListModule;
import com.lnews.evgen.locationnews.features.newslist.NewsListActivity;
import dagger.Subcomponent;

@PerActivity(NewsListActivity.class)
@Subcomponent(modules = NewsListModule.class)
public interface NewsListComponent {
    void inject(NewsListActivity activity);

    NewsListTabComponent plusNewsListTabComponent();
}
