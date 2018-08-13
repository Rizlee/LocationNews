package com.lnews.evgen.locationnews.features.newslisttab;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import com.lnews.evgen.locationnews.features.description.DescriptionActivity;
import com.lnews.evgen.locationnews.features.newslisttab.adapter.NewsRecyclerAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.adapter.OnItemClickListener;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableCompletableObserver;
import io.reactivex.observers.DisposableSingleObserver;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Objects;
import javax.inject.Inject;

@InjectViewState
public class NewsListTabPresenter extends BasePresenter<NewsListTabView> {
    private final NewsInteractor newsInteractor;

    private NewsRecyclerAdapter newsRecyclerAdapter;
    private String title;
    private String countryCode;
    private List<Article> articles;

    private boolean connected = false;

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

    @SuppressLint("SimpleDateFormat")
    public void initRecyclerAdapter() {
        newsRecyclerAdapter = new NewsRecyclerAdapter(articles, item -> {
            Intent intent = DescriptionActivity.newIntent(context);
            intent.putExtra(NewsListTabFragment.TITLE_TAG, item.getTitle());
            intent.putExtra(NewsListTabFragment.DATE_TAG, item.getPublishedAt());
            intent.putExtra(NewsListTabFragment.DESCRIPTION_TAG, item.getDescription());
            intent.putExtra(NewsListTabFragment.IMAGE_TAG, item.getUrlToImage());
            getViewState().showActivity(intent);
        });
    }

    private boolean isOnline() {
        try {
            ConnectivityManager connectivityManager =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

            assert connectivityManager != null;
            NetworkInfo networkInfo = connectivityManager.getActiveNetworkInfo();
            connected = networkInfo != null && networkInfo.isAvailable() &&
                networkInfo.isConnected();
            return connected;

        } catch (Exception e) {
            Log.v("connectivity", e.toString());
        }
        return connected;
    }

    private void updateDB(){
        newsInteractor.removeDescription(title, new DisposableCompletableObserver() {
            @Override
            public void onComplete() {
                newsInteractor.insertDescription(title, articles, new DisposableCompletableObserver() {
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

        if (isOnline()) {
            getViewState().showProgressBar();
            newsInteractor.getNews(countryCode, title, "", new DisposableSingleObserver<RootObject>() {
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
        else {
            newsInteractor.getDescription(title, new DisposableSingleObserver<List<Article>>() {
                @Override
                public void onSuccess(List<Article> articles) {
                    newsRecyclerAdapter.setItems(articles);
                }

                @Override
                public void onError(Throwable e) {

                }
            });
        }
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }
}
