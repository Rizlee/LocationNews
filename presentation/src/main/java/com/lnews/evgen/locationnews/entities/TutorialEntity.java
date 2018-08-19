package com.lnews.evgen.locationnews.entities;

public class TutorialEntity {
    private int titleResId;
    private int descriptionResId;
    private int imageResId;
    private int colorResId;

    public TutorialEntity(int titleResId, int descriptionResId, int imageResId, int colorResId) {
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
