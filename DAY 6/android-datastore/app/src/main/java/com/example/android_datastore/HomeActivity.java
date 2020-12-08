package com.example.android_datastore;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class HomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        SharedPreferences preferences = getSharedPreferences("com.example.android_datastore", Context.MODE_PRIVATE);
        String token = preferences.getString("com.example.android_datastore.token", "-");
        Log.d("Token", token);
        setTitle(token);
    }
}