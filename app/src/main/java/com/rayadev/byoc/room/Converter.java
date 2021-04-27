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

    private String mConverterUnitA_Name;
    private String mConverterUnitB_Name;
    private int mConverterBoxImageID;

    @ColumnInfo(name = "converter_name")
    private String mConverterName;
//    private int mConverterRatioAB;
//    private int mConverterRatioBA;


    public Converter() {

    }

    public Converter(String mConverterUnitA_Name, String mConverterUnitB_Name, int mConverterBoxImageID, double mConverterRatioAB,
                     double mConverterRatioBA) {

        this.mConverterUnitA_Name = mConverterUnitA_Name;
        this.mConverterUnitB_Name = mConverterUnitB_Name;
        this.mConverterBoxImageID = mConverterBoxImageID;
        this.mConverterName = mConverterUnitA_Name + mConverterUnitB_Name; //This will be the unique key that we search converters with.
//        this.mConverterRatioAB = mConverterRatioAB;
//        this.mConverterRatioBA = mConverterRatioBA;

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


//    public int getConverterRatioAB() {
//        return mConverterRatioAB;
//    }
//
//    public int getConverterRatioBA() {
//        return mConverterRatioBA;
//    }
}
