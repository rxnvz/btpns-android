package com.example.tugas_berita;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
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

public class MainActivity extends AppCompatActivity {
    private List<Berita> listBerita = new ArrayList();
    BeritaAdapter beritaAdapter;
    ListView newsList;
    private ImageView newNews;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
        dummy();
        List<Berita> newsList = (List<Berita>) getIntent().getSerializableExtra("newsList");
        if (newsList != null) {
            listBerita =  newsList;
            initData();
        }
    }

    void findViewById() {
        newsList = (ListView) findViewById(R.id.newsList);
        newNews = (ImageView) findViewById(R.id.newNews);
    }

    void initData() {
        beritaAdapter = new BeritaAdapter(getApplicationContext(), listBerita);
        newsList.setAdapter(beritaAdapter);
        beritaAdapter.notifyDataSetChanged();
    }

    void onClickGroup() {
        newNews.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                intent.putExtra("newsList", (Serializable) listBerita);
                startActivity(intent);
            }
        });
    }

    void dummy(){
        Berita newBerita = new Berita();
        newBerita.setJudul("GRAMMY'S WINNER: BTS");
        newBerita.setTags("entertaintment");
        newBerita.setPhotoURL("https://sa.kapamilya.com/absnews/abscbnnews/media/2020/entertainment/02/25/202225-bts.jpg");
        listBerita.add(newBerita);
        beritaAdapter.notifyDataSetChanged();
    }

}