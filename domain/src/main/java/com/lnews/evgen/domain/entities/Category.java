package com.lnews.evgen.domain.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

@Entity
public class Category {
    @PrimaryKey
    @NonNull
    public String categoryName;

    public Category(){}

    public Category(@NonNull String categoryName) {
        this.categoryName = categoryName;
    }
}

