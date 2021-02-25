package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MySetFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MySetFragment extends Fragment {


    public MySetFragment() {
        // Required empty public constructor
    }

    public static MySetFragment newInstance() {
        MySetFragment fragment = new MySetFragment();

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
        return inflater.inflate(R.layout.fragment_my_set, container, false);
    }
}