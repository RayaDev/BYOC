package com.rayadev.byoc.ui.main;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rayadev.byoc.R;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

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
        createScrollViewLogic(view);

    }

    private void createScrollViewLogic(View view) {


        //Get the set of unit View IDs based on the layout being used.
        //Declare all of this in an arrays resource file.
        ArrayList<Integer> unitAIDs = new ArrayList<>();
        ArrayList<Integer> unitBIDs = new ArrayList<>();

        switch (layoutID) {
            case R.layout.spinner_scrollview_distance:
                int[] arrayA = getResources().getIntArray(R.array.distance_scrollview_unitA_ID);
                int[] arrayB = getResources().getIntArray(R.array.distance_scrollview_unitB_ID);
                unitAIDs= (ArrayList<Integer>) Arrays.stream(arrayA).boxed().collect(Collectors.toList());
                unitBIDs= (ArrayList<Integer>) Arrays.stream(arrayB).boxed().collect(Collectors.toList());

                break;

            case R.layout.spinner_scrollview_area:
                break;

            default:
                arrayA = getResources().getIntArray(R.array.distance_scrollview_unitA_ID);
                arrayB = getResources().getIntArray(R.array.distance_scrollview_unitB_ID);
                unitAIDs= (ArrayList<Integer>) Arrays.stream(arrayA).boxed().collect(Collectors.toList());
                unitBIDs= (ArrayList<Integer>) Arrays.stream(arrayB).boxed().collect(Collectors.toList());
                break;

        }

        //Link Views in spinner and enable UI
        linkSpinnerViews(view, unitAIDs, unitBIDs);

    }

    private void linkSpinnerViews(View view, ArrayList<Integer> unitAIDs, ArrayList<Integer> unitBIDs) {

        final ArrayList<TextView> mTextViewAList = new ArrayList<>();
        final ArrayList<TextView> mTextViewBList = new ArrayList<>();

        int i = 0;
        while(i < unitAIDs.size()) {
           TextView unitATextView = view.findViewById(unitAIDs.get(i));
           TextView unitBTextView = view.findViewById(unitBIDs.get(i));
           mTextViewAList.add(unitATextView);
           mTextViewBList.add(unitBTextView);
           i++;

        }

        for(final TextView textView : mTextViewAList) {

            final int[] a = new int[1];
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    for(TextView textView : mTextViewAList) {
//                        textView.setBackgroundColor(view.getResources().getColor(R.color.colorPrimaryDark));
//                    }

                    textView.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
//                    setConverterUnitA_ID(textView.getId());
//                    checkID(textView.getId());
//                    sendOverConverter();


                }

            });
        }

        for(final TextView textView : mTextViewBList) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
//                    for(TextView tv2 : mTextViewBList) {
//                        tv2.setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
//                    }

                    textView.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
//                    setConverterUnitB_ID(textView.getId());
//                    checkID(textView.getId());
//                    sendOverConverter();


                }

            });
        }

    }

}