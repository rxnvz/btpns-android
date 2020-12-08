package com.example.tugas_berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tugas_berita.model.Berita;
import com.example.tugas_berita.viewmodel.BeritaViewModel;

public class AddActivity extends AppCompatActivity {
    private String title, category, url;
    private int id;
    private Berita news = new Berita();

    EditText titleET, categoryET, imgET;
    Button saveBtn;
    String mode = "add";
    BeritaViewModel beritaVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById() {
        titleET = findViewById(R.id.titleET);
        categoryET = findViewById(R.id.tagsET);
        imgET = findViewById(R.id.urlET);
        saveBtn = findViewById(R.id.saveBtn);
    }

    void initData() {
        beritaVM = ViewModelProviders.of(this).get(BeritaViewModel.class);
        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("mode", "");
        id = bundle.getInt("id", 0);
        title = bundle.getString("title", "");
        category = bundle.getString("category", "");
        url = bundle.getString("url", "");

        Toast.makeText(getApplicationContext(), title, Toast.LENGTH_SHORT).show();
    }

    void onClickGroup() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita payload = new Berita();
                payload.setTitle(titleET.getText().toString());
                payload.setCategory(categoryET.getText().toString());
                payload.setUrl(imgET.getText().toString());
                postNews(payload);
            }
        });
    }

    private void postNews(Berita payload) {
        beritaVM.newNewsRepo(payload).observe(this, newsResponse -> {
            news =newsResponse.getData();
            finish();
        });
    }
}