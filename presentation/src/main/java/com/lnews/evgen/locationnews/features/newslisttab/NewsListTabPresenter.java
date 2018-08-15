package com.lnews.evgen.locationnews.features.newslisttab;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.description.DescriptionActivity;
import com.lnews.evgen.locationnews.features.newslisttab.adapter.NewsRecyclerAdapter;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.util.List;
import javax.inject.Inject;

@InjectViewState
public class NewsListTabPresenter extends BasePresenter<NewsListTabView> {
    private final NewsInteractor newsInteractor;

    private NewsRecyclerAdapter newsRecyclerAdapter;
    private String title;
    private String countryCode;
    private List<Article> articles;

    @Inject
    NewsListTabPresenter(NewsInteractor newsInteractor) {
        this.newsInteractor = newsInteractor;
    }

    @Override
    protected void clearComponent() {
        Injector.getInstance().clearNewsListTabComponent();
    }

    @Override
    public void onDestroy() {
        clearComponent();
        super.onDestroy();
    }

    public void initRecyclerAdapter() {
        newsRecyclerAdapter = new NewsRecyclerAdapter(articles,
            item -> getViewState().showActivity(DescriptionActivity.newIntent(context, item, title)));
    }

    private void updateDB() {
        newsInteractor.removeDescription(title, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                newsInteractor.insertDescription(title, articles,
                    new DisposableCompletableObserver() {
                        @Override
                        public void onComplete() {

                        }

                        @Override
                        public void onError(Throwable e) {

                        }
                    });
            }

            @Override
            public void onError(Throwable e) {

            }
        });
    }

    public NewsRecyclerAdapter getNewsRecyclerAdapter() {
        return newsRecyclerAdapter;
    }

    public void titleChanged(String title) {
        this.title = title;
    }

    public void updateList(boolean isRestoreState) {
        if (isRestoreState) {
            if (articles != null) {
                newsRecyclerAdapter.setItems(articles);
                return;
            }
        }

        getViewState().showProgressBar();
        newsInteractor.getNews(countryCode, title, new DisposableSingleObserver<RootObject>() {
            @Override
            public void onSuccess(RootObject rootObject) {
                getViewState().hideProgressBar();
                articles = rootObject.getArticles();
                newsRecyclerAdapter.setItems(articles);
                updateDB();
            }

            @Override
            public void onError(Throwable e) {
                getViewState().hideProgressBar();
                getViewState().showToast(e.getMessage());
            }
        });
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
