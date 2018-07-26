package com.lnews.evgen.locationnews.di;

import android.app.Application;
import com.lnews.evgen.locationnews.di.components.AppComponent;
import com.lnews.evgen.locationnews.di.components.AuthComponent;
import com.lnews.evgen.locationnews.di.components.DaggerAppComponent;
import com.lnews.evgen.locationnews.di.components.DescriptionComponent;
import com.lnews.evgen.locationnews.di.components.NewsListComponent;
import com.lnews.evgen.locationnews.di.modules.AppModule;

public class Injector {

    private static final Injector injector = new Injector();

    private AppComponent appComponent;
    private AuthComponent authComponent;
    private DescriptionComponent descriptionComponent;
    private NewsListComponent newsListComponent;

    public static Injector getInstance() {
        return injector;
    }

    private Injector() {}

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void initializeAppComponent(Application application) {
        appComponent = DaggerAppComponent.builder()
            .appModule(new AppModule(application))
            .build();
    }

    public AuthComponent plusAuthComponent(){
        if (authComponent == null) {
            authComponent = appComponent.plusAuthComponent();
        }
        return authComponent;
    }

}
