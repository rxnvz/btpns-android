package com.example.android_datastore.network;

import androidx.lifecycle.MutableLiveData;

import com.example.android_datastore.model.LoginRequest;
import com.example.android_datastore.model.UserResponse;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserRepository {
    public static UserRepository userRepo;
    public static UserRepository getInstance() {
        if (userRepo == null) {
            userRepo = new UserRepository();
        }
        return userRepo;
    }

    private UserAPI api;
    public UserRepository(){
        api =RetrofitService.cteateService(UserAPI.class);
    }

    public MutableLiveData<UserResponse> postLogin(LoginRequest loginRequest) {
        MutableLiveData<UserResponse> userData = new MutableLiveData<>();
        api.postLogin(loginRequest).enqueue(new Callback<UserResponse>() {
            @Override
            public void onResponse(Call<UserResponse> call, Response<UserResponse> response) {
                if (response.isSuccessful()) {
                    userData.setValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<UserResponse> call, Throwable t) {
                userData.setValue(null);
            }
        });
        return userData;
    }
}
