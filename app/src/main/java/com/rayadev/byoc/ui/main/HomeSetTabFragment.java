package com.rayadev.byoc.ui.main;

import android.content.Context;
import android.os.Bundle;

import androidx.activity.OnBackPressedCallback;
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

        //Text watchers for the editText

        //Loop problem solved.. But now there's a performance issue.
        //Performance issues

        //Add on Click clear input.


        final int[] editTextSelectedId = new int[1];

        final boolean[] editTextASelected = new boolean[1];
        final boolean[] editTextBSelected = new boolean[1];

        TextWatcher mUnitEditTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            //Here! Check to see which edit text has focus..
            //Based on that you can

            if(editTextASelected[0]) {
//                mUnitAInputEditText.addTextChangedListener(this);
                mUnitBInputEditText.removeTextChangedListener(this);
            }

            else if(editTextBSelected[0]) {
//                mUnitBInputEditText.addTextChangedListener(this);
                mUnitAInputEditText.removeTextChangedListener(this);

                }

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                if (editTextASelected[0]) {


                    //Just need string checks for Double.parse needed. Plenty of cases where it bugs.
                    //E's and what not.

                    String editTextAInputString = String.valueOf(mUnitAInputEditText.getText());
                    if(!editTextAInputString.equals("")) {
                        double unitAInput = Double.parseDouble(editTextAInputString);
                        double result = runConversionAB(unitAInput, unitBValue);
                        String resultText = result + "";
                        mUnitBInputEditText.setText(resultText);
                    }
                    else{
                        mUnitBInputEditText.setText(""); //This line is cuasing the loop


                    }

                }

                else if (editTextBSelected[0]) {

                    mUnitAInputEditText.removeTextChangedListener(this);
                    mUnitAInputEditText.clearComposingText();

                    String editTextBInputString = String.valueOf(mUnitBInputEditText.getText());
                    if(!editTextBInputString.equals("")) {
                        double unitBInput = Double.parseDouble(String.valueOf(mUnitBInputEditText.getText()));
                        double result = runConversionBA(unitBInput, unitAValue);
                        String resultText = result + "";
                        mUnitAInputEditText.setText(resultText);
                    }
                    else {
//                        mUnitAInputEditText.setText("");
//                        mUnitAInputEditText.clearComposingText();

                    }
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };


        //This fixes the input issue. Keeping these outside the onClick.
        //The bug is almost fixed!!

        /*Adding and removing text Watchers is whats causing the performance issue
       So both EditTexts need to be using a single text watcher.
       Or just set the TextWatcher's permanently.

       The bug is more when you switch between converters than anything else.


         */



        mUnitAInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                if (hasFocus) {

                    editTextASelected[0] = true;


                    if (editTextBSelected[0]) {
//                        mUnitBInputEditText.removeTextChangedListener(mUnitEditTextWatcher);
                        editTextBSelected[0] = false;

                    }

                    Toast.makeText(getContext(), "Edit Text A", Toast.LENGTH_SHORT).show();
                    mUnitAInputEditText.addTextChangedListener(mUnitEditTextWatcher);
                    clearUserInput();

                }
            }

        });

        mUnitBInputEditText.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {

                if (hasFocus) {

                    editTextBSelected[0] = true;


                    if (editTextASelected[0]) {
//                        mUnitAInputEditText.removeTextChangedListener(mUnitEditTextWatcher);
                        editTextASelected[0] = false;
                    }


                    Toast.makeText(getContext(), "Edit Text B", Toast.LENGTH_SHORT).show();
                    mUnitBInputEditText.addTextChangedListener(mUnitEditTextWatcher);
                    clearUserInput();

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

    private double runConversionAB(double unitAValue, double unitBValue) {

//        String test = 1.0+"";
//        mUnitBInputEditText.setText(test);
        double result = unitAValue * unitBValue;
        return result;
    }

    private double runConversionBA(double unitBValue, double unitAValue) {

        //Needs a different conversion ratio.
        double result = unitBValue * unitAValue;
        return result;
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
