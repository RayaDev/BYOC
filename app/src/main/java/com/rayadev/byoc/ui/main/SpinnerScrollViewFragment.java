package com.rayadev.byoc.ui.main;

import android.content.res.Resources;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.rayadev.byoc.MainActivity;
import com.rayadev.byoc.R;
import com.rayadev.byoc.room.ConverterRepository;
import com.rayadev.byoc.room.ConverterViewModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SpinnerScrollViewFragment extends Fragment {

    private final int layoutID;
    private UserConverterSelection mUserConverterSelection;
    private String converterUnitAName = "";
    private String converterUnitBName = "";



//    public SpinnerScrollViewFragment() {
//        // Required empty public constructor
//    }

    public SpinnerScrollViewFragment(int layoutID, UserConverterSelection userConverterSelection) {

        this.mUserConverterSelection = userConverterSelection;
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


        if(layoutID == R.layout.spinner_scrollview_distance) {

            int[] distanceScrollViewA_ViewIDs = new int[]{R.id.DA1, R.id.DA2, R.id.DA3, R.id.DA4,
                    R.id.DA5, R.id.DA6, R.id.DA7, R.id.DA8};

            int[] distanceScrollViewB_ViewIDs = new int[]{R.id.DB1, R.id.DB2, R.id.DB3, R.id.DB4,
                    R.id.DB5, R.id.DB6, R.id.DB7, R.id.DB8};

            linkSpinnerViews(view, distanceScrollViewA_ViewIDs, distanceScrollViewB_ViewIDs);

        }

//        if(layoutID == R.layout.spinner_scrollview_area) {
//            int areaScrollViewA_ViewIDs = int int[] {R.id.D}
//        }


    }

    private void linkSpinnerViews(View view, int[]unitAIDs, int[]unitBIDs) {

        final ArrayList<TextView> mTextViewAList = new ArrayList<>();
        final ArrayList<TextView> mTextViewBList = new ArrayList<>();

        int i = 0;
        while(i < unitAIDs.length) {
           TextView unitATextView = view.findViewById(unitAIDs[i]);
           TextView unitBTextView = view.findViewById(unitBIDs[i]);

           mTextViewAList.add(unitATextView);
           mTextViewBList.add(unitBTextView);
           i++;
        }

        buildSpinnerUI(view, mTextViewAList, mTextViewBList);

    }

    private void buildSpinnerUI(View view, ArrayList<TextView> mTextViewAList, ArrayList<TextView> mTextViewBList){


        Log.i("TAG", mTextViewAList.size()+"");
        for(final TextView textView : mTextViewAList) {
            Log.i("TAG", textView.toString());

            final int[] a = new int[1];
            textView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
//                    This is for removing the color of the other selected units.
                    for(TextView textView : mTextViewAList) {
                        textView.setBackgroundColor(view.getResources().getColor(R.color.colorPrimaryDark));
                    }

                    textView.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
//                    setConverterUnitA_ID(textView.getId());
//                    checkID(textView.getId());
//                    sendOverConverter();

                    converterUnitAName = textView.getText().toString();

                    Log.i("TAGS",textView.getText().toString());

                    sendConverterName();



                }


            });
        }

        for(final TextView textView : mTextViewBList) {
            textView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    for(TextView tv2 : mTextViewBList) {
                        tv2.setBackgroundColor(view.getResources().getColor(R.color.colorPrimary));
                    }

                    textView.setBackgroundColor(view.getResources().getColor(R.color.colorAccent));
//                    setConverterUnitB_ID(textView.getId());
//                    checkID(textView.getId());
//                    sendOverConverter();

                    converterUnitBName = textView.getText().toString();

                    Log.i("TAGS",textView.getText().toString());
                    sendConverterName();


                }

            });
        }

    }

    private void sendConverterName() {

        if(!converterUnitAName.equals("") && !converterUnitBName.equals("")) {
            String converterName = converterUnitAName + converterUnitBName;

            mUserConverterSelection.sendConverterName(converterName);
            mUserConverterSelection.setConverterBoxName(converterUnitAName, converterUnitBName);

            Log.i("TAGS", converterName);
            converterUnitAName ="";
            converterUnitBName="";
        }
    }

    public interface UserConverterSelection {

        void sendConverterName(String converterName);
        void setConverterBoxName(String converterUnitAName, String converterUnitBName);
    }



}