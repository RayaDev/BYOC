package com.rayadev.byoc.ui.main;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import com.rayadev.byoc.R;



//Returns the Fragment that determines the content and function of that specific tab.
public class PageAdapter extends FragmentStatePagerAdapter {

    int numOfTabs;

    public PageAdapter(@NonNull FragmentManager fm, int numOfTabs) {
        super(fm, numOfTabs);
        this.numOfTabs = numOfTabs;

    }

    // uses a switch case block to return the Fragment to show based on which tab is clicked
    @NonNull
    @Override
    public Fragment getItem(int position) {

        Log.i("TAG","pager position" + position);
        switch (position) {

            case 0: return ConverterTabFragment.newInstance();
            case 1: return HomeSetTabFragment.newInstance();
            case 2: return SetListTabFragment.newInstance();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

