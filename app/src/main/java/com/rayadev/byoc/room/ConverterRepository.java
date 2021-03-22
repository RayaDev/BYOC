package com.rayadev.byoc.room;


import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import java.util.List;

//Layer of abstraction. Passes data from either the RoomDatabase, or a network source.
public class ConverterRepository {

    private ConverterDAO mConverterDao;
    private LiveData<List<Converter>> mAllConverters;

    //Add a constructor that gets a handle to the database and initializes the member variables.
    public ConverterRepository (Application application) {
        ConverterRoomDataBase db = ConverterRoomDataBase.getDatabase(application);
        mConverterDao = db.mConverterDAO();
        mAllConverters = mConverterDao.getAllConverters();
    }

    //This are the methods that get called by the ViewModel.

    /*
    Add a wrapper method called getAllWords() that returns the cached words as LiveData.
    Room executes all queries on a separate thread.
    Observed LiveData notifies the observer when the data changes.
     */
    LiveData<List<Converter>> getAllConverters() {
        return mAllConverters;
    }

    /*
   Add a wrapper for the insert() method. Use an AsyncTask to call insert() on a non-UI thread, or your app will crash.
   Room ensures that you don't do any long-running operations on the main thread, which would block the UI.
    */
    public void insert (Converter converter) {
        new insertAsyncTask(mConverterDao).execute(converter);
    }

    public void deleteConverter(Converter converter)  {
        new deleteConverterAsyncTask(mConverterDao).execute(converter);
    }

    //Every method from the ConverterDAO needs an Async innerclass to execute.

    private static class insertAsyncTask extends AsyncTask<Converter, Void, Void> {

        private ConverterDAO mAsyncTaskDao;

        insertAsyncTask(ConverterDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(final Converter... params) {
            mAsyncTaskDao.insertConverter(params[0]);
            return null;
        }
    }

    private static class deleteConverterAsyncTask extends AsyncTask<Converter , Void, Void> {

        private ConverterDAO mAsyncTaskDao;

        public deleteConverterAsyncTask(ConverterDAO dao) {
            mAsyncTaskDao = dao;
        }

        @Override
        protected Void doInBackground(Converter... params) {
            mAsyncTaskDao.deleteConverter(params[0]);
            return null;
        }
    }


}
