package com.lnews.evgen.locationnews.di.modules;

import com.lnews.evgen.locationnews.utils.CategoryParser;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public interface CategoryParserModule {

    @Provides
    @Singleton
    static CategoryParser provideCategoryParser(){
        return new CategoryParser();
    }
}
