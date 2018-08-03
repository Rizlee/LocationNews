package com.lnews.evgen.locationnews.features.tutorial.adapter;

import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import butterknife.BindView;
import butterknife.ButterKnife;
import com.lnews.evgen.locationnews.R;

public class TutorialPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;

    @BindView(R.id.textview_tutorial_title)
    TextView textViewTitle;
    @BindView(R.id.textview_tutorial_description)
    TextView textViewDescription;
    @BindView(R.id.imageview_tutorial_icon)
    ImageView imageViewIcon;

    public TutorialPagerAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
    }

    @NonNull
    @Override
    public Object instantiateItem(
        @NonNull
            ViewGroup container, int position) {
        TutorialEnum tutorialEnum = TutorialEnum.values()[position];
        ViewGroup view =
            (ViewGroup) layoutInflater.inflate(R.layout.tutorial_screen, container, false);

        ButterKnife.bind(this, view);

        textViewTitle.setText(tutorialEnum.getTitleResId());
        textViewDescription.setText(tutorialEnum.getDescriptionResId());
        imageViewIcon.setImageResource(tutorialEnum.getImageResId());
        view.setBackgroundResource(tutorialEnum.getColorResId());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return TutorialEnum.values().length;
    }

    @Override
    public boolean isViewFromObject(@NonNull
            View view,
        @NonNull
            Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(
        @NonNull
            ViewGroup collection, int position,
        @NonNull
            Object view) {
        collection.removeView((View) view);
    }
}
