package com.lnews.evgen.locationnews.features.newslist;

public interface OnLocationDialogResult {
    void countrySelectEvent(String country, String countryCode);
    void currentLocationEvent();
}
