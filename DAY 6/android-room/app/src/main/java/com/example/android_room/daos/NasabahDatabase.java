package com.example.android_room.daos;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.android_room.model.Nasabah;

@Database(entities = {Nasabah.class}, version = 1)
public abstract class NasabahDatabase extends RoomDatabase {
    private static NasabahDatabase instance;
    public abstract NasabahDao nbDAO();

    public static synchronized NasabahDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    NasabahDatabase.class, "nasabah_database")
                    .fallbackToDestructiveMigration()
                    .addCallback(roomCB)
                    .build();
        }
        return instance;
    }

    private static RoomDatabase.Callback roomCB = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(instance).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private NasabahDao nbDAO;

        private PopulateDbAsyncTask(NasabahDatabase db) {
            nbDAO = db.nbDAO();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            nbDAO.insert(new Nasabah("Irene", "Jakarta Timur", "rene@g.co"));
            nbDAO.insert(new Nasabah("Cimot", "Jakarta Selatan", "cimot@g.co"));
            nbDAO.insert(new Nasabah("Kuma", "Jakarta Barat", "kuma@g.co"));
            return null;
        }
    }
}
