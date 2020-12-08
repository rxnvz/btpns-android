package com.example.tugas_berita.daos;

import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.tugas_berita.model.Berita;

@Database(entities = {Berita.class}, version = 1)
public abstract class DaoDatabase extends RoomDatabase {
    private static DaoDatabase instance;
    public abstract BeritaDao beritaDao();

    public static synchronized DaoDatabase getInstance(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    DaoDatabase.class, "berita_db")
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
        private BeritaDao beritaDao;

        private PopulateDbAsyncTask(DaoDatabase db) {
            beritaDao = db.beritaDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            beritaDao.insertRoom(new Berita("Irene", "Jakarta Timur", "rene@g.co"));
            return null;
        }
    }
}
