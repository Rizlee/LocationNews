package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.di.modules.PassRecoveryModule;
import com.lnews.evgen.locationnews.features.passrecovery.PassRecoveryFragment;
import dagger.Subcomponent;

@PerFragment(PassRecoveryFragment.class)
@Subcomponent(modules = PassRecoveryModule.class)
public interface PassRecoveryComponent {

    void inject(PassRecoveryFragment fragment);

}
