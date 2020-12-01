package com.example.android_crud;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_crud.adapter.NasabahAdapter;
import com.example.android_crud.model.Nasabah;
import com.example.android_crud.viewmodels.NasabahViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    ArrayList<Nasabah> nasabahArrayList = new ArrayList<>();
    NasabahAdapter nbAdapter;
    RecyclerView rvNasabah;
    NasabahViewModel nbVM;
    TextView refreshTV, addTV;
    List<Nasabah> nasabahs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListNasabah("1","20");
    }


    void findViewById() {
        rvNasabah = findViewById(R.id.nasabahRV);
        refreshTV = (TextView) findViewById(R.id.refreshTV);
        addTV = (TextView) findViewById(R.id.addTV);
    }

    private void initData() {
        nbVM = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nbVM.init();
        nbVM.getNasabahRepo().observe(this, nasabahResponse -> {
            List<Nasabah> nbs = nasabahResponse.getData();
            nasabahArrayList.addAll(nbs);
            nbAdapter.notifyDataSetChanged();
        });

        if (nbAdapter == null) {
            nbAdapter = new NasabahAdapter(MainActivity.this, nasabahArrayList);
            rvNasabah.setLayoutManager(new LinearLayoutManager(this));
            rvNasabah.setAdapter(nbAdapter);
            rvNasabah.setItemAnimator(new DefaultItemAnimator());
            rvNasabah.setNestedScrollingEnabled(true);
        } else {
            nbAdapter.notifyDataSetChanged();
        }
    }

    public void getListNasabah(String page, String limit) {
        nbVM.refresh(page, limit);
        nbVM.getNasabahRepo().observe(this, nasabahResponse -> {
            nasabahs = nasabahResponse.getData();
            nasabahArrayList.clear();
            nasabahArrayList.addAll(nasabahs);
            nbAdapter.notifyDataSetChanged();
        });
    }

    void onClickGroup() {
        refreshTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getListNasabah("1", "10");
            }
        });

        addTV.setOnClickListener(new View.OnClickListener() {
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
}