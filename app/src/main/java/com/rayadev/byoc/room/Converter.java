package com.rayadev.byoc.room;


import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "converter_table")
//Room should serialize everything inside a class marked with Entity.
//Add column names if needed, but otherwise all Converter data should still be stored.
public class Converter {

    //Room annotations
    @PrimaryKey(autoGenerate = true)
    private int converterID;

    @NonNull
    private String mConverterUnitA_Name;

    @NonNull
    private String mConverterUnitB_Name;

    private double mConverterUnitA_Value;

    private double mConverterUnitB_Value;

    private int mConverterBoxImageID;

    @ColumnInfo(name = "converter_name") @NonNull
    private String mConverterName;


    public Converter(String mConverterUnitA_Name, String mConverterUnitB_Name, int mConverterBoxImageID, double mConverterUnitA_Value, double mConverterUnitB_Value) {

        this.mConverterUnitA_Name = mConverterUnitA_Name;
        this.mConverterUnitB_Name = mConverterUnitB_Name;
        this.mConverterBoxImageID = mConverterBoxImageID;
        this.mConverterName = mConverterUnitA_Name + mConverterUnitB_Name; //This will be the unique key to search for converters with.
        this.mConverterUnitA_Value = mConverterUnitA_Value;
        this.mConverterUnitB_Value = mConverterUnitB_Value;

    }

    public String getConverterUnitA_Name() {
        return mConverterUnitA_Name;
    }

    public void setConverterUnitA_Name(String converterUnitA_Name) {
        mConverterUnitA_Name = converterUnitA_Name;
    }

    public String getConverterUnitB_Name() {
        return mConverterUnitB_Name;
    }

    public void setConverterUnitB_Name(String converterUnitB_Name) {
        mConverterUnitB_Name = converterUnitB_Name;
    }

    public int getConverterBoxImageID() {
        return mConverterBoxImageID;
    }

    public void setConverterBoxImageID(int converterBoxImageID) {
        mConverterBoxImageID = converterBoxImageID;
    }

    public String getConverterName() {
        return mConverterName;
    }

    public void setConverterName(String converterName) {
        mConverterName = converterName;
    }

    public int getConverterID() {
        return converterID;
    }

    public void setConverterID(int converterID) {
        this.converterID = converterID;
    }


    public double getConverterUnitA_Value() {
        return mConverterUnitA_Value;
    }

    public void setConverterUnitA_Value(double converterUnitA_Value) {
        mConverterUnitA_Value = converterUnitA_Value;
    }

    public double getConverterUnitB_Value() {
        return mConverterUnitB_Value;
    }

    public void setConverterUnitB_Value(double converterUnitB_Value) {
        mConverterUnitB_Value = converterUnitB_Value;
    }


}
