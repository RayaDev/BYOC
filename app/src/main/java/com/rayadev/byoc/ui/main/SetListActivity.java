package com.rayadev.byoc.ui.main;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.rayadev.byoc.R;

/*The Activity triggered by the user selection from the SetList
Contains a screen full of CB's (Converter Box's) for that particular Set List
Can Also be triggered by subsets in the HomeSetTab
Still has to relay data to a View Model like an other UI facing class.
*/
public class SetListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_list);
    }
}