package com.example.berita_restapi.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.berita_restapi.model.Berita;
import com.example.berita_restapi.model.BeritaResponse;
import com.example.berita_restapi.model.SingleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BeritaRepository {
    public static BeritaRepository newsesRepo;

    public static BeritaRepository getInstance() {
        if (newsesRepo == null) {
            newsesRepo = new BeritaRepository();
        }
        return newsesRepo;
    }

    private BeritaAPI newsAPI;
    public BeritaRepository() {
        newsAPI = RetrofitService.createService(BeritaAPI.class);
    }

    public MutableLiveData<BeritaResponse> allNews(String page, String limit){
        MutableLiveData<BeritaResponse> newsData = new MutableLiveData<>();
        newsAPI.getNewsList(page, limit).enqueue(new Callback<BeritaResponse>() {
            @Override
            public void onResponse(Call<BeritaResponse> call, Response<BeritaResponse> response) {
                if (response.isSuccessful()) {
                    Log.d("Halo, berhasil!", response.body().toString());
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<BeritaResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }

    public MutableLiveData<SingleResponse> newNews(Berita payload) {
        MutableLiveData<SingleResponse> newsData = new MutableLiveData<>();
        newsAPI.postNews(payload).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                if (response.isSuccessful()) {
                    System.out.println("Post Berita");
                    newsData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                newsData.setValue(null);
            }
        });
        return newsData;
    }
}
