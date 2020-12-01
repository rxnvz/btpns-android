package com.example.android_crud.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_crud.model.Nasabah;
import com.example.android_crud.model.NasabahResponse;
import com.example.android_crud.model.SingleResponse;
import com.example.android_crud.networking.NasabahRepository;

public class NasabahViewModel extends ViewModel {
    private MutableLiveData<NasabahResponse> mutableLiveData;
    private NasabahRepository nbsRepo;
    private MutableLiveData<SingleResponse> mutableNasabahLiveData;

    public void init() {
        if (mutableLiveData != null) {
            return;
        }
        nbsRepo = NasabahRepository.getInstance();
        mutableLiveData = nbsRepo.getNasabah("1", "10");
    }

    public LiveData<NasabahResponse> getNasabahRepo() {
        return mutableLiveData;
    }

    public void refresh(String page, String limit) {
        if (mutableLiveData != null) {
            mutableLiveData = nbsRepo.getNasabah(page, limit);
            return;
        }
        nbsRepo = NasabahRepository.getInstance();
        mutableLiveData = nbsRepo.getNasabah("1", "10");
    }

    public LiveData<SingleResponse> getSingleRepo (String id) {
        if (mutableNasabahLiveData == null) {
            nbsRepo = NasabahRepository.getInstance();
            mutableNasabahLiveData = nbsRepo.getSingleNasabah(id);
        }
        return mutableNasabahLiveData;
    }

//    public void getNasabah(String id) {
//        if (mutableNasabahLiveData != null) {
//            mutableNasabahLiveData = nbsRepo.getSingleNasabah(id);
//            return;
//        }
//        nbsRepo = NasabahRepository.getInstance();
//        mutableNasabahLiveData = nbsRepo.getSingleNasabah(id);
//    }

    public LiveData<SingleResponse> putNasabahRepo(String id, Nasabah nasabahPayLoad) {
        if (mutableNasabahLiveData == null) {
            nbsRepo = NasabahRepository.getInstance();
        }
        mutableNasabahLiveData = nbsRepo.updateNasabah(id, nasabahPayLoad);
        return mutableNasabahLiveData;
    }

    public LiveData<SingleResponse> postNasabahRepo(Nasabah nasabahPayload) {
        if (mutableNasabahLiveData == null) {
            nbsRepo =  NasabahRepository.getInstance();
        }
        mutableNasabahLiveData = nbsRepo.postNasabah(nasabahPayload);
        return mutableNasabahLiveData;
    }

    public LiveData<SingleResponse> deleteNasabahRepo(String id) {
        if (mutableNasabahLiveData == null) {
            nbsRepo = NasabahRepository.getInstance();
        }
        mutableNasabahLiveData = nbsRepo.deleteNasabah(id);
        return mutableNasabahLiveData;
    }
}
