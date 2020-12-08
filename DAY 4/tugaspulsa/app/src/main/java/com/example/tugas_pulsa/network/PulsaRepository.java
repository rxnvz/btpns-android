package com.example.tugas_pulsa.network;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.tugas_pulsa.model.Product;
import com.example.tugas_pulsa.model.ProductResponse;
import com.example.tugas_pulsa.model.Pulsa;
import com.example.tugas_pulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PulsaRepository {
    public static PulsaRepository pulsasRepo;
    public static PulsaRepository getInstance() {
        if (pulsasRepo == null) {
            pulsasRepo = new PulsaRepository();
        }
        return pulsasRepo;
    }

    private PulsaAPI pulsaAPI;
    public PulsaRepository() {
        pulsaAPI = RetrofitService.createService(PulsaAPI.class);
    }

    public MutableLiveData<ProductResponse> getAll() {
        MutableLiveData<ProductResponse> dataPulsa = new MutableLiveData<>();
        pulsaAPI.getPulsa().enqueue(new Callback<ProductResponse>() {
            @Override
            public void onResponse(Call<ProductResponse> call, Response<ProductResponse> response) {
                if (response.isSuccessful()) {
                    dataPulsa.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<ProductResponse> call, Throwable t) {
                dataPulsa.setValue(null);
            }
        });
        return dataPulsa;
    }

    public MutableLiveData<PulsaResponse> beliPulsa(Pulsa payload) {
        MutableLiveData<PulsaResponse> dataPulsa = new MutableLiveData<>();
        pulsaAPI.buyPulsa(payload).enqueue(new Callback<PulsaResponse>() {
            @Override
            public void onResponse(Call<PulsaResponse> call, Response<PulsaResponse> response) {
                Log.d("BERHASIL CUY: ", response.body().toString());
                dataPulsa.setValue(response.body());
            }

            @Override
            public void onFailure(Call<PulsaResponse> call, Throwable t) {
                dataPulsa.setValue(null);
            }
        });
        return dataPulsa;
    }
}
