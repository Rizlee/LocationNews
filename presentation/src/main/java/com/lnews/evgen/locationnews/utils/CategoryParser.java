package com.lnews.evgen.locationnews.utils;

import java.util.ArrayList;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

@Singleton
public class CategoryParser {
    private static final String DELIMITER = "_";
    private static final int ONE_CHARACTER = 1;

    @Inject
    public CategoryParser() {
    }

    public List<String> parseCategoriesFirebase(String categoriesString) {
        List<String> bufCategories = new ArrayList<>();
        StringBuilder bufStringBuffer = new StringBuilder(categoriesString);
        while (bufStringBuffer.length() > 0) {
            bufCategories.add(bufStringBuffer.substring(0, bufStringBuffer.indexOf(DELIMITER)));
            bufStringBuffer.delete(0, bufStringBuffer.indexOf(DELIMITER) + ONE_CHARACTER);
        }
        return bufCategories;
    }
}
