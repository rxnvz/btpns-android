package com.example.androidimplicit;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    Button urlButton, browseButton;
    EditText urlEditText, fileEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
    }

    void findViewById() {
        urlEditText = findViewById(R.id.urlEditText);
        fileEditText = findViewById(R.id.fileEditText);
        urlButton = findViewById(R.id.urlButton);
        browseButton = findViewById(R.id.browseButton);
    }

    void onClickGroup() {
        urlButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String url = urlEditText.getText().toString();
                Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(url));
                startActivity(browserIntent);
            }
        });

        browseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent IntentImage = new Intent();
                IntentImage.setType("image/*");

                IntentImage.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(IntentImage, 2212);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 2212) {
            if (resultCode == Activity.RESULT_OK) {
                Uri selectedImage = data.getData();
                fileEditText.setText(selectedImage.toString());
            }
        }
    }
}