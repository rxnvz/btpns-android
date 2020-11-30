package com.example.android_bundledata;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class PreviewActivity extends AppCompatActivity {
    private EditText namaEditText, emailEditText, passwordEditText, photoEditText;
    private TextView exitTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_preview);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById() {
        namaEditText = (EditText) findViewById(R.id.namaEditText);
        emailEditText = (EditText) findViewById(R.id.emailEditText);
        passwordEditText = (EditText) findViewById(R.id.passwordEditText);
        photoEditText = (EditText) findViewById(R.id.photoEditText);
        exitTextView = (TextView) findViewById(R.id.exitTextView);
    }

    void initData() {
        Bundle bundle = getIntent().getExtras();
        namaEditText.setText(bundle.getString("nama"));
        emailEditText.setText(bundle.getString("email"));
        passwordEditText.setText(bundle.getString("password"));
        photoEditText.setText(bundle.getString("photoURL"));
    }

    void onClickGroup() {
        exitTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
}