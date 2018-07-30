package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.di.modules.RegistrationModule;
import com.lnews.evgen.locationnews.features.registration.RegistrationFragment;
import dagger.Subcomponent;

@PerFragment(RegistrationFragment.class)
@Subcomponent(modules = RegistrationModule.class)
public interface RegistrationComponent {

    void inject(RegistrationFragment fragment);

}
