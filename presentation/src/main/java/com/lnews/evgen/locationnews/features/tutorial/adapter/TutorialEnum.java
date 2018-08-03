package com.lnews.evgen.locationnews.features.tutorial.adapter;

import com.lnews.evgen.locationnews.R;

//todo исправить на коллекцию
public enum TutorialEnum {

    AUTH(R.string.all_auth_title,
        R.string.tutorial_auth_description,
        R.drawable.ic_auth,
        R.color.tutorial_screen_1),
    LOCATION(R.string.tutorial_location_title,
        R.string.tutorial_location_description,
        R.drawable.ic_my_location,
        R.color.tutorial_screen_2),
    NEWS(R.string.tutorial_news_title,
        R.string.tutorial_news_description,
        R.drawable.ic_description,
        R.color.tutorial_screen_3);

    private int titleResId;
    private int descriptionResId;
    private int imageResId;
    private int colorResId;

    TutorialEnum(int titleResId, int descriptionResId, int imageResId, int colorResId) {
        this.titleResId = titleResId;
        this.descriptionResId = descriptionResId;
        this.imageResId = imageResId;
        this.colorResId = colorResId;
    }

    public int getTitleResId() {
        return titleResId;
    }

    public int getDescriptionResId() {
        return descriptionResId;
    }

    public int getImageResId() {
        return imageResId;
    }

    public int getColorResId() {
        return colorResId;
    }
}