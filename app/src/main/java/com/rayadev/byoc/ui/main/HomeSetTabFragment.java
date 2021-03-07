package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

/*
A list of converters (stored in CB's) the user has saved. Each one is a little square like converter bee.

Can drag and drop converter boxes (CB)'s into each other to create a new set. Like an Android folder
    > When you click the converter folder, it opens up a new activity with all the converters in that sub set.


 */
public class HomeSetTabFragment extends Fragment {


    public HomeSetTabFragment() {
        // Required empty public constructor
    }

    public static HomeSetTabFragment newInstance() {
        HomeSetTabFragment fragment = new HomeSetTabFragment();

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
        return inflater.inflate(R.layout.fragment_my_set_tab, container, false);
    }
}