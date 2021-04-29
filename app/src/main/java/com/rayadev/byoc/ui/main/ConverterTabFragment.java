package com.rayadev.byoc.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;
import com.rayadev.byoc.room.ConverterViewModel;

import java.util.List;


//The main fragment that allows the user to run conversions, and set up a converter to be saved to the HomeSetTab
public class ConverterTabFragment extends Fragment {


    private int spinnerID;

    public ConverterTabFragment() {
        this.spinnerID = R.layout.spinner_scrollview_distance;

    }

    public static ConverterTabFragment newInstance() {
        ConverterTabFragment fragment = new ConverterTabFragment();

        return fragment;
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        savedInstanceState = null;
        //Ensures that we are creating an entirely fresh fragment.
        //savedInstanceState was causing bugs with the Spinner.
        //In the future can modify this for a better UX.

        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_converter_tab, container, false);

        buildSpinner(view);

        return view;


    }

    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


//        setSpinnerScrollViewFragment(spinnerID);


    }


    private void buildSpinner(View view) {

        String[] units = {getString(R.string.spinner_distance_title), getString(R.string.spinner_area_title), getString(R.string.spinner_time_title), getString(R.string.spinner_volume_title), getString(R.string.spinner_weight_title)};

        Spinner spinner = (Spinner) view.findViewById(R.id.spinner1);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this.getActivity(), android.R.layout.simple_spinner_item, units);
        adapter.setDropDownViewResource(android.R.layout.simple_dropdown_item_1line);
        spinner.setAdapter(adapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parentView, View view, int position, long id) {
                String selected = parentView.getItemAtPosition(position).toString();
                Context context = parentView.getContext();
                CharSequence text = selected;
                int duration = Toast.LENGTH_SHORT;

                if(text.equals(getString(R.string.spinner_distance_title))){
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    setSpinnerScrollViewFragment(R.layout.spinner_scrollview_distance);
                    setUpTargetConverter();

                }

                if(text.equals(getString(R.string.spinner_area_title))){
                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();
                    setSpinnerScrollViewFragment(R.layout.spinner_scrollview_area);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

    }

    //Replaces the ScrollViews for unit selection based on the spinner menu choice.
    private void setSpinnerScrollViewFragment(int layoutID) {
        SpinnerScrollViewFragment mFragment = new SpinnerScrollViewFragment(layoutID);

        FragmentTransaction ft = getChildFragmentManager().beginTransaction();
        // Replace the contents of the container with the new fragment
        ft.replace(R.id.spinner_frame_layout, mFragment);

        // Complete the changes added above
        ft.commit();

    }

    //Will come from the SpinnerInterface
    private String getConverterName(){
        return "KMMiles";
    }

    private void setUpTargetConverter() {

        //Creating multiple instances of this view model just to access the database seems not good..
        //But that's not really whats happening!.. Right?
        ConverterViewModel mConverterViewModel = new ViewModelProvider(this).get(ConverterViewModel.class);
        new getTargetConverterAsyncTask(mConverterViewModel).execute(getConverterName());

    }

    //Need methods to set the data inside the converter_master_cardview

    private static class getTargetConverterAsyncTask extends AsyncTask<String, Void, List<Converter>> {

        private ConverterViewModel mConverterViewModel;

        public getTargetConverterAsyncTask(ConverterViewModel converterViewModel){
            this.mConverterViewModel = converterViewModel;
        }
        @Override
        protected List<Converter> doInBackground(String... strings) {
            List<Converter> converters = mConverterViewModel.getTargetConverter(strings[0]);
            return converters;
        }

        @Override
        protected void onPostExecute(List<Converter> converters) {
            super.onPostExecute(converters);
            Log.i("TAG", converters.get(0).toString()+"Success!");
            //Update Converter UI here using LiveData and observer?
        }
    }
}