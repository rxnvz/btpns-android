package com.example.android_datastore;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android_datastore.model.LoginRequest;
import com.example.android_datastore.model.User;
import com.example.android_datastore.viewmodel.UserViewModel;

public class MainActivity extends AppCompatActivity {
    Button loginBtn, regisBtn;
    EditText emailET, passET;
    UserViewModel userVM;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        userVM = ViewModelProviders.of(this).get(UserViewModel.class);
        userVM.init();
    }

    void findViewById() {
        emailET = findViewById(R.id.emailET);
        passET = findViewById(R.id.pwET);
        loginBtn = findViewById(R.id.loginBtn);
        regisBtn = findViewById(R.id.regisBtn);
    }

    void onClickGroup() {
        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                doLogin(emailET.getText().toString(), passET.getText().toString());
            }
        });

        regisBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, HomeActivity.class);
                intent.putExtra("name","Candra S");
//                startActivity(intent);
                startActivityForResult(intent,2212);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2212) {
            if (resultCode == Activity.RESULT_OK) {
                String selectedImage = data.getExtras().getString("streetkey");
                Toast.makeText(MainActivity.this,selectedImage,Toast.LENGTH_SHORT)
                        .show();
            }
        }
    }

    private void doLogin(String email, String password) {
        userVM.postLogin(new LoginRequest(email, password)).observe(this, userResponse -> {
            user = userResponse.getData();
            SharedPreferences preferences = getSharedPreferences("com.example.android_datastore", Context.MODE_PRIVATE);

            SharedPreferences.Editor editor = preferences.edit();
            editor.putString("com.example.android_datastore.token", user.getToken());
            editor.apply();

            Intent intent = new Intent(MainActivity.this, HomeActivity.class);
            startActivity(intent);
        });
    }
}