package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;

import java.util.ArrayList;

/*
A list of converters (stored in CB's) the user has saved. Each one is a little square like converter bee.

Can drag and drop converter boxes (CB)'s into each other to create a new set. Like an Android folder
    > When you click the converter folder, it opens up a new activity with all the converters in that sub set.


 */
public class HomeSetTabFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private HomeSetRecyclerViewAdapter mAdapter;


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

        View view  = inflater.inflate(R.layout.fragment_home_set_tab, container, false);
        setUpHomeSetRecyclerView(view);
        return view;
    }

    private void setUpHomeSetRecyclerView(View view) {
        //Test data for the view
        ArrayList<Converter> mConverterArrayList = new ArrayList<>();
        Converter mConverter = new Converter("KM","Miles",R.drawable.ic_baseline_distance_icon, 1,1);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);
        mConverterArrayList.add(mConverter);

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.home_set_tab_recycler_view);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new HomeSetRecyclerViewAdapter(view.getContext(), mConverterArrayList);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
    }

    }
