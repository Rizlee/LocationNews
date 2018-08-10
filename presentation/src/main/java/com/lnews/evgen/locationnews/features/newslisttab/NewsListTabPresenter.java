package com.lnews.evgen.locationnews.features.newslisttab;

import android.content.Intent;
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
import io.reactivex.observers.DisposableSingleObserver;
import javax.inject.Inject;

@InjectViewState
public class NewsListTabPresenter extends BasePresenter<NewsListTabView> {
    private final NewsInteractor newsInteractor;

    private NewsRecyclerAdapter newsRecyclerAdapter;
    private String title;
    private String countryCode;
    private RootObject mainObject;

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
        newsRecyclerAdapter = new NewsRecyclerAdapter(mainObject, item -> {
            Intent intent = new Intent(context, DescriptionActivity.class);
            intent.putExtra(NewsListTabFragment.TITLE_TAG, item.getTitle());
            intent.putExtra(NewsListTabFragment.DATE_TAG, item.getPublishedAt());
            intent.putExtra(NewsListTabFragment.DESCRIPTION_TAG, item.getDescription());
            intent.putExtra(NewsListTabFragment.IMAGE_TAG, item.getUrlToImage());
            getViewState().showActivity(intent);
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
            if (mainObject != null) {
                newsRecyclerAdapter.setItems(mainObject);
                return;
            }
        }

        getViewState().showProgressBar();
        newsInteractor.getNews(countryCode, title, "", new DisposableSingleObserver<RootObject>() {
            @Override
            public void onSuccess(RootObject rootObject) {
                getViewState().hideProgressBar();
                mainObject = rootObject;
                newsRecyclerAdapter.setItems(mainObject);
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
