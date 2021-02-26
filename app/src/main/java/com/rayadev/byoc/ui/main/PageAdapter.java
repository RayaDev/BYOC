package com.rayadev.byoc.ui.main;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

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

        switch (position) {
            case 0: return new ConverterTabFragment();
            case 1: return new HomeSetTabFragment();
            case 2: return new SetListTabFragment();
            default: return null;
        }

    }

    @Override
    public int getCount() {
        return numOfTabs;
    }
}

