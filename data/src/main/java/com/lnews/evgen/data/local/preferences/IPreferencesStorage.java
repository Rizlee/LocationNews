package com.lnews.evgen.data.local.preferences;

import com.lnews.evgen.domain.entities.UserEntity;

public interface IPreferencesStorage {
    void setToken(String token);

    String getToken();

    void setTutorialNeed(boolean isTutorialNeed);

    boolean isTutorialNeed();

    UserEntity getAuthUser();

    void setAuthUser(UserEntity user);
}
