package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.*;
import com.lnews.evgen.locationnews.di.modules.AuthModule;
import com.lnews.evgen.locationnews.features.auth.AuthActivity;
import dagger.Subcomponent;

@PerActivity(AuthActivity.class)
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent {
    void inject(AuthActivity activity);
}
