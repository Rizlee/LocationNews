package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentPagerAdapter {
    private List<NewsListTabFragment> fragments;
    private List<String> titles;

    public NewsPagerAdapter(FragmentManager fm) {
        super(fm);
        fragments = new ArrayList<>();
        titles = new ArrayList<>();
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

    public void addFragment(String title, String countryCode) {
        fragments.add(NewsListTabFragment.newInstance(title,countryCode));
        titles.add(title);
    }

    public void addFragment(ArrayList<String> title, String countryCode) {
        for (int i = 0; i< title.size(); i++){
            fragments.add(NewsListTabFragment.newInstance(title.get(i), countryCode));
            titles.add(title.get(i));
        }

    }
}


