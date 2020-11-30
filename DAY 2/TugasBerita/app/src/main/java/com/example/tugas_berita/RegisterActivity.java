package com.example.tugas_berita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;

import com.example.tugas_berita.adapter.BeritaAdapter;
import com.example.tugas_berita.model.Berita;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class RegisterActivity extends AppCompatActivity implements Serializable {
    private EditText titleET, tagET, urlET;
    private ImageView saveBtn;
    BeritaAdapter beritaAdapter;
    private List<Berita> listBerita = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        listBerita = (List<Berita>) getIntent().getSerializableExtra("newsList");
        findViewById();
        onClickGroup();
//        initData();
    }

    void findViewById() {
        saveBtn = findViewById(R.id.saveBtn);
        titleET = findViewById(R.id.titleET);
        tagET = findViewById(R.id.tagsET);
        urlET = findViewById(R.id.urlET);
    }

    void onClickGroup() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Berita nb = new Berita();
                nb.setJudul(titleET.getText().toString());
                nb.setTags(tagET.getText().toString());
                nb.setPhotoURL(urlET.getText().toString());

                listBerita.add(nb);
//                beritaAdapter.notifyDataSetChanged();
                Intent intent = new Intent(RegisterActivity.this, MainActivity.class);
                intent.putExtra("newsList", (Serializable) listBerita);
                startActivity(intent);
                finish();
            }
        });
    }
}