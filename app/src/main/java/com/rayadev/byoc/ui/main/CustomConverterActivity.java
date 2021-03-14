package com.rayadev.byoc.ui.main;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

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
        setUpToolbar();
    }

    private void setUpToolbar() {

        //Links the toolbar over the app Actionbar
        Toolbar toolbar = findViewById(R.id.custom_converter_activity_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);
        setTitle("Converter Workshop");
    }
}