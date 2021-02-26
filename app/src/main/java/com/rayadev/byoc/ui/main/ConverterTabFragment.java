package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ConverterTabFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ConverterTabFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public ConverterTabFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     */

    //Fragment that is returned to the SectionsPagerAdapter
    public static ConverterTabFragment newInstance() {
        ConverterTabFragment fragment = new ConverterTabFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_converter_tab, container, false);

        return view;
    }

    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {

        buildSpinnerScrollViewFragment(R.layout.spinner_scrollview_distance);

    }


    //Replaces the ScrollViews for unit selection based on the spinner menu choice.
    private void buildSpinnerScrollViewFragment(int layoutID) {
        Fragment mFragment = new SpinnerScrollViewFragment(layoutID);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.test1, mFragment);

        // Complete the changes added above
        ft.commit();

    }


}