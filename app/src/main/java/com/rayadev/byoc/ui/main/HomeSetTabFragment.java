package com.rayadev.byoc.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
import androidx.annotation.Keep;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

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
public class HomeSetTabFragment extends Fragment implements HomeSetRecyclerViewAdapter.ConverterClickListener {

    private RecyclerView mRecyclerView;
    private HomeSetRecyclerViewAdapter mAdapter;
    private ConverterViewModel mConverterViewModel;
    private View mConverterUI;


    //Views for the Converter UI
    private TextView mUnitATitleTextView, mUnitBTitleTextView;
    private EditText mUnitAInputEditText, mUnitBInputEditText;
    private ImageButton mConverterInfoButton, mConverterSwapButton;


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

        View view = inflater.inflate(R.layout.fragment_home_set_tab, container, false);

        setUpHomeSetRecyclerView(view);
        linkViews(view);

        return view;
    }

    private void linkViews(View view) {
        //Link ConverterBox Views
        mConverterUI = view.findViewById(R.id.converter_cardlayout_include_home_tab); // root View id from include

        mUnitATitleTextView = mConverterUI.findViewById(R.id.cardView_UnitATitle_TextView);
        mUnitBTitleTextView = mConverterUI.findViewById(R.id.cardView_UnitBTitle_TextView);

        mUnitAInputEditText = mConverterUI.findViewById(R.id.cardView_UnitAInput_EditText);
        mUnitBInputEditText = mConverterUI.findViewById(R.id.cardView_UnitBInput_EditText);

        mConverterInfoButton = mConverterUI.findViewById(R.id.cardView_InfoButton);
        mConverterSwapButton = mConverterUI.findViewById(R.id.cardView_SwapButton);

        mConverterUI.setVisibility(View.GONE);
    }

    private void setUpHomeSetRecyclerView(View view) {

        // Get a handle to the RecyclerView.
        mRecyclerView = view.findViewById(R.id.home_set_tab_recycler_view);

        // Create an adapter and supply the data to be displayed.
        mAdapter = new HomeSetRecyclerViewAdapter(view.getContext(), this);

        setUpConverterViewModel(mAdapter);

        // Connect the adapter with the RecyclerView.
        mRecyclerView.setAdapter(mAdapter);

        // Give the RecyclerView a default layout manager.
        mRecyclerView.setLayoutManager(new GridLayoutManager(view.getContext(), 3));
    }

    private void setUpConverterViewModel(final HomeSetRecyclerViewAdapter adapter) {


        //all the activity's interactions are with the ViewModel only.
        // When the activity is destroyed, the ViewModel still exists. It is not subject to LifeCycle methods.
        mConverterViewModel = new ViewModelProvider(this).get(ConverterViewModel.class); //Call ViewModel constructor directly

        //To display the current contents of the database, you add an observer that observes the LiveData in the ViewModel.
        mConverterViewModel.getAllConverters().observe(getViewLifecycleOwner(), new Observer<List<Converter>>() {
            @Override
            public void onChanged(List<Converter> converters) {
                adapter.setConverterArrayList((ArrayList<Converter>) converters);
                //Clear it does need this.. or else it won't update the HomeSet.
            }
        });

    }

    //Sets the Converter data into the fragment Converter UI.
    @Override
    public void onConverterIconClick(String unitAName, String unitBName, double unitAValue, double unitBValue) {

        String unitString = unitAName + ": " + unitAValue + " " + unitBName + ": " + unitBValue;


        Toast.makeText(getContext(), unitString, Toast.LENGTH_SHORT).show();
        Log.i("TAG", "HomeSetTabFragClick");


        setConverterBoxTitles(unitAName, unitBName);
        setConverterBoxLogic(unitAValue, unitBValue);
        keyboardManager();

        //Custom back button function.
        OnBackPressedCallback backPressedCallback = new OnBackPressedCallback(true) {
            @Override
            public void handleOnBackPressed() {
                mConverterUI.setVisibility(View.GONE);
                clearUserInput();

            }
        };

        requireActivity().getOnBackPressedDispatcher().addCallback(this, backPressedCallback);
        //Keyboard

    }

    private void setConverterBoxTitles(String unitAText, String unitBText) {

//        Toast.makeText(getContext(), "Thread Success", Toast.LENGTH_SHORT).show();
        mUnitATitleTextView.setText(unitAText);
        mUnitBTitleTextView.setText(unitBText);

    }

    private void setConverterBoxLogic(double unitAValue, double unitBValue) {

        final MyTextWatcherUtils[] myTextWatcherUtils = {null};

        mUnitAInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if(hasFocus) {

                    myTextWatcherUtils[0] = null; //Clears the variable and starts fresh.
                    myTextWatcherUtils[0] = new MyTextWatcherUtils(1, unitAValue, unitBValue, mUnitAInputEditText, mUnitBInputEditText);
                    myTextWatcherUtils[0].removeTextWatcher();
                    myTextWatcherUtils[0].setUnitEditTextWatcher(mUnitAInputEditText);
                }

                else {
                    myTextWatcherUtils[0].removeTextWatcher();

                }


            }
        });


    }

    private void keyboardManager() {

        //Keyboard opens when Converter Icon is clicked
        mUnitAInputEditText.requestFocus();
        InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
        imm.showSoftInput(mUnitAInputEditText, InputMethodManager.SHOW_IMPLICIT);

        //When keyboard is closed, Hides the converter UI.
        KeyboardUtils.addKeyboardToggleListener(getActivity(), new KeyboardUtils.SoftKeyboardToggleListener() {

            @Override
            public void onToggleSoftKeyboard(boolean isVisible) {

                Log.i("KTAG", "keyboard visible: " + isVisible);
                if (isVisible) {
                    mConverterUI.setVisibility(View.VISIBLE);
                } else {
                    mConverterUI.setVisibility(View.GONE);
                    clearUserInput();

                }
            }
        });

    }



    private void clearUserInput() {

        if (mUnitAInputEditText.getText() != null) {
            mUnitAInputEditText.getText().clear();
        }

        if (mUnitBInputEditText.getText() != null) {
            mUnitBInputEditText.getText().clear();
        }

    }


}
