package com.example.android_bundledata;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private EditText namaEditText, emailEditText, passwordEditText, photoURLEditText;
    private TextView submitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
    }

    void findViewById() {
        namaEditText = findViewById(R.id.namaEditText);
        emailEditText = findViewById(R.id.emailEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        photoURLEditText = findViewById(R.id.photoEditText);
        submitTextView = findViewById(R.id.submitTextView);
    }

    void onClickGroup() {
        submitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, PreviewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("nama", namaEditText.getText().toString());
                bundle.putString("email", emailEditText.getText().toString());
                bundle.putString("password", passwordEditText.getText().toString());
                bundle.putString("photoURL", photoURLEditText.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
                finish();
            }
        });
    }
}