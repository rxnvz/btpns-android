package com.example.belajar_fcm;

import androidx.appcompat.app.AppCompatActivity;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

public class MessageActivity extends AppCompatActivity {
    EditText titleET, msgET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_message);
        findViewByIdGroup();
        loadData();
    }

    private void findViewByIdGroup(){
        titleET = (EditText) findViewById(R.id.titleET);
        msgET = (EditText) findViewById(R.id.msgET);
    }

    private void loadData(){
        Bundle bundle = getIntent().getExtras();
        titleET.setText(bundle.getString("title"));
        msgET.setText(bundle.getString("message"));
    }
}