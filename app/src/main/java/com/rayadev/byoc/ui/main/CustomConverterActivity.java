package com.rayadev.byoc.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rayadev.byoc.R;

/*Activity that gets triggered by the wrench on the ConverterTab

Allows the user to build their own converter, and save it to the HomeTab.
 */

public class CustomConverterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_converter);
    }
}