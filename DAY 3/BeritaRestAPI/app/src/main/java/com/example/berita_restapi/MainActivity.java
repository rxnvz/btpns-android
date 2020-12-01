package com.example.berita_restapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.TextView;

import com.example.berita_restapi.adapter.BeritaAdapter;
import com.example.berita_restapi.model.Berita;
import com.example.berita_restapi.viewmodels.BeritaViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Berita> beritaArr = new ArrayList<>();
    BeritaAdapter newsAd;
    RecyclerView newsRV;
    BeritaViewModel newsVM;
    TextView refreshTV, newTV;
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
        newsRV = findViewById(R.id.newsRV);
        refreshTV = (TextView) findViewById(R.id.refreshTV);
        newTV = (TextView) findViewById(R.id.addTV);
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

    public void getListBerita(String page, String limit){
        newsVM.refresh(page, limit);
        newsVM.getNewsRepo().observe(this, newsResponse -> {
            newsList = newsResponse.getData();
            beritaArr.clear();
            beritaArr.addAll(newsList);
            newsAd.notifyDataSetChanged();
        });
    }

    void onClickGroup() {
        refreshTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListBerita("1", "20");
            }
        });

        newTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), ViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("mode", "add");
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListBerita("1","20");
    }
}