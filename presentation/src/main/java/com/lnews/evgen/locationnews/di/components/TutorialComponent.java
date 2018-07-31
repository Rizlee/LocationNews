package com.lnews.evgen.locationnews.di.components;

import com.lnews.evgen.locationnews.di.annotations.PerActivity;
import com.lnews.evgen.locationnews.di.modules.TutorialModule;
import com.lnews.evgen.locationnews.features.tutorial.TutorialActivity;
import dagger.Subcomponent;

@PerActivity(TutorialActivity.class)
@Subcomponent(modules = TutorialModule.class)
public interface TutorialComponent {
    void inject(TutorialActivity actvity);
}
