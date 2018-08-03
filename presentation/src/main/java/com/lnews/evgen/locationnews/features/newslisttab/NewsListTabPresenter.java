package com.lnews.evgen.locationnews.features.newslisttab;

import com.arellomobile.mvp.InjectViewState;
import com.lnews.evgen.domain.entities.RootObject;
import com.lnews.evgen.domain.interactors.NewsInteractor;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.di.annotations.PerFragment;
import com.lnews.evgen.locationnews.features.base.BasePresenter;
import io.reactivex.SingleObserver;
import io.reactivex.disposables.Disposable;
import javax.inject.Inject;

@InjectViewState
@PerFragment(NewsListTabFragment.class)
public class NewsListTabPresenter extends BasePresenter<NewsListTabView> {
    private final NewsInteractor newsInteractor;

    private String title;

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
        newsInteractor.dispose();
        clearComponent();
        super.onDestroy();
    }

    public void titleChanged(String title) {
        this.title = title;
    }

    public void updateList() {
        //newsInteractor.getNews("ru", title, "").doOnSuccess(getViewState()::showList);
        newsInteractor.getNews("ru", title, "").subscribe(new SingleObserver<RootObject>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(RootObject rootObject) {
                getViewState().showList(rootObject);
            }

            @Override
            public void onError(Throwable e) {
                getViewState().showToast(e.getMessage());
            }
        });
    }
}
