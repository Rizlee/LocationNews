package com.lnews.evgen.locationnews.features.launch;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.google.firebase.firestore.DocumentSnapshot;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.interactors.LaunchInteractor;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationActivity;
import com.lnews.evgen.locationnews.features.newslist.NewsListActivity;
import com.lnews.evgen.locationnews.features.tutorial.TutorialActivity;
import com.lnews.evgen.locationnews.utils.CategoryParser;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

@InjectViewState
public class LaunchPresenter extends MvpPresenter<LaunchView> {
    public static final String CATEGORY_FIREBASE_TAG = "categoryName";

    private final LaunchInteractor launchInteractor;
    private final NewsInteractor newsInteractor;
    private final CategoryParser categoryParser;
    private final Context context;

    @Inject
    LaunchPresenter(LaunchInteractor launchInteractor, NewsInteractor newsInteractor,
        CategoryParser categoryParser, Context context) {
        this.launchInteractor = launchInteractor;
        this.context = context;
        this.newsInteractor = newsInteractor;
        this.categoryParser = categoryParser;
    }

    @Override
    public void attachView(LaunchView view) {
        super.attachView(view);
        showNextActivity();
    }

    @Override
    public void onDestroy() {
        launchInteractor.dispose();
        super.onDestroy();
    }

    private void showNextActivity() {
        if (launchInteractor.isTutorialNeed()) {
            getViewState().startNextActivity(TutorialActivity.getActivityIntent(context));
        } else if (launchInteractor.isAuth()) {
            syncDBCategories();
        } else {
            getViewState().startNextActivity(AuthenticationActivity.getActivityIntent(context));
        }
    }

    private void syncDBCategories() {
        newsInteractor.getCategoriesFirebase(new DisposableSingleObserver() {
            @Override
            public void onSuccess(Object o) {
                syncCategories(categoryParser.parseCategoriesFirebase(
                    Objects.requireNonNull(((DocumentSnapshot) o).get(CATEGORY_FIREBASE_TAG))
                        .toString()));
            }

            @Override
            public void onError(Throwable e) {
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }
        });
    }

    private void syncCategories(List<String> categories) {
        List<Category> bufCategories = new ArrayList<>();

        for (int i = 0; i < categories.size(); i++) {
            bufCategories.add(new Category(categories.get(i)));
        }

        newsInteractor.insertCategories(bufCategories, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }

            @Override
            public void onError(Throwable e) {
                getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
            }
        });
    }
}
