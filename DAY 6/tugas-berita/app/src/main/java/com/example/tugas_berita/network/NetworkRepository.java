package com.example.tugas_berita.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugas_berita.model.Berita;
import com.example.tugas_berita.model.BeritaResponse;
import com.example.tugas_berita.model.SingleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NetworkRepository {

    public static NetworkRepository networkRepository;
    public static NetworkRepository getInstance() {
        if (networkRepository == null) {
            networkRepository = new NetworkRepository();
        }
        return networkRepository;
    }

    private API newsAPI;
    public NetworkRepository() {
        newsAPI = RetrofitService.createService(API.class);
    }

    // Buat get All data from API
    public MutableLiveData<BeritaResponse> getAll(String page, String limit) {
        MutableLiveData<BeritaResponse> newsData = new MutableLiveData<>();
        newsAPI.getAll(page, limit).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                Log.d("Bisa fetch nih cuy", response.body().toString());
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    // Buat post new data to API
    public MutableLiveData<SingleResponse> newNews(Berita payload) {
        MutableLiveData<SingleResponse> newsData = new MutableLiveData<>();
        newsAPI.postNews(payload).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                System.out.println("Berhasil post :D");
                newsData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
