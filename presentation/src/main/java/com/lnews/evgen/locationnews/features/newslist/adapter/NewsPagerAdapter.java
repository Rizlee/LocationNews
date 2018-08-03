package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import java.util.ArrayList;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private ArrayList<NewsListTabFragment> fragments;
    private ArrayList<String> titles;

    public NewsPagerAdapter(FragmentManager fm, ArrayList<NewsListTabFragment> fragments,
        ArrayList<String> titles) {
        super(fm);
        this.fragments = fragments;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
        return fragments.get(position);
    }

    @Override
    public int getCount() {
        return fragments.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }

    public void addFragment(NewsListTabFragment fragment, String title) {
        fragments.add(fragment);
        titles.add(title);
    }
}


