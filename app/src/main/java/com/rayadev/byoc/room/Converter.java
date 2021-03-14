package com.rayadev.byoc.room;

import android.widget.TextView;

public class Converter {

    private String mConverterUnitA_Name;
    private String mConverterUnitB_Name;
    private int mConverterBoxImageID;
//    private int mConverterRatioAB;
//    private int mConverterRatioBA;

    public Converter(String mConverterUnitA_Name, String mConverterUnitB_Name, int mConverterBoxImageID, int mConverterRatioAB,
                     int mConverterRatioBA) {

        this.mConverterUnitA_Name = mConverterUnitA_Name;
        this.mConverterUnitB_Name = mConverterUnitB_Name;
        this.mConverterBoxImageID = mConverterBoxImageID;
//        this.mConverterRatioAB = mConverterRatioAB;
//        this.mConverterRatioBA = mConverterRatioBA;

    }

    public String getConverterUnitA_Name() {
        return mConverterUnitA_Name;
    }

    public String getConverterUnitB_Name() {
        return mConverterUnitB_Name;
    }

    public int getConverterBoxImageID() {
        return mConverterBoxImageID;
    }

//    public int getConverterRatioAB() {
//        return mConverterRatioAB;
//    }
//
//    public int getConverterRatioBA() {
//        return mConverterRatioBA;
//    }
}
