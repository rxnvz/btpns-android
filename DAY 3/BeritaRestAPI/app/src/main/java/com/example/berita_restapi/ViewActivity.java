package com.example.berita_restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.berita_restapi.model.Berita;
import com.example.berita_restapi.viewmodels.BeritaViewModel;

public class ViewActivity extends AppCompatActivity {
    private String id, judul, tags, url;
    private Berita news = new Berita();

    EditText judulET, tagsET, urlET;
    Button saveBtn;
    String mode = "add";
    BeritaViewModel newsVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById() {
        judulET = (EditText) findViewById(R.id.titleET);
        tagsET = (EditText) findViewById(R.id.tagsET);
        urlET = (EditText) findViewById(R.id.urlET);

        saveBtn = (Button) findViewById(R.id.saveBtn);
    }

    void initData() {
        newsVM = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle =getIntent().getExtras();
        mode = bundle.getString("mode", "");
        id = bundle.getString("id", "");
        judul = bundle.getString("title", "");
        tags = bundle.getString("category", "");
        url = bundle.getString("url", "");

        Toast.makeText(getApplicationContext(), judul, Toast.LENGTH_SHORT).show();
    }

    void onClickGroup() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita payload = new Berita();
                payload.setJudul(judulET.getText().toString());
                payload.setTags(tagsET.getText().toString());
                payload.setPhotoURL(urlET.getText().toString());
                postNews(payload);
            }
        });
    }

    private void postNews(Berita payload) {
        newsVM.postNewsRepo(payload).observe(this, newsResponse -> {
            news =newsResponse.getData();
            finish();
        });
    }
}