package com.example.android_butterknife;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.android_butterknife.adapter.NasabahAdapter;
import com.example.android_butterknife.model.Nasabah;
import com.example.android_butterknife.viewmodel.NasabahViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {
    ArrayList<Nasabah> nbArr = new ArrayList<>();
    List<Nasabah> nbList;

    @BindView(R.id.nbRV) RecyclerView nbRV;
    @BindView(R.id.refreshTV) TextView refreshTV;

    NasabahAdapter nbAdapter;
    NasabahViewModel nbVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
        if (nbAdapter == null) {
            nbAdapter = new NasabahAdapter(MainActivity.this, nbArr);
            nbRV.setLayoutManager(new LinearLayoutManager(this));
            nbRV.setAdapter(nbAdapter);
            nbRV.setNestedScrollingEnabled(true);
        } else {
            nbAdapter.notifyDataSetChanged();
        }

        nbVM = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nbVM.init();
        nbVM.getAllRepo().observe(this, nbResponse -> {
            nbList = nbResponse.getData();
            nbArr.clear();
            nbArr.addAll(nbList);
            nbAdapter.notifyDataSetChanged();
        });
    }

    public void getListNasabah(String page, String limit) {
        nbVM.refresh(page, limit);
        nbVM.getAllRepo().observe(this, nbResponse -> {
            nbList = nbResponse.getData();
            nbArr.clear();
            nbArr.addAll(nbList);
            nbAdapter.notifyDataSetChanged();
        });
    }

    @OnClick(R.id.refreshTV)
    public void submit(View view) {
        getListNasabah("1", "20");
    }

    @Override
    protected void onResume() {
        super.onResume();
        getListNasabah("1", "20");
    }
}