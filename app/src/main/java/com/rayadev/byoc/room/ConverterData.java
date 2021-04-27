package com.rayadev.byoc.room;

import android.app.Activity;

import androidx.appcompat.app.AppCompatActivity;

import com.rayadev.byoc.R;

public class ConverterData extends AppCompatActivity{

    String s = getResources().getString(R.string.distance_meter);

static {
    //Meters as base unit********************************

    Converter mMtoKM = new Converter(getResources().getString(R.string.distance_meter), R.string.distance_kilometer,1, 0.001);


    Converter mMtoM = new Converter(R.string.distance_meter,R.string.distance_meter,1, 1);

    Converter mMtoCM = new Converter(R.string.distance_meter,R.string.distance_centimeter,1, 100);


    Converter mMtoMM = new Converter(R.string.distance_meter,R.string.distance_millimeter,1, 1000);


    Converter mMtoMI = new Converter(R.string.distance_meter, R.string.distance_miles,1,0.00062);


    Converter mMtoYD = new Converter(R.string.distance_meter, R.string.distance_yard,1,1.09361);


    Converter mMtoFT = new Converter(R.string.distance_meter, R.string.distance_foot,1,3.280);

    Converter mMtoIN = new Converter(R.string.distance_meter, R.string.distance_inch,1,39.3701);
}

}
