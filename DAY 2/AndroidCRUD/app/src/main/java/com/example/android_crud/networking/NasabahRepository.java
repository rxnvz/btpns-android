package com.example.android_crud.networking;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;

import com.example.android_crud.model.Nasabah;
import com.example.android_crud.model.NasabahResponse;
import com.example.android_crud.model.SingleResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class NasabahRepository {
    public static NasabahRepository nbsRepo;

    public static NasabahRepository getInstance() {
        if (nbsRepo == null) {
            nbsRepo = new NasabahRepository();
        }
        return nbsRepo;
    }

    private NasabahApi nasabahApi;

    public NasabahRepository() {
        nasabahApi = RetrofitService.createService(NasabahApi.class);
    }

    public MutableLiveData<NasabahResponse> getNasabah(String page, String limit) {
        final MutableLiveData<NasabahResponse> nasabahData = new MutableLiveData<>();
        nasabahApi.getNasabahList(page, limit).enqueue(new Callback<NasabahResponse>() {
            @Override
            public void onResponse(Call<NasabahResponse> call, Response<NasabahResponse>response) {
                if (response.isSuccessful()) {
                    Log.d("Halo, berhasil!", response.body().toString());
                    nasabahData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<NasabahResponse> call, Throwable t) {
                nasabahData.setValue(null);
            }
        });
        return nasabahData;
    }

    public MutableLiveData<SingleResponse> getSingleNasabah(String id) {
        MutableLiveData<SingleResponse> nbData = new MutableLiveData<>();
        nasabahApi.getNasabah(id).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                if (response.isSuccessful()) {
                    nbData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                nbData.setValue(null);
            }
        });
        return nbData;
    }

    public MutableLiveData<SingleResponse> updateNasabah(String id, Nasabah nasabahPayload) {
        MutableLiveData<SingleResponse> nbData = new MutableLiveData<>();
        nasabahApi.putNasabah(id, nasabahPayload).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                nbData.setValue(response.body());
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                nbData.setValue(null);
            }
        });
        return nbData;
    }

    public MutableLiveData<SingleResponse> postNasabah (Nasabah nasabahPayload) {
        MutableLiveData<SingleResponse> nbData = new MutableLiveData<>();
        nasabahApi.postNasabah(nasabahPayload).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                if (response.isSuccessful()) {
                    nbData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                nbData.setValue(null);
            }
        });
        return nbData;
    }

    public MutableLiveData<SingleResponse> deleteNasabah(String id) {
        MutableLiveData<SingleResponse> nbData = new MutableLiveData<>();
        nasabahApi.deletNasabah(id).enqueue(new Callback<SingleResponse>() {
            @Override
            public void onResponse(Call<SingleResponse> call, Response<SingleResponse> response) {
                if (response.isSuccessful()){
                    nbData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<SingleResponse> call, Throwable t) {
                nbData.setValue(null);
            }
        });
        return nbData;
    }
}
