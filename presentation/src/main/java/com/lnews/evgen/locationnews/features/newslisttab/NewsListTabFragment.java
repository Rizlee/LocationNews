package com.lnews.evgen.locationnews.features.newslisttab;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseFragment;
import javax.inject.Inject;
import javax.inject.Provider;

public class NewsListTabFragment extends BaseFragment implements NewsListTabView {
    public static final String TITLE_TAG = "title";
    private static final String COUNTRY_CODE_TAG = "country_code";
    private static final String DEFAULT_COUNTRY_CODE = "";
    public static final String IMAGE_TAG = "image";
    public static final String DESCRIPTION_TAG = "description";
    public static final String DATE_TAG = "date";
    public static final String TOOLBAR_TAG = "toolbar";

    @BindView(R.id.recyclerview_newslist_category)
    RecyclerView recyclerView;
    @BindView(R.id.progressbar_newslist_category)
    ProgressBar progressBar;

    @InjectPresenter
    NewsListTabPresenter presenter;
    @Inject
    Provider<NewsListTabPresenter> presenterProvider;

    @ProvidePresenter
    NewsListTabPresenter providePresenter() {
        return presenterProvider.get();
    }

    public NewsListTabFragment() {

    }

    public static NewsListTabFragment newInstance(String title, String countryCode) {
        NewsListTabFragment fragment = new NewsListTabFragment();

        Bundle args = new Bundle();
        args.putString(TITLE_TAG, title);
        args.putString(COUNTRY_CODE_TAG, countryCode);
        fragment.setArguments(args);

        return fragment;
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
        Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_newslist_tab, container, false);

        getArg();

        //todo в базовом классе уже есть
        ButterKnife.bind(this, view);

        setupRecyclerView();

        presenter.updateList(true);

        return view;
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusNewsListTabComponent().inject(this);
    }

    private void setupRecyclerView() {
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        presenter.initRecyclerAdapter();
        recyclerView.setAdapter(presenter.getNewsRecyclerAdapter());
    }

    private void getArg(){
        if (getArguments() != null) {
            if (getArguments().containsKey(TITLE_TAG)) {
                presenter.titleChanged(getArguments().getString(TITLE_TAG));
            }
            if (getArguments().containsKey(COUNTRY_CODE_TAG)) {
                presenter.setCountryCode(
                    getArguments().getString(COUNTRY_CODE_TAG, DEFAULT_COUNTRY_CODE));
            }
        }
    }

    public void setCountryCode(String countryCode){
        presenter.setCountryCode(countryCode);
        presenter.updateList(false);
    }

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.INVISIBLE);
    }
}
