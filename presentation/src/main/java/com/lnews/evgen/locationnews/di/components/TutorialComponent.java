package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.di.modules.TutorialModule;
import com.lnews.evgen.locationnews.features.tutorial.TutorialActvity;
import dagger.Subcomponent;

@PerActivity(TutorialActvity.class)
@Subcomponent(modules = TutorialModule.class)
public interface TutorialComponent {
    void inject(TutorialActvity actvity);
}
