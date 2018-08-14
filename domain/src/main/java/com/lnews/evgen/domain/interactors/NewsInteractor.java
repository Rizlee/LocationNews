package com.lnews.evgen.domain.interactors;

import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.base.BaseInteractor;
import com.lnews.evgen.domain.usecases.CategoryUseCase;
import com.lnews.evgen.domain.usecases.InsertCategoriesUseCase;
import com.lnews.evgen.domain.usecases.InsertCategoryUseCase;
import com.lnews.evgen.domain.usecases.InsertDescriptionUseCase;
import com.lnews.evgen.domain.usecases.NewsUseCase;
import com.lnews.evgen.domain.usecases.RemoveCategoryUseCase;
import com.lnews.evgen.domain.usecases.RemoveDescriptionUseCase;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import javax.inject.Inject;

public class NewsInteractor extends BaseInteractor {
    private final NewsUseCase newsUseCase;
    private final CategoryUseCase categoryUseCase;
    private final InsertCategoriesUseCase insertCategoriesUseCase;
    private final RemoveCategoryUseCase removeCategoryUseCase;
    private final InsertCategoryUseCase insertCategoryUseCase;
    private final InsertDescriptionUseCase insertDescriptionUseCase;
    private final RemoveDescriptionUseCase removeDescriptionUseCase;

    @Inject
    NewsInteractor(NewsUseCase newsUseCase, CategoryUseCase categoryUseCase,
        InsertCategoriesUseCase insertCategoriesUseCase,
        RemoveCategoryUseCase removeCategoryUseCase, InsertCategoryUseCase insertCategoryUseCase,
        InsertDescriptionUseCase insertDescriptionUseCase,
        RemoveDescriptionUseCase removeDescriptionUseCase) {
        this.newsUseCase = newsUseCase;
        this.categoryUseCase = categoryUseCase;
        this.insertCategoriesUseCase = insertCategoriesUseCase;
        this.removeCategoryUseCase = removeCategoryUseCase;
        this.insertCategoryUseCase = insertCategoryUseCase;
        this.insertDescriptionUseCase = insertDescriptionUseCase;
        this.removeDescriptionUseCase = removeDescriptionUseCase;
    }

    public void getNews(String country, String category,
        DisposableSingleObserver<RootObject> observer) {
        execute(newsUseCase, new NewsUseCase.NewsData(country, category), observer);
    }

    public void getCategories(DisposableSingleObserver<List<Category>> observer) {
        execute(categoryUseCase, 1, observer); //TODO magic int :)
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

    public void insertDescription(String category, List<Article> articles, DisposableCompletableObserver observer){
        execute(insertDescriptionUseCase, new InsertDescriptionUseCase.DescriptionData(category, articles), observer);
    }

    public void removeDescription(String category, DisposableCompletableObserver observer){
        execute(removeDescriptionUseCase, category, observer);
    }
}
