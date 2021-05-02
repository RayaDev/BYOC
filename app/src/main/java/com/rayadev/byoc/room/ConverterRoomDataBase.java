package com.rayadev.byoc.room;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.rayadev.byoc.R;

import java.util.ArrayList;


@Database(entities = {Converter.class}, version = 1, exportSchema = false)
public abstract class ConverterRoomDataBase extends RoomDatabase {

    //Provide an abstract "getter" method for each @Dao
    //What does this method do exactly? It's part of the pattern though. Returns a ConverterDao!
    public abstract ConverterDAO mConverterDAO();

    //Create the ConverterRoomDatabase as a singleton to prevent having multiple instances of the database opened at the same time,
    private static ConverterRoomDataBase INSTANCE;

    public static ConverterRoomDataBase getDatabase(final Context context) {
        if (INSTANCE == null) {
            synchronized (ConverterRoomDataBase.class) {
                if (INSTANCE == null) {

                    // Create database here
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            ConverterRoomDataBase.class, "word_database")
                            // Wipes and rebuilds instead of migrating
                            // if no Migration object.
                            // Migration is not part of this practical.
                            .fallbackToDestructiveMigration()
                            .addCallback(sRoomDatabaseCallback) //Adds the call back the cleans and repopulates the database,
                            .build();
                }
            }
        }
        return INSTANCE;

    }

    /*
    Through this code.
    Every time the database is opened, all content is deleted and repopulated.
    This is a reasonable solution for a sample app, where you usually want to restart on a clean slate.
     */

    private static RoomDatabase.Callback sRoomDatabaseCallback =
            new RoomDatabase.Callback(){

                @Override
                public void onOpen (@NonNull SupportSQLiteDatabase db){
                    super.onOpen(db);
                    new PopulateDbAsync(INSTANCE).execute();
                }
            };


    /**
     * Populate the database in the background.
     */
    private static class PopulateDbAsync extends AsyncTask<Void, Void, Void> {

        private final ConverterDAO mDao;

        //String[] words = {"dolphin", "crocodile", "cobra"};

        ArrayList<Converter> mConverterArrayList = new ArrayList<>();

        PopulateDbAsync(ConverterRoomDataBase db) {
            mDao = db.mConverterDAO();
        }

        @Override
        protected Void doInBackground(final Void... params) {
            // Start the app with a clean database every time.
            // Not needed if you only populate the database
            // when it is first created
            //mDao.deleteAll();

            //****Populate the DataBase here****
            //Repeat for each converter wanted... Maybe load from a JSON file to make life easier...

            //Or load all this from a seperate ConverterDataClass like in the old one.



            //Test database.. These String Resources for the name are super important.. Used to search the database. 
            Converter mConverter = new Converter("KM","Miles", R.drawable.ic_baseline_distance_icon, 1,1);
            mConverterArrayList.add(mConverter);


            if (mDao.getAnyConverter().length < 1) {   //If we have no words, then create the initial list of words
                for (int i = 0; i <= mConverterArrayList.size() - 1; i++) {
                    Converter converter = mConverterArrayList.get(i);
                    mDao.insertConverter(converter);
                }
            }

            return null;
        }
    }
}
