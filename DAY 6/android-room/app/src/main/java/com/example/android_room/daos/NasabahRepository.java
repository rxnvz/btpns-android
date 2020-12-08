package com.example.android_room.daos;

import android.app.Application;
import android.os.AsyncTask;
import android.util.Log;

import androidx.lifecycle.LiveData;

import com.example.android_room.model.Nasabah;

import java.util.List;

public class NasabahRepository {
    private NasabahDao nbDAO;
    private LiveData<List<Nasabah>> allNB;

    public NasabahRepository(Application application) {
        NasabahDatabase db = NasabahDatabase.getInstance(application);
        nbDAO = db.nbDAO();
        allNB = nbDAO.getAllData();
    }

    public void insert(Nasabah nasabah) {
        new InsertNBAsyncTask(nbDAO).execute(nasabah);
    }
    public void update(Nasabah nasabah) {
        new UpdateNBAsyncTask(nbDAO).execute(nasabah);
    }
    public void delete(Nasabah nasabah) {
        new DeleteNBAsyncTask(nbDAO).execute(nasabah);
    }
    public void deleteAll() {
        new DeleteAllNBAsyncTask(nbDAO).execute();
    }
    public LiveData<List<Nasabah>> gettAllNB() {
        return allNB;
    }

    public static class InsertNBAsyncTask extends AsyncTask<Nasabah, Void, Void> {
        private NasabahDao nasabahDao;

        private InsertNBAsyncTask(NasabahDao nasabahDao) {
            this.nasabahDao = nasabahDao;
        }

        @Override
        protected Void doInBackground(Nasabah... nasabahs) {
            nasabahDao.insert(nasabahs[0]);
            return null;
        }
    }

    public static class UpdateNBAsyncTask extends AsyncTask<Nasabah, Void, Void> {
        private NasabahDao nasabahDao;

        private UpdateNBAsyncTask(NasabahDao nasabahDao) {
            this.nasabahDao = nasabahDao;
        }

        @Override
        protected Void doInBackground(Nasabah... nasabahs) {
            nasabahDao.update(nasabahs[0]);
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            Log.d("UpdateNasabahAsyncTask", "Finish");
        }
    }

    public static class DeleteNBAsyncTask extends  AsyncTask<Nasabah, Void, Void> {
        private NasabahDao nasabahDao;

        private DeleteNBAsyncTask(NasabahDao nasabahDao) {
            this.nasabahDao = nasabahDao;
        }

        @Override
        protected Void doInBackground(Nasabah... nasabahs) {
            nasabahDao.delete(nasabahs[0]);
            return null;
        }
    }

    public static class DeleteAllNBAsyncTask extends AsyncTask<Void, Void, Void> {
        private NasabahDao nasabahDao;

        private DeleteAllNBAsyncTask(NasabahDao nasabahDao) {
            this.nasabahDao = nasabahDao;
        }

        @Override
        protected Void doInBackground(Void... voids) {
            nasabahDao.deleteAll();
            return null;
        }
    }
}
