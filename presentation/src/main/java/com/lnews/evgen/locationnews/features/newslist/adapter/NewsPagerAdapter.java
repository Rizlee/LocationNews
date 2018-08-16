package com.lnews.evgen.locationnews.features.newslist.adapter;

import android.support.annotation.Nullable;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import com.lnews.evgen.locationnews.features.newslist.adapter.base.BaseStateAdapter;
import com.lnews.evgen.locationnews.features.newslisttab.NewsListTabFragment;
import com.lnews.evgen.locationnews.features.newslisttab.UpdateableFragment;
import java.util.List;

public class NewsPagerAdapter extends BaseStateAdapter {
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

    @Override
    public int getItemPosition(Object object) {
        if (object instanceof UpdateableFragment) {
            ((UpdateableFragment) object).updateCountryCode(countryCode);
        }
        return super.getItemPosition(object);
    }

    @Override
    public void clearState() {
        super.clearState();
    }

    public void updateCountryCode(String countryCode){
        this.countryCode = countryCode;
        notifyDataSetChanged();
    }
}


