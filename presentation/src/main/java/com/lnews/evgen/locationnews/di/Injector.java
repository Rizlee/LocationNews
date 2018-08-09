package com.lnews.evgen.locationnews.di;

import android.app.Application;
import com.lnews.evgen.locationnews.di.components.AppComponent;
import com.lnews.evgen.locationnews.di.components.AuthComponent;
import com.lnews.evgen.locationnews.di.components.AuthenticationComponent;
import com.lnews.evgen.locationnews.di.components.DaggerAppComponent;
import com.lnews.evgen.locationnews.di.components.DescriptionComponent;
import com.lnews.evgen.locationnews.di.components.NewsListComponent;
import com.lnews.evgen.locationnews.di.components.NewsListTabComponent;
import com.lnews.evgen.locationnews.di.components.PassRecoveryComponent;
import com.lnews.evgen.locationnews.di.components.RegistrationComponent;
import com.lnews.evgen.locationnews.di.components.TutorialComponent;
import com.lnews.evgen.locationnews.di.modules.AppModule;

public class Injector {

    private static final Injector injector = new Injector();

    private AppComponent appComponent;
    private AuthenticationComponent authenticationComponent;
    private DescriptionComponent descriptionComponent;
    private NewsListComponent newsListComponent;
    private TutorialComponent tutorialComponent;
    private AuthComponent authComponent;
    private PassRecoveryComponent passRecoveryComponent;
    private RegistrationComponent registrationComponent;
    private NewsListTabComponent newsListTabComponent;

    public static Injector getInstance() {
        return injector;
    }

    private Injector() {
    }

    public AppComponent getAppComponent() {
        return appComponent;
    }

    public void initializeAppComponent(Application application) {
        appComponent = DaggerAppComponent.builder().appModule(new AppModule(application)).build();
    }

    public AuthenticationComponent plusAuthenticationComponent() {
        if (authenticationComponent == null) {
            authenticationComponent = appComponent.plusAuthComponent();
        }
        return authenticationComponent;
    }

    public void clearAuthenticationComponent() {
        authenticationComponent = null;
    }

    public TutorialComponent plusTutorialComponent() {
        if (tutorialComponent == null) {
            tutorialComponent = appComponent.plusTutorialComponent();
        }
        return tutorialComponent;
    }

    public void clearTutorialComponent() {
        tutorialComponent = null;
    }

    public PassRecoveryComponent plusPassRecoveryComponent() {
        if (passRecoveryComponent == null) {
            plusAuthenticationComponent();
            passRecoveryComponent = authenticationComponent.plusPassRecoveryComponent();
        }
        return passRecoveryComponent;
    }

    public void clearPassRecoveryComponent() {
        passRecoveryComponent = null;
    }

    public AuthComponent plusAuthComponent() {
        if (authComponent == null) {
            plusAuthenticationComponent();
            authComponent = authenticationComponent.plusAuthComponent();
        }
        return authComponent;
    }

    public void clearAuthComponent() {
        authComponent = null;
    }

    public RegistrationComponent plusRegistrationComponent() {
        if (registrationComponent == null) {
            plusAuthenticationComponent();
            registrationComponent = authenticationComponent.plusRegistrationComponent();
        }
        return registrationComponent;
    }

    public void clearRegistrationComponent() {
        registrationComponent = null;
    }

    public NewsListComponent plusNewsListComponent() {
        if (newsListComponent == null) {
            newsListComponent = appComponent.plusNewsListComponent();
        }
        return newsListComponent;
    }

    public void clearNewsListComponent() {
        newsListComponent = null;
    }

    public NewsListTabComponent plusNewsListTabComponent() {
        if (newsListTabComponent == null) {
            plusNewsListComponent();
            newsListTabComponent = newsListComponent.plusNewsListTabComponent();
        }
        return newsListTabComponent;
    }

    public void clearNewsListTabComponent() {
        newsListTabComponent = null;
    }

    public DescriptionComponent plusDescriptionComponent(){
        if (descriptionComponent == null){
            descriptionComponent = appComponent.plusDescriptionComponent();
        }
        return descriptionComponent;
    }

    public void clearDescriptionComponent(){
        descriptionComponent = null;
    }
}
