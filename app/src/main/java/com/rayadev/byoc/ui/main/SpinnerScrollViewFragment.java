package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
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
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        return inflater.inflate(layoutID, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {

        //Handle all view interactions here.

        //Handle user selection of units, based on layout ID
        createScrollViewLogic();

    }

    private void createScrollViewLogic() {


        //Get the set of unit View IDs based on the layout being used.
        //Declare all of this in an arrays resource file.
        int[] unitAIDs;
        int[] unitBIDs;

        switch (layoutID) {
            case R.layout.spinner_scrollview_distance:
                unitAIDs = getResources().getIntArray(R.array.distance_scrollview_unitA_ID);
                unitBIDs = getResources().getIntArray(R.array.distance_scrollview_unitB_ID);
                break;

            case R.layout.spinner_scrollview_area:


        }

    }

}