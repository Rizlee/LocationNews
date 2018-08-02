package com.lnews.evgen.locationnews.di.modules;

import android.content.Context;
import com.patloew.rxlocation.RxLocation;
import dagger.Module;
import dagger.Provides;
import javax.inject.Singleton;

@Module
public interface LocationModule {

    @Provides
    @Singleton
    static RxLocation provideRxLocation(Context context){
        return new RxLocation(context);
    }
}
