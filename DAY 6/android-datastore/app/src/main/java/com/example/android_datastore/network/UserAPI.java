package com.example.android_datastore.network;

import com.example.android_datastore.model.LoginRequest;
import com.example.android_datastore.model.UserResponse;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserAPI {
    @POST("user/login")
    Call<UserResponse> postLogin(@Body LoginRequest body);
}
