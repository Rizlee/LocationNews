package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.di.modules.AuthModule;
import com.lnews.evgen.locationnews.features.authorization.AuthFragment;
import dagger.Subcomponent;

@PerFragment(AuthFragment.class)
@Subcomponent(modules = AuthModule.class)
public interface AuthComponent {

    void inject(AuthFragment fragment);

}
