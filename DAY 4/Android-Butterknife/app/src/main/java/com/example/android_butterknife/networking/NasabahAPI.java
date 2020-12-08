package com.example.android_butterknife.networking;

import com.example.android_butterknife.model.NasabahResponse;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface NasabahAPI {
    @GET("nasabah")
    Call<NasabahResponse> getAllNasabah(@Query("page") String page,
                                        @Query("limit") String limit);
}
