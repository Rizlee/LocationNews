package com.lnews.evgen.data.local;

import com.lnews.evgen.data.local.preferences.IPreferencesStorage;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class Cache {

    private final IPreferencesStorage preferencesStorage;

    @Inject
    Cache(IPreferencesStorage preferencesStorage) {
        this.preferencesStorage = preferencesStorage;
    }

    public String getToken(){
        return preferencesStorage.getToken();
    }

    public void setToken(String token){
        preferencesStorage.setToken(token);
    }

    public void setTutorialNeed(boolean isTutorialNeed){
        preferencesStorage.setTutorialNeed(isTutorialNeed);
    }

    public boolean isTutorialNeed(){
        return preferencesStorage.isTutorialNeed();
    }

    public void setCountry(String country){
        preferencesStorage.setCountry(country);
    }

    public String getCountry(){
        return preferencesStorage.getCountry();
    }

    public String getCountryCode(){
        return preferencesStorage.getCountryCode();
    }

    public void setCountryCode(String countryCode){
        preferencesStorage.setCountryCode(countryCode);
    }

    public void clearPreferences(){
        preferencesStorage.clearPreferences();
    }
}
