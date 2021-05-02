package com.rayadev.byoc.ui.main;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelStoreOwner;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.rayadev.byoc.MainActivity;
import com.rayadev.byoc.R;
import com.rayadev.byoc.room.Converter;
import com.rayadev.byoc.room.ConverterViewModel;

import java.util.ArrayList;
import java.util.List;


//The main fragment that allows the user to run conversions, and set up a converter to be saved to the HomeSetTab
public class ConverterTabFragment extends Fragment {


    private int spinnerID;
    private TextView mUnitATitleTextView, mUnitBTitleTextView;
    private EditText mUnitAInputEditText, mUnitBInputEditText;
    private ImageButton mConverterInfoButton, mConverterSwapButton;

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

        linkViews(view);
        buildSpinner(view);

        return view;


    }

    // Any view setup should occur here.  E.g., view lookups and attaching view listeners.
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {


//        setSpinnerScrollViewFragment(spinnerID);


    }

    private void linkViews(View view) {

        //Link ConverterBox Views
        View myLayout = view.findViewById( R.id.converter_cardlayout_include_converter_tab ); // root View id from include

        mUnitATitleTextView = myLayout.findViewById(R.id.cardView_UnitATitle_TextView);
        mUnitBTitleTextView = myLayout.findViewById(R.id.cardView_UnitBTitle_TextView);

        mUnitAInputEditText = myLayout.findViewById(R.id.cardView_UnitAInput_EditText);
        mUnitBInputEditText = myLayout.findViewById(R.id.cardView_UnitBInput_EditText);

        mConverterInfoButton = myLayout.findViewById(R.id.cardView_InfoButton);
        mConverterSwapButton = myLayout.findViewById(R.id.cardView_SwapButton);


    }

    private void setConverterBoxData() {

        Toast.makeText(getContext(), "Thread Success", Toast.LENGTH_SHORT).show();
        mUnitATitleTextView.setText("Thread");
        mUnitBTitleTextView.setText("Success");

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
        mConverterViewModel.getTargetConverter(getConverterName()).observe(getViewLifecycleOwner(), new Observer<List<Converter>>() {
            @Override
            public void onChanged(List<Converter> converters) {
               setConverterBoxData();
            }
        });
    }

//    //Need methods to set the data inside the converter_master_cardview
//    //Use LiveData for the List<Converter> with an observer. See HomeSetTab
//    private static class getTargetConverterAsyncTask extends AsyncTask<String, Void, LiveData<List<Converter>>> {
//
//        private ConverterViewModel mConverterViewModel;
//
//        public getTargetConverterAsyncTask(ConverterViewModel converterViewModel){
//            this.mConverterViewModel = converterViewModel;
//        }
//        @Override
//        protected LiveData<List<Converter>> doInBackground(String... strings) {
//            LiveData<List<Converter>> converters = mConverterViewModel.getTargetConverter(strings[0]);
//            return converters;
//        }
//
//        @Override
//        protected void onPostExecute(LiveData<List<Converter>> converters) {
//            super.onPostExecute(converters);
//            Log.i("TAG", converters.getValue()+"success");
//
//        }
//    }
//
//    private class GetTargetConverterThread implements Runnable {
//
//        private String converterName;
//        private ConverterViewModel mConverterViewModel;
//
//        private GetTargetConverterThread(ConverterViewModel converterViewModel) {
//            this.mConverterViewModel = converterViewModel;
//        }
//
//        public GetTargetConverterThread(String converterName, ConverterViewModel converterViewModel) {
//            this.converterName = converterName;
//        }
//
//        @Override
//        public void run() {
//            mConverterViewModel.getTargetConverter(getConverterName()).observe(getViewLifecycleOwner(), new Observer<List<Converter>>() {
//                @Override
//                public void onChanged(List<Converter> converters) {
//                    //This is where you would update the Converter UI with the data.
//                    setConverterBoxData();
//                }
//            });
//        }
//
//    }
}