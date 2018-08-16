package com.lnews.evgen.data.local.preferences;

import android.content.Context;
import android.content.SharedPreferences;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class PreferencesStorage implements IPreferencesStorage {

    private static final String PREFERENCES_APP = "com.lnews.evgen.PREFERENCES_APP";

    private static final String PREFERENCE_TOKEN = "PREFERENCE_TOKEN";
    private static final String PREFERENCE_IS_FIRST_LAUNCH = "PREFERENCE_IS_FIRST_LAUNCH";
    private static final String PREFERENCE_COUNTRY_CODE = "countryCode";
    private static final String PREFERENCE_COUNTRY = "country";

    private final SharedPreferences pref;

    @Inject
    PreferencesStorage(Context context) {
        pref = context.getSharedPreferences(PREFERENCES_APP, Context.MODE_PRIVATE);
    }

    @Override
    public void setToken(String token) {
        pref.edit().putString(PREFERENCE_TOKEN, token).apply();
    }

    @Override
    public String getToken() {
        return pref.getString(PREFERENCE_TOKEN, "");
    }

    @Override
    public void setTutorialNeed(boolean isTutorialNeed) {
        pref.edit().putBoolean(PREFERENCE_IS_FIRST_LAUNCH, isTutorialNeed).apply();
    }

    @Override
    public boolean isTutorialNeed() {
        return pref.getBoolean(PREFERENCE_IS_FIRST_LAUNCH, true);
    }

    @Override
    public void resetToken() {
        pref.edit().putString(PREFERENCE_TOKEN, "").apply();
    }

    @Override
    public void setCountry(String country) {
        pref.edit().putString(PREFERENCE_COUNTRY, country).apply();
    }

    @Override
    public String getCountry() {
        return pref.getString(PREFERENCE_COUNTRY, "");
    }

    @Override
    public String getCountryCode() {
        return pref.getString(PREFERENCE_COUNTRY_CODE, "");
    }

    @Override
    public void setCountryCode(String countryCode) {
        pref.edit().putString(PREFERENCE_COUNTRY_CODE, countryCode).apply();
    }
}