package com.lnews.evgen.locationnews.features.authorization;

import com.lnews.evgen.locationnews.features.base.BaseFragmentView;

interface AuthView extends BaseFragmentView {
    void sendEvent(int id);
    void onAuthSuccess();
}
