package com.lnews.evgen.data.local.preferences;

public interface IPreferencesStorage {
    void setToken(String token);

    String getToken();

    void setTutorialNeed(boolean isTutorialNeed);

    boolean isTutorialNeed();
}
