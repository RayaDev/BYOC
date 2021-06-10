package com.rayadev.byoc.ui.main;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class MyTextWatcherUtils {

    private EditText viewA, viewB, userInputEditText1;
    private double unitAValue, unitBValue;
    private int userSelection;

    private TextWatcher mTextWatcher;

    MyTextWatcherUtils(int userSelection, double unitAValue, double unitBValue, EditText viewA, EditText viewB) {

        this.userSelection = userSelection;
        this.unitAValue = unitAValue;
        this.unitBValue = unitBValue;

        this.viewA = viewA;
        this.viewB = viewB;

    }

    public void setUnitEditTextWatcher(EditText userInputEditText) {

        this.userInputEditText1 = userInputEditText;

        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch(userSelection) {
                    case 1:
                        runConversionAB();
                        break;
                    case 2:
                        runConversionBA();
                        break;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        userInputEditText1.addTextChangedListener(mTextWatcher);
    }

    public void removeTextWatcher() {
        userInputEditText1.removeTextChangedListener(mTextWatcher);
    }

    private void runConversionAB() {

        String editTextAInputString = String.valueOf(viewA.getText());
        if(!editTextAInputString.equals("")) {
            double unitAInput = Double.parseDouble(editTextAInputString);
            double result = unitAInput * unitBValue;
            String resultText = result + "";
            viewB.setText(resultText);
        }
        else{
            viewB.setText(""); //This line is cuasing the loop


        }

    }

    private void runConversionBA() {

        String editTextBInputString = String.valueOf(viewB.getText());
        if(!editTextBInputString.equals("")) {
            double unitBInput = Double.parseDouble(editTextBInputString);
            double result = unitBInput * unitAValue;
            String resultText = result + "";
            viewA.setText(resultText);
        }
        else{
            viewA.setText(""); //This line is cuasing the loop


        }

    }


}
