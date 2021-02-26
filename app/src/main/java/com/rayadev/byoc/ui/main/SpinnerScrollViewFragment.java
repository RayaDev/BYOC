package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.rayadev.byoc.R;

public class SpinnerScrollViewFragment extends Fragment {

    private int layoutID;

//    public SpinnerScrollViewFragment() {
//        // Required empty public constructor
//    }

    public SpinnerScrollViewFragment(int layoutID) {
        this.layoutID = layoutID;

    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

//        Log.i("TAG", "Spinner onCreateView");
//        // Inflate the layout for this fragment
//        if(layoutID == 0) {
//            Log.i("TAG", "Spinner LayoutBug");
//            layoutID = R.layout.spinner_scrollview_distance;
//        }

        return inflater.inflate(layoutID, container, false);
    }
}