package com.example.android_datastore.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.android_datastore.model.LoginRequest;
import com.example.android_datastore.model.UserResponse;
import com.example.android_datastore.network.UserRepository;

public class UserViewModel extends ViewModel {
    private UserRepository userRepo;
    private MutableLiveData<UserResponse> userLiveData;

    public void init() {
        if (userLiveData != null) {
            return;
        }
        userRepo = UserRepository.getInstance();
    }

    public LiveData<UserResponse> postLogin(LoginRequest loginRequest){
        if (userLiveData == null) {
            userRepo = UserRepository.getInstance();
        }
        userLiveData = userRepo.postLogin(loginRequest);
        return userLiveData;
    }
}
