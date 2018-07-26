package com.lnews.evgen.domain.entities;

public class CategoryEntity {
    private String country;
    private String countryShort;
    private String categoryName;
    private String keyWord;

    public CategoryEntity(String country, String countryShort, String categoryName,
        String keyWord) {
        this.country = country;
        this.countryShort = countryShort;
        this.categoryName = categoryName;
        this.keyWord = keyWord;
    }

    public String getCountry() {
        return country;
    }

    public String getCountryShort() {
        return countryShort;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public String getKeyWord() {
        return keyWord;
    }
}
