package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

/*
Has a list of pre made converter sets that you can choose from
When you click the set, it opens up a new activity, which has the same converter box selection screen
as the HomeSet Tab.
    > This is the same Activity that is triggered by selecting a sub set from the HomeSetTab.
 */
public class SetListTabFragment extends Fragment {


    public SetListTabFragment() {
        // Required empty public constructor
    }

    public static SetListTabFragment newInstance() {
        SetListTabFragment fragment = new SetListTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_set_list_tab, container, false);
    }
}