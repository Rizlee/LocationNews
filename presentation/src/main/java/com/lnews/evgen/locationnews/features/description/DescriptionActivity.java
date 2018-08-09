package com.lnews.evgen.locationnews.features.description;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import com.lnews.evgen.locationnews.utils.GlideApp;
import javax.inject.Inject;
import javax.inject.Provider;

public class DescriptionActivity extends BaseActivity implements DescriptionView{

    @BindView(R.id.imageview_description)
    ImageView imageView;
    @BindView(R.id.textview_description)
    TextView textViewDescription;
    @BindView(R.id.textview_description_date)
    TextView textViewDate;
    @BindView(R.id.textview_description_title)
    TextView textViewTitle;

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
    }

    @Override protected void injectComponent() {
        Injector.getInstance().plusDescriptionComponent().inject(this);
    }

    @Override public void showNews(String title, String date, String description, String imageUrl) {
        textViewTitle.setText(title);
        textViewDate.setText(date);
        textViewDescription.setText(description);
        GlideApp.with(this).load(imageUrl).error(R.drawable.ic_broken_image).into(imageView);
    }
}
