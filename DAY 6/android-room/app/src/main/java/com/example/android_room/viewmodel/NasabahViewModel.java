package com.example.android_room.viewmodel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.android_room.daos.NasabahRepository;
import com.example.android_room.model.Nasabah;

import java.util.List;

public class NasabahViewModel extends AndroidViewModel {
    private NasabahRepository nbRepo;
    private LiveData<List<Nasabah>> allNB;

    public NasabahViewModel(@NonNull Application application) {
        super(application);
        nbRepo = new NasabahRepository(application);
        allNB = nbRepo.gettAllNB();
    }

    public void insert(Nasabah nasabah) {
        nbRepo.insert(nasabah);
    }

    public void update(Nasabah nasabah) {
        nbRepo.update(nasabah);
    }

    public void delete(Nasabah nasabah) {
        nbRepo.delete(nasabah);
    }

    public void deleteAll() {
        nbRepo.deleteAll();
    }

    public LiveData<List<Nasabah>> getAllData() {
        return allNB;
    }
}
