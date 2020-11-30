package com.example.android_adapter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;

import com.example.android_adapter.adapter.NasabahAdapter;
import com.example.android_adapter.model.Nasabah;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ListView nasabahLV;
    TextView refreshTV;
    NasabahAdapter nasabahAdapter;
    private List<Nasabah> listNasabah = new ArrayList();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById();
        onClickGroup();
        initData();
    }

    void findViewById() {
        nasabahLV = (ListView) findViewById(R.id.nbList);
        refreshTV = (TextView) findViewById(R.id.refreshTV);
    }

    void onClickGroup() {
        refreshTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nasabah newNB = new Nasabah();
                newNB.setNama("Wati");
                newNB.setAlamat("Jakarta Selatan");
                newNB.setSaldo(new Double(250000));
                listNasabah.add(newNB);

                newNB = new Nasabah();
                newNB.setNama("Budi");
                newNB.setAlamat("Jakarta Timur");
                newNB.setSaldo(new Double(150000));
                listNasabah.add(newNB);

                nasabahAdapter.notifyDataSetChanged();
            }
        });
    }

        void initData() {
            nasabahAdapter = new NasabahAdapter(getApplicationContext(), listNasabah);
            nasabahLV.setAdapter(nasabahAdapter);
            nasabahAdapter.notifyDataSetChanged();
        }
}