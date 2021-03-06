package com.rayadev.byoc.room;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import java.util.List;


//Links the data from the Repository to the Main Activity, in order to update the UI.
public class ConverterViewModel extends AndroidViewModel {

    //Add a private member variable to hold a reference to the Repository.
    private ConverterRepository mRepository;

    //Add a private LiveData member variable to cache the list of converters.
    private LiveData<List<Converter>> mAllConverters;

    private LiveData<List<Converter>> mFavoriteConverters;


    public ConverterViewModel(@NonNull Application application) {
        super(application);
        mRepository = new ConverterRepository(application);
        mAllConverters = mRepository.getAllConverters();
        mFavoriteConverters = mRepository.getFavoriteConverters();
    }

    // The ViewModel implements all methods that will be available to the Main Activity.
    // This completely hides the implementation from the UI.
    // Pass these up to the the Main Activity or whatever needs access.
    /*
    In this case, this ViewModel would mostly be accessed by:
        >The Add button, any delete buttons
            > Which is informed by the selections on the Spinner.
     */

    //
    public LiveData<List<Converter>> getAllConverters() { return mAllConverters; }

    //This will be implemented by the Spinner. All Converters will already exist in the database.
    public LiveData<List<Converter>> getTargetConverter(String converterName) {
        return mRepository.getTargetConverter(converterName);
    }

    public LiveData<List<Converter>> getFavoriteConverters() {return mFavoriteConverters;}


    public void insertConverter(Converter converter) {mRepository.insertConverter(converter);}

    //Delete all converters
    public void deleteConverters(Converter converter) {mRepository.deleteConverter(converter);}

   public void deleteConverterByID(int converterID) {
       mRepository.deleteConverterByID(converterID);
   }

}
