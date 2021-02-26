package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

public class UnitScrollViewFragment extends Fragment {

    private int layoutID;

    public UnitScrollViewFragment() {
        // Required empty public constructor
    }

    public UnitScrollViewFragment(int layoutID) {
        this.layoutID = layoutID;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(layoutID, container, false);
    }
}