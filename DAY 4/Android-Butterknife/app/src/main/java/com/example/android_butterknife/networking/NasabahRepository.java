package com.example.android_butterknife.networking;

import androidx.lifecycle.MutableLiveData;

import com.example.android_butterknife.model.NasabahResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class NasabahRepository {
    private static NasabahRepository nbsRepo;
    private NasabahAPI nbAPI;

    public static NasabahRepository getInstance() {
        if (nbsRepo == null) {
            nbsRepo = new NasabahRepository();
        }
        return nbsRepo;
    }

    public NasabahRepository() {
        nbAPI = RetrofitService.createService(NasabahAPI.class);
    }

    public MutableLiveData<NasabahResponse> getAllNasabah (String page, String limit) {
        MutableLiveData<NasabahResponse> nbData = new MutableLiveData<>();
        nbAPI.getAllNasabah(page, limit).enqueue(new Callback<NasabahResponse>() {
            @Override
            public void onResponse(Call<NasabahResponse> call, Response<NasabahResponse> response) {
                if (response.isSuccessful()) {
                    nbData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NasabahResponse> call, Throwable t) {
                nbData.setValue(null);
            }
        });
        return nbData;
    }
}
