package com.example.tugas_berita.network;

import com.example.tugas_berita.model.Berita;
import com.example.tugas_berita.model.BeritaResponse;
import com.example.tugas_berita.model.SingleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface API {
    @GET("berita")
    Call<BeritaResponse> getAll (@Query("page") String page,
                                 @Query("limit") String limit);

    @POST("berita")
    Call<SingleResponse> postNews(@Body Berita body);
}
