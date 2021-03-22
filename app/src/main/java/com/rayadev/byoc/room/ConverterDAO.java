package com.rayadev.byoc.room;

/*
The data access object, or Dao,
is an annotated class where you specify SQL queries and associate them with method calls.

How you interact and make changes to the SQL database and Entities.
 */

//All of these tasks are performed on a separate thread, but Room handles that automatically.. Thanks Room..

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.ArrayList;
import java.util.List;

@Dao
interface ConverterDAO {

    //No need to provide SQL here b/c of Room.
    //Ignores duplicate words.
    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertConverter(Converter converter);

    //Delete a single converter from the database
    @Delete
    void deleteConverter(Converter converter);

    @Query("SELECT * from converter_table WHERE converter_name =:converterName")
    ArrayList<Converter> getTargetConverter(String converterName); //Should be get a distinct entity, but ArrayList just in case.


    @Query("SELECT * from converter_table ORDER BY converter_name ASC") //Ordering word/entities makes testing easier.
    LiveData<List<Converter>> getAllConverters();
}
