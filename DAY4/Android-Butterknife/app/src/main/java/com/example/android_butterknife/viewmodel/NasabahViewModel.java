package com.example.android_butterknife.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_butterknife.model.NasabahResponse;
import com.example.android_butterknife.networking.NasabahRepository;

public class NasabahViewModel extends ViewModel {
    private MutableLiveData<NasabahResponse> mutableAll;
    private NasabahRepository nbRepo;

    public void init() {
        if (mutableAll != null) {
            return;
        }
        nbRepo = NasabahRepository.getInstance();
        mutableAll = nbRepo.getAllNasabah("1", "10");
    }

    public LiveData<NasabahResponse> getAllRepo() {
        return mutableAll;
    }

    public void refresh(String page, String limit) {
        if (mutableAll != null) {
            mutableAll = nbRepo.getAllNasabah(page, limit);
            return;
        }
        nbRepo = NasabahRepository.getInstance();
        mutableAll = nbRepo.getAllNasabah("1", "10");
    }
}
