package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MySetTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySetTabFragment extends Fragment {


    public MySetTabFragment() {
        // Required empty public constructor
    }

    public static MySetTabFragment newInstance() {
        MySetTabFragment fragment = new MySetTabFragment();

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