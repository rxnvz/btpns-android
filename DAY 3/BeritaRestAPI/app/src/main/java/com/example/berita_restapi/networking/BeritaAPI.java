package com.example.berita_restapi.networking;

import com.example.berita_restapi.model.Berita;
import com.example.berita_restapi.model.BeritaResponse;
import com.example.berita_restapi.model.SingleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface BeritaAPI {

    @GET("berita")
    Call<BeritaResponse> getNewsList (@Query("page") String page,
                                      @Query("limit") String limit);

    @POST("berita")
    Call<SingleResponse> postNews (@Body Berita body);
}
