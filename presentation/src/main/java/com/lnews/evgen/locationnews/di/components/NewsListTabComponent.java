package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.di.modules.NewsListTabModule;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import dagger.Subcomponent;

@PerFragment(NewsListTabFragment.class)
@Subcomponent(modules = NewsListTabModule.class)
public interface NewsListTabComponent {

    void inject(NewsListTabFragment fragment);

}
