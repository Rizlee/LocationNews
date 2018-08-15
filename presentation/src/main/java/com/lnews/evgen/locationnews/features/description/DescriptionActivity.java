package com.lnews.evgen.locationnews.features.description;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindString;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.bumptech.glide.load.resource.drawable.DrawableTransitionOptions;
import com.lnews.evgen.domain.entities.Article;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import com.lnews.evgen.locationnews.utils.GlideApp;
import java.util.Objects;
import javax.inject.Inject;
import javax.inject.Provider;

public class DescriptionActivity extends BaseActivity implements DescriptionView {

    @BindView(R.id.imageview_description)
    ImageView imageView;
    @BindView(R.id.textview_description)
    TextView textViewDescription;
    @BindView(R.id.textview_description_date)
    TextView textViewDate;
    @BindView(R.id.textview_description_title)
    TextView textViewTitle;
    @BindView(R.id.toolbar_description)
    Toolbar toolbar;

    @InjectPresenter
    DescriptionPresenter presenter;
    @Inject
    Provider<DescriptionPresenter> presenterProvider;

    @ProvidePresenter
    DescriptionPresenter providePresenter() {
        return presenterProvider.get();
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_description);

        setupToolbar();

        presenter.incomeDataEvent(getIntent().getStringExtra(NewsListTabFragment.TITLE_TAG),
            getIntent().getStringExtra(NewsListTabFragment.DATE_TAG),
            getIntent().getStringExtra(NewsListTabFragment.DESCRIPTION_TAG),
            getIntent().getStringExtra(NewsListTabFragment.IMAGE_TAG),
            getIntent().getStringExtra(NewsListTabFragment.TOOLBAR_TAG));
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusDescriptionComponent().inject(this);
    }

    @Override
    public void showNews(String title, String date, String description, String imageUrl,
        String category) {
        Objects.requireNonNull(getSupportActionBar()).setTitle(category);
        textViewTitle.setText(title);
        textViewDate.setText(date);
        textViewDescription.setText(description);
        GlideApp.with(this)
            .load(imageUrl)
            .placeholder(R.drawable.ic_watch)
            .transition(DrawableTransitionOptions.withCrossFade())
            .error(R.drawable.ic_broken_image)
            .into(imageView);
    }

    @Override
    public void onBackPressed() {
        presenter.onBackPressedEvent();
        super.onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        presenter.onBackPressedEvent();
        return true;
    }

    private void setupToolbar() {
        setSupportActionBar(toolbar);
        ActionBar actionbar = getSupportActionBar();
        if (actionbar != null) {
            actionbar.setDisplayHomeAsUpEnabled(true);
            actionbar.setDisplayShowHomeEnabled(true);
        }
    }

    public static Intent newIntent(Context context, Article item, String category) {
        Intent intent = new Intent(context, DescriptionActivity.class);
        intent.putExtra(NewsListTabFragment.TITLE_TAG, item.getTitle());
        intent.putExtra(NewsListTabFragment.DATE_TAG, item.getPublishedAt());
        intent.putExtra(NewsListTabFragment.DESCRIPTION_TAG, item.getDescription());
        intent.putExtra(NewsListTabFragment.IMAGE_TAG, item.getUrlToImage());
        intent.putExtra(NewsListTabFragment.TOOLBAR_TAG, category);
        return intent;
    }
}
