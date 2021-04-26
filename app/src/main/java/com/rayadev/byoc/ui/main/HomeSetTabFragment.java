package com.rayadev.byoc.ui.main;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;
import com.rayadev.byoc.room.ConverterViewModel;

import java.util.ArrayList;
import java.util.List;

/*
A list of converters (stored in CB's) the user has saved. Each one is a little square like converter bee.

Can drag and drop converter boxes (CB)'s into each other to create a new set. Like an Android folder
    > When you click the converter folder, it opens up a new activity with all the converters in that sub set.


 */
public class HomeSetTabFragment extends Fragment {

    private RecyclerView mRecyclerView;
    private HomeSetRecyclerViewAdapter mAdapter;
    private ConverterViewModel mConverterViewModel;


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

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.home_set_tab_recycler_view);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new HomeSetRecyclerViewAdapter(view.getContext());

        setUpConverterViewModel(mAdapter);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
    }

    private void setUpConverterViewModel(final HomeSetRecyclerViewAdapter adapter) {

        //The recyleView utilizes the database to populate itself.
        //But does the view model really need to be set up with an adapter? Seems silly doesnt look it
        //Can still be set up inside the homesetfragment, doesn't really matter, problably makes more sense to set up in MainActivity.

        //all the activity's interactions are with the ViewModel only.
        // When the activity is destroyed, the ViewModel still exists. It is not subject to LifeCycle methods.
        mConverterViewModel = new ViewModelProvider(this).get(ConverterViewModel.class); //Call ViewModel constructor directly

        //To display the current contents of the database, you add an observer that observes the LiveData in the ViewModel.
        //getAllWords passes the data to the MainActivity for display.
        mConverterViewModel.getAllConverters().observe(getViewLifecycleOwner(), new Observer<List<Converter>>() {
            @Override
            public void onChanged(List<Converter> converters) {
              adapter.setConverterArrayList((ArrayList<Converter>)converters);
            }
        });

    }

}
