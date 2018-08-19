package com.lnews.evgen.domain.entities;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.Ignore;
import android.arch.persistence.room.PrimaryKey;
import android.arch.persistence.room.Relation;
import android.support.annotation.NonNull;
import java.util.ArrayList;
import java.util.List;

@Entity
public class RootObject {
    @Ignore
    private String status;
    @Ignore
    private int totalResults;
    @PrimaryKey(autoGenerate = true)
    @NonNull
    public long id;

    private List<Article> articles;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getTotalResults() {
        return this.totalResults;
    }

    public void setTotalResults(int totalResults) {
        this.totalResults = totalResults;
    }

    public List<Article> getArticles() {
        return articles;
    }

    public void setArticles(List<Article> articles) {
        this.articles = articles;
    }
}
