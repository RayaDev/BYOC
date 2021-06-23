package com.rayadev.byoc.ui.main;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

class MyTextWatcherUtils {

    private EditText viewA, viewB;
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


        mTextWatcher = new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {


            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

                switch(userSelection) {
                    case 1:
                        if(viewA.isFocused()) {
                            runConversionAB();
                        }

                        break;
                    case 2:
                        if(viewB.isFocused()) {
                            runConversionBA();
                        }
                        break;
                }


            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        };

        userInputEditText.addTextChangedListener(mTextWatcher);
    }

    public void removeTextWatcher(EditText userInputEditText) {
        userInputEditText.removeTextChangedListener(mTextWatcher);
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
//            viewB.setText(""); //This line is cuasing the loop

        }

    }

    private void runConversionBA() {

        String editTextBInputString = String.valueOf(viewB.getText());
        if(!editTextBInputString.equals("")) {
            double unitBInput = Double.parseDouble(editTextBInputString);

            double ratio = unitAValue / unitBValue;
            double result = unitBInput * ratio;
            String resultText = result + "";
            viewA.setText(resultText);
        }
        else{
//            viewA.setText(""); //This line is cuasing the loop


        }

    }


}
