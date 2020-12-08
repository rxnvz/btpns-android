package com.example.tugas_berita.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.tugas_berita.model.Berita;
import com.example.tugas_berita.model.BeritaResponse;
import com.example.tugas_berita.model.SingleResponse;
import com.example.tugas_berita.network.NetworkRepository;

import java.util.List;

public class BeritaViewModel extends ViewModel {
    private NetworkRepository netRepo;
    private LiveData<List<Berita>> newsList;
    private MutableLiveData<BeritaResponse> newsLiveData;
    private MutableLiveData<SingleResponse> singleLiveData;

//    public BeritaViewModel(@NonNull Application application) {
//        super(application);
//    }

    public void init() {
        if (newsLiveData != null) {
            return;
        }
        netRepo = NetworkRepository.getInstance();
        newsLiveData = netRepo.getAll("1", "20");
    }

    public LiveData<BeritaResponse> getNewsRepo() {
        return newsLiveData;
    }

    public LiveData<SingleResponse> newNewsRepo(Berita payload) {
        if (singleLiveData == null) {
            netRepo = NetworkRepository.getInstance();
        }
        singleLiveData = netRepo.newNews(payload);
        return singleLiveData;
    }
}
