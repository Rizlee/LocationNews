package com.lnews.evgen.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import com.lnews.evgen.domain.entities.UserEntity;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesStorage implements IPreferencesStorage {

    private static final String PREFERENCES_APP = "com.lnews.evgen.PREFERENCES_APP";

    private static final String PREFERENCE_TOKEN = "PREFERENCE_TOKEN";
    private static final String PREFERENCE_IS_FIRST_LAUNCH = "PREFERENCE_IS_FIRST_LAUNCH";
    private static final String PREFERENCE_USER_EMAIL = "PREFERENCE_USER_EMAIL";
    private static final String PREFERENCE_USER_PASSWORD = "PREFERENCE_USER_PASSWORD";

    private final SharedPreferences pref;

    @Inject
    PreferencesStorage(Context context) {
        pref = context.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE);
    }

    @Override
    public void setToken(String token) {
        pref.edit()
            .putString(PREFERENCE_TOKEN, token)
            .apply();
    }

    @Override
    public String getToken() {
        return pref
            .getString(PREFERENCE_TOKEN, null);
    }

    @Override
    public void setTutorialNeed(boolean isTutorialNeed) {
        pref.edit()
            .putBoolean(PREFERENCE_IS_FIRST_LAUNCH, isTutorialNeed)
            .apply();
    }

    @Override
    public boolean isTutorialNeed() {
        return pref.getBoolean(PREFERENCE_IS_FIRST_LAUNCH, true);
    }

    @Override public UserEntity getAuthUser() {
        return new UserEntity(pref.getString(PREFERENCE_USER_EMAIL,null), pref.getString(PREFERENCE_USER_PASSWORD, null));
    }

    @Override public void setAuthUser(UserEntity user) {
        pref.edit()
            .putString(PREFERENCE_USER_EMAIL, user.getEmail())
            .putString(PREFERENCE_USER_PASSWORD, user.getPassword())
            .apply();
    }
}