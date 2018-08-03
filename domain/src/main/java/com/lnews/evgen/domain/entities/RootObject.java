package com.lnews.evgen.domain.entities;

import java.util.ArrayList;

public class RootObject {
    private String status;
    private int totalResults;
    private ArrayList<Article> articles;

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

    public ArrayList<Article> getArticles() {
        return this.articles;
    }

    public void setArticles(ArrayList<Article> articles) {
        this.articles = articles;
    }
}
