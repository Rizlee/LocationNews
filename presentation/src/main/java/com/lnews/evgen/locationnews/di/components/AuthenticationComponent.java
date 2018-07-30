package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.*;
import com.lnews.evgen.locationnews.di.modules.AuthenticationModule;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationActivity;
import dagger.Subcomponent;

@PerActivity(AuthenticationActivity.class)
@Subcomponent(modules = AuthenticationModule.class)
public interface AuthenticationComponent {
    void inject(AuthenticationActivity activity);

    PassRecoveryComponent plusPassRecoveryComponent();

    AuthComponent plusAuthComponent();

    RegistrationComponent plusRegistrationComponent();
}
