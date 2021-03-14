package com.rayadev.byoc.room;

import android.widget.TextView;

public class Converter {

    private TextView mConverterUnitA_Name;
    private TextView mConverterUnitB_Name;
    private int mConverterBoxImageID;
    private int mConverterRatioAB;
    private int mConverterRatioBA;

    public Converter(TextView mConverterUnitA_Name, TextView mConverterUnitB_Name, int mConverterBoxImageID, int mConverterRatioAB,
                     int mConverterRatioBA) {

        this.mConverterUnitA_Name = mConverterUnitA_Name;
        this.mConverterUnitB_Name = mConverterUnitB_Name;
        this.mConverterBoxImageID = mConverterBoxImageID;
        this.mConverterRatioAB = mConverterRatioAB;
        this.mConverterRatioBA = mConverterRatioBA;

    }
}
