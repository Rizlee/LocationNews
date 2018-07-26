package com.lnews.evgen.domain.entities;

public class NewsEntity {
    private String imageUrl;
    private String title;
    private String sourceName;
    private String author;
    private String dateTime;
    private String description;
    private String url;
    private String category;

    public NewsEntity(String imageUrl, String title, String sourceName, String author,
        String dateTime, String description, String url, String category) {
        this.imageUrl = imageUrl;
        this.title = title;
        this.sourceName = sourceName;
        this.author = author;
        this.dateTime = dateTime;
        this.description = description;
        this.url = url;
        this.category = category;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getSourceName() {
        return sourceName;
    }

    public String getAuthor() {
        return author;
    }

    public String getDateTime() {
        return dateTime;
    }

    public String getDescription() {
        return description;
    }

    public String getUrl() {
        return url;
    }

    public String getCategory() {
        return category;
    }
}
