package com.lnews.evgen.locationnews.features.launch;

import android.content.Context;
import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.lnews.evgen.domain.entities.Category;
import com.lnews.evgen.domain.interactors.LaunchInteractor;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.features.authentication.AuthenticationActivity;
import com.lnews.evgen.locationnews.features.newslist.NewsListActivity;
import com.lnews.evgen.locationnews.features.tutorial.TutorialActivity;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import javax.inject.Inject;

@InjectViewState
public class LaunchPresenter extends MvpPresenter<LaunchView> {

    private final LaunchInteractor launchInteractor;
    private final NewsInteractor newsInteractor;
    private final Context context;

    @Inject
    LaunchPresenter(LaunchInteractor launchInteractor, NewsInteractor newsInteractor, Context context) {
        this.launchInteractor = launchInteractor;
        this.context = context;
        this.newsInteractor = newsInteractor;
    }

    @Override
    public void attachView(LaunchView view) {
        super.attachView(view);
        syncDBCategories();
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
            getViewState().startNextActivity(NewsListActivity.getActivityIntent(context));
        } else {
            getViewState().startNextActivity(AuthenticationActivity.getActivityIntent(context));
        }
    }

    private void syncDBCategories(){
        newsInteractor.getCategories(new DisposableSingleObserver<List<Category>>() {
            @Override
            public void onSuccess(List<Category> categories) {
                if (categories.size() == 0){
                    initStartCategories();
                }
                showNextActivity();
            }

            @Override
            public void onError(Throwable e) {
                showNextActivity();
            }
        });
    }

    private void initStartCategories(){
        List<Category> bufCategories = new ArrayList<>();
        String[] bufArrayCategories = context.getResources().getStringArray(R.array.all_start_categories);

        for (String bufArrayCategory : bufArrayCategories) {
            bufCategories.add(new Category(bufArrayCategory));
        }
        newsInteractor.insertCategories(bufCategories, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
            }

            @Override
            public void onError(Throwable e) {
            }
        });
    }
}
