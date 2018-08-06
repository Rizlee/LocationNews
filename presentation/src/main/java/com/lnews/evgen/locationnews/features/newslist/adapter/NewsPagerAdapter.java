package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import java.util.ArrayList;
import java.util.List;

public class NewsPagerAdapter extends FragmentStatePagerAdapter {
    //private List<NewsListTabFragment> fragments;
    private List<String> titles;
    private String countryCode;

    public NewsPagerAdapter(FragmentManager fm, List<String> titles,  String countryCode) {
        super(fm);
      //  fragments = new ArrayList<>();
        this.countryCode = countryCode;
        this.titles = titles;
    }

    @Override
    public Fragment getItem(int position) {
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

//    public void addFragment(String title, String countryCode) {
//        fragments.add(NewsListTabFragment.newInstance(title,countryCode));
//        titles.add(title);
//    }
//
//    public void addFragment(ArrayList<String> title, String countryCode) {
//        for (int i = 0; i< title.size(); i++){
//            fragments.add(NewsListTabFragment.newInstance(title.get(i), countryCode));
//            titles.add(title.get(i));
//        }
//
//    }
}


