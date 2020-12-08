package com.example.tugas_berita;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugas_berita.adapter.BeritaAdapter;
import com.example.tugas_berita.model.Berita;
import com.example.tugas_berita.viewmodel.BeritaViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Berita> beritaArr = new ArrayList<>();
    BeritaAdapter newsAd;
    RecyclerView newsRV;
    BeritaViewModel newsVM;
    FloatingActionButton newBtn;
    List<Berita> newsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById() {
        newsRV = findViewById(R.id.nbRV);
        newBtn = findViewById(R.id.newBtn);
    }

    private void initData(){
        if (newsAd == null) {
            newsAd = new BeritaAdapter(MainActivity.this, beritaArr);
            newsRV.setLayoutManager(new LinearLayoutManager(this));
            newsRV.setAdapter(newsAd);
            newsRV.setItemAnimator(new DefaultItemAnimator());
            newsRV.setNestedScrollingEnabled(true);
        } else {
            newsAd.notifyDataSetChanged();
        }

        newsVM = ViewModelProviders.of(this).get(BeritaViewModel.class);
        newsVM.init();
        newsVM.getNewsRepo().observe(this, newsResponse -> {
            List<Berita> newNews =newsResponse.getData();
            beritaArr.addAll(newNews);
            newsAd.notifyDataSetChanged();
        });
    }

    void onClickGroup() {
        newBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), AddActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}