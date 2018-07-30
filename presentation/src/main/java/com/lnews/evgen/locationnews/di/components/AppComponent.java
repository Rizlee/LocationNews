package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.modules.AppModule;
import com.lnews.evgen.locationnews.di.modules.RestModule;
import com.lnews.evgen.locationnews.features.launch.LaunchActivity;
import dagger.Component;
import javax.inject.Singleton;

@Singleton
@Component(modules = { AppModule.class, RestModule.class })
public interface AppComponent {

    void inject(LaunchActivity activity);

    AuthenticationComponent plusAuthComponent();

    TutorialComponent plusTutorialComponent();

    NewsListComponent plusNewsListComponent();
}
