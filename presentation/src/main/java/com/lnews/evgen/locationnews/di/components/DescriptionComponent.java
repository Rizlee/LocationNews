package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.*;
import com.lnews.evgen.locationnews.di.modules.DescriptionModule;
import com.lnews.evgen.locationnews.features.description.DescriptionActivity;
import dagger.Subcomponent;

@PerActivity(DescriptionActivity.class)
@Subcomponent(modules = DescriptionModule.class)
public interface DescriptionComponent {
    void inject(DescriptionActivity activity);
}
