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
import com.lnews.evgen.locationnews.entities.TutorialEntity;
import java.util.ArrayList;
import java.util.List;

public class TutorialPagerAdapter extends PagerAdapter {
    private LayoutInflater layoutInflater;
    private ArrayList<TutorialEntity> tutorials;

    @BindView(R.id.textview_tutorial_title)
    TextView textViewTitle;
    @BindView(R.id.textview_tutorial_description)
    TextView textViewDescription;
    @BindView(R.id.imageview_tutorial_icon)
    ImageView imageViewIcon;

    public TutorialPagerAdapter(LayoutInflater layoutInflater) {
        this.layoutInflater = layoutInflater;
        initTutorials();
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        ViewGroup view =
            (ViewGroup) layoutInflater.inflate(R.layout.tutorial_screen, container, false);

        ButterKnife.bind(this, view);

        textViewTitle.setText(tutorials.get(position).getTitleResId());
        textViewDescription.setText(tutorials.get(position).getDescriptionResId());
        imageViewIcon.setImageResource(tutorials.get(position).getImageResId());
        view.setBackgroundResource(tutorials.get(position).getColorResId());

        container.addView(view);
        return view;
    }

    @Override
    public int getCount() {
        return tutorials.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object obj) {
        return view == obj;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup collection, int position, @NonNull Object view) {
        collection.removeView((View) view);
    }

    private void initTutorials() {
        tutorials = new ArrayList<>();
        tutorials.add(
            new TutorialEntity(R.string.all_auth_title, R.string.tutorial_auth_description,
                R.drawable.ic_auth, R.color.tutorial_screen_1));
        tutorials.add(
            new TutorialEntity(R.string.tutorial_location_title, R.string.tutorial_location_description,
                R.drawable.ic_my_location, R.color.tutorial_screen_2));
        tutorials.add(
            new TutorialEntity(R.string.tutorial_news_title, R.string.tutorial_news_description,
                R.drawable.ic_description, R.color.tutorial_screen_3));
    }
}