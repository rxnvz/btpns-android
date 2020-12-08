package com.example.belajar_fcm;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    TextView tokenTV;
    EditText tokenET;
    String token = "";
    Button tokenBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewByIdGroup();
        onClickGroup();
    }

    private void findViewByIdGroup(){
        tokenTV = (TextView) findViewById(R.id.tokenTV);
        tokenET = (EditText) findViewById(R.id.tokenET);
        tokenBtn = (Button) findViewById(R.id.tokenBtn);
    }

    private void loadToken() {
        SharedPreferences sharedPreferences = getSharedPreferences("com.example.belajar_fcm", Context.MODE_PRIVATE);
        token = sharedPreferences.getString("com.example.belajar_fcm.token", "-");
        tokenET.setText(token);
    }

    void onClickGroup() {
        tokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadToken();
            }
        });
    }
}