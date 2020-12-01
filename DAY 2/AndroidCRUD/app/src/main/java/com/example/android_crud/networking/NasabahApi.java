package com.example.android_crud.networking;

import com.example.android_crud.model.Nasabah;
import com.example.android_crud.model.NasabahResponse;
import com.example.android_crud.model.SingleResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface NasabahApi {
    @GET("nasabah")
    Call<NasabahResponse> getNasabahList (@Query("page") String page,
                                          @Query("limit") String limit);

//    @FormUrlEncoded
    @POST("nasabah")
    Call<SingleResponse> postNasabah(@Body Nasabah body);

    @GET("nasabah/{id}")
    Call<SingleResponse> getNasabah(@Path("id") String id);

//    @FormUrlEncoded
    @PUT("nasabah/{id}")
    Call<SingleResponse> putNasabah(@Path("id") String id,
                                    @Body Nasabah body);

    @DELETE("nasabah/{id}")
    Call<SingleResponse> deletNasabah(@Path("id") String id);
}
