package com.example.tugas_pulsa.network;

import com.example.tugas_pulsa.model.ProductResponse;
import com.example.tugas_pulsa.model.Pulsa;
import com.example.tugas_pulsa.model.PulsaResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface PulsaAPI {
    @GET("product")
    Call<ProductResponse> getPulsa();

    @POST("pulsa")
    Call<PulsaResponse> buyPulsa(@Body Pulsa body);
}
