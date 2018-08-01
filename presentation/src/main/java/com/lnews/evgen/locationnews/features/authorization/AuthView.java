package com.lnews.evgen.locationnews.features.authorization;

import com.lnews.evgen.locationnews.features.base.BaseFragmentView;

interface AuthView extends BaseFragmentView {
    void showForgotPass();
    void showRegistration();
    void onAuthSuccess();
}
