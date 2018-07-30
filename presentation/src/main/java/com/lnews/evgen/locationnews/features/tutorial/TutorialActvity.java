package com.lnews.evgen.locationnews.features.tutorial;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import butterknife.BindArray;
import butterknife.BindString;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.arellomobile.mvp.presenter.ProvidePresenter;
import com.lnews.evgen.locationnews.R;
import com.lnews.evgen.locationnews.di.Injector;
import com.lnews.evgen.locationnews.features.base.BaseActivity;
import com.lnews.evgen.locationnews.features.tutorial.adapter.TutorialPagerAdapter;
import io.reactivex.Observable;
import javax.inject.Inject;
import javax.inject.Provider;

public class TutorialActvity extends BaseActivity implements TutorialView {
    private static final int FIRST_PAGE = 0;
    private static final int ONE_PAGE = 1;
    private static final int[] LAYOUTS = {
        R.layout.tutorial_screen_1,
        R.layout.tutorial_screen_2,
        R.layout.tutorial_screen_3};

    //TODO
    private TextView[] dots;
    private TutorialPagerAdapter tutorialPagerAdapter;

    @BindView(R.id.button_next)
    Button buttonNext;
    @BindView(R.id.button_skip)
    Button buttonSkip;
    @BindView(R.id.view_pager_tutorial)
    ViewPager viewPagerTutorial;
    @BindView(R.id.linearlayout_dots)
    LinearLayout linearLayoutDots;

    @BindString(R.string.start_btn)
    String startButtonDescription;
    @BindString(R.string.next_btn)
    String nextButtonDescription;

    @OnClick(R.id.button_skip)
    public void buttonSkipListener(){
        tutorialPresenter.buttonSkipPressed();
    }

    @OnClick(R.id.button_next)
    public void buttonNextListener(){
        tutorialPresenter.buttonNextPressed(getNextPage(), LAYOUTS.length);
    }

    @InjectPresenter
    TutorialPresenter tutorialPresenter;
    @Inject
    Provider<TutorialPresenter> presenterProvider;

    @ProvidePresenter
    TutorialPresenter providePresenter() {
        return presenterProvider.get();
    }

    public static Intent getActivityIntent(Context context) {
        return new Intent(context, TutorialActvity.class);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);

        addBottomDots(FIRST_PAGE);

        tutorialPagerAdapter = new TutorialPagerAdapter((LayoutInflater)getSystemService(Context.LAYOUT_INFLATER_SERVICE), LAYOUTS);
        viewPagerTutorial.setAdapter(tutorialPagerAdapter);
        viewPagerTutorial.addOnPageChangeListener(simpleOnPageChangeListener);

        changeStatusBarColor();
    }

    @Override
    protected void injectComponent() {
        Injector.getInstance().plusTutorialComponent().inject(this);
    }

    private int getNextPage() {
        return viewPagerTutorial.getCurrentItem() + ONE_PAGE;
    }

    @Override
    public void showItem(int id) {
        viewPagerTutorial.setCurrentItem(id);
    }

//TODO
    private void addBottomDots(int currentPage) {
        dots = new TextView[LAYOUTS.length];

        int[] colorsActive = getResources().getIntArray(R.array.array_dot_active);
        int[] colorsInactive = getResources().getIntArray(R.array.array_dot_inactive);

        linearLayoutDots.removeAllViews();
        for (int i = 0; i < dots.length; i++) {
            dots[i] = new TextView(this);
            dots[i].setText(Html.fromHtml("&#8226;"));
            dots[i].setTextSize(35);
            dots[i].setTextColor(colorsInactive[currentPage]);
            linearLayoutDots.addView(dots[i]);
        }

        if (dots.length > 0)
            dots[currentPage].setTextColor(colorsActive[currentPage]);
    }

    private void changeStatusBarColor() {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            Window window = getWindow();
            window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
            window.setStatusBarColor(Color.TRANSPARENT);
        }
    }

    ViewPager.SimpleOnPageChangeListener  simpleOnPageChangeListener = new ViewPager.SimpleOnPageChangeListener(){
        @Override
        public void onPageSelected(int position) {
            super.onPageSelected(position);

            addBottomDots(position);

            if (position == LAYOUTS.length - 1) {
                buttonNext.setText(startButtonDescription);
                buttonSkip.setVisibility(View.GONE);
            } else {
                buttonNext.setText(nextButtonDescription);
                buttonSkip.setVisibility(View.VISIBLE);
            }
        }
    };
}
