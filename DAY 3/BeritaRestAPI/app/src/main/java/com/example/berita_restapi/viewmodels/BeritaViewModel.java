package com.example.berita_restapi.viewmodels;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.berita_restapi.model.Berita;
import com.example.berita_restapi.model.BeritaResponse;
import com.example.berita_restapi.model.SingleResponse;
import com.example.berita_restapi.networking.BeritaRepository;

public class BeritaViewModel extends ViewModel {
    private MutableLiveData<BeritaResponse> newsLiveData;
    private MutableLiveData<SingleResponse> singleLiveData;
    private BeritaRepository newsRepo;

    public void init() {
        if (newsLiveData != null) {
            return;
        }
        newsRepo = BeritaRepository.getInstance();
        newsLiveData = newsRepo.allNews("1", "20");
    }

    public LiveData<BeritaResponse> getNewsRepo() {
        return newsLiveData;
    }

    public void refresh(String page, String limit) {
        if (newsLiveData != null) {
            newsLiveData = newsRepo.allNews(page, limit);
            return;
        }
        newsRepo = BeritaRepository.getInstance();
        newsLiveData = newsRepo.allNews("1", "20");
    }

    public LiveData<SingleResponse> postNewsRepo(Berita payload) {
        if (singleLiveData == null) {
            newsRepo = BeritaRepository.getInstance();
        }
        singleLiveData = newsRepo.newNews(payload);
        return singleLiveData;
    }
}
