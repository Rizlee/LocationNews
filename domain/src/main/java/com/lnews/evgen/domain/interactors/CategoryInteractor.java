package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.CategoryFirebaseUseCase;
import com.lnews.evgen.domain.usecases.CategoryUseCase;
import com.lnews.evgen.domain.usecases.CountryCodeUseCase;
import com.lnews.evgen.domain.usecases.CountryUseCase;
import com.lnews.evgen.domain.usecases.GetCategoryFirebaseUseCase;
import com.lnews.evgen.domain.usecases.GetCountryCodeUseCase;
import com.lnews.evgen.domain.usecases.GetCountryUseCase;
import com.lnews.evgen.domain.usecases.InsertCategoriesUseCase;
import com.lnews.evgen.domain.usecases.InsertCategoryUseCase;
import com.lnews.evgen.domain.usecases.RemoveCategoryUseCase;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import javax.inject.Inject;

public class CategoryInteractor extends BaseInteractor {
    private final CategoryUseCase categoryUseCase;
    private final InsertCategoriesUseCase insertCategoriesUseCase;
    private final RemoveCategoryUseCase removeCategoryUseCase;
    private final InsertCategoryUseCase insertCategoryUseCase;
    private final CountryUseCase countryUseCase;
    private final GetCountryUseCase getCountryUseCase;
    private final CountryCodeUseCase countryCodeUseCase;
    private final GetCountryCodeUseCase getCountryCodeUseCase;
    private final CategoryFirebaseUseCase categoryFirebaseUseCase;
    private final GetCategoryFirebaseUseCase getCategoryFirebaseUseCase;

    @Inject
    public CategoryInteractor(CategoryUseCase categoryUseCase,
        InsertCategoriesUseCase insertCategoriesUseCase,
        RemoveCategoryUseCase removeCategoryUseCase, InsertCategoryUseCase insertCategoryUseCase,
        CountryUseCase countryUseCase, GetCountryUseCase getCountryUseCase,
        CountryCodeUseCase countryCodeUseCase, GetCountryCodeUseCase getCountryCodeUseCase,
        CategoryFirebaseUseCase categoryFirebaseUseCase,
        GetCategoryFirebaseUseCase getCategoryFirebaseUseCase) {
        this.categoryUseCase = categoryUseCase;
        this.insertCategoriesUseCase = insertCategoriesUseCase;
        this.removeCategoryUseCase = removeCategoryUseCase;
        this.insertCategoryUseCase = insertCategoryUseCase;
        this.countryUseCase = countryUseCase;
        this.getCountryUseCase = getCountryUseCase;
        this.countryCodeUseCase = countryCodeUseCase;
        this.getCountryCodeUseCase = getCountryCodeUseCase;
        this.categoryFirebaseUseCase = categoryFirebaseUseCase;
        this.getCategoryFirebaseUseCase = getCategoryFirebaseUseCase;
    }

    public void getCategories(DisposableSingleObserver<List<Category>> observer) {
        execute(categoryUseCase, 1, observer);
    }

    public void insertCategories(List<Category> categories,
        DisposableCompletableObserver observer) {
        execute(insertCategoriesUseCase, categories, observer);
    }

    public void removeCategory(Category category, DisposableCompletableObserver observer) {
        execute(removeCategoryUseCase, category, observer);
    }

    public void insertCategory(Category category, DisposableCompletableObserver observer) {
        execute(insertCategoryUseCase, category, observer);
    }

    public void saveCountry(String country) {
        countryUseCase.execute(country);
    }

    public String getCountry() {
        return getCountryUseCase.execute();
    }

    public void saveCountryCode(String countryCode) {
        countryCodeUseCase.execute(countryCode);
    }

    public String getCountryCode() {
        return getCountryCodeUseCase.execute();
    }

    public void saveCategoriesFirebase(List<String> categories) {
        categoryFirebaseUseCase.execute(categories);
    }

    public void getCategoriesFirebase(DisposableSingleObserver observer) {
        execute(getCategoryFirebaseUseCase, "", observer);
    }
}
