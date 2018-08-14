package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    private List<String> titles;
    private String countryCode;

    public NewsPagerAdapter(FragmentManager fm, List<String> titles, String countryCode) {
        super(fm);
        this.countryCode = countryCode;
        this.titles = titles;
    }

    @Override
    public NewsListTabFragment getItem(int position) {
        return NewsListTabFragment.newInstance(titles.get(position), countryCode);
    }

    @Override
    public int getCount() {
        return titles.size();
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return titles.get(position);
    }
}


