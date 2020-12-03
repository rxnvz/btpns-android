package com.example.tugas_pulsa;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_pulsa.adapter.PulsaAdapter;
import com.example.tugas_pulsa.model.Product;
import com.example.tugas_pulsa.model.Pulsa;
import com.example.tugas_pulsa.viewmodel.PulsaViewModel;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    ArrayList<Product> prodArr = new ArrayList<>();
    List<Product> prodList;
    private Pulsa puls = new Pulsa();
    PulsaAdapter pulsaAdapter;
    PulsaViewModel pulsaVM;

    @BindView(R.id.pulsaRV)
    RecyclerView pulsaRV;

    @BindView(R.id.telpET)
    EditText telpET;

    @BindView(R.id.hargaTV)
    TextView hargaTV;

//    @BindView(R.id.pulsaCV) CardView pulsaCV;
    @BindView(R.id.bayarPulsa)LinearLayout bayarPopUp;

    private boolean showContent = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initData();
    }

    public void initData() {
        if (pulsaAdapter == null) {
            pulsaAdapter = new PulsaAdapter(MainActivity.this, prodArr, hargaTV, bayarPopUp);
            pulsaRV.setLayoutManager(new GridLayoutManager(this, 2));
            pulsaRV.setAdapter(pulsaAdapter);
            pulsaRV.setItemAnimator(new DefaultItemAnimator());
            pulsaRV.setNestedScrollingEnabled(true);
        } else {
            pulsaAdapter.notifyDataSetChanged();
        }

        pulsaVM = ViewModelProviders.of(this).get(PulsaViewModel.class);
        pulsaVM.init();
        pulsaVM.getProductRepo().observe(this, pulsaResp -> {
            List<Product> prod = pulsaResp.getData();
            prodArr.addAll(prod);
            pulsaAdapter.notifyDataSetChanged();
        });
    }

    public void getListProduct() {
        pulsaVM.refresh();
        pulsaVM.getProductRepo().observe(this, pulsaResponse -> {
            prodList = pulsaResponse.getData();
            prodArr.clear();
            prodArr.addAll(prodList);
            pulsaAdapter.notifyDataSetChanged();
        });
    }

    @OnClick(R.id.beliBtn)
    public void bayar() {
        Pulsa payload = new Pulsa();
        payload.setNum(telpET.getText().toString());

        pulsaVM.postPulsa(payload).observe(this, pulsaResponse -> {
            puls = pulsaResponse.getData();
            Toast.makeText(getApplicationContext(), "Pembelian Sukses!", Toast.LENGTH_SHORT).show();
        });
    }

//    @OnClick(R.id.pulsaCV)
//    public void showRincian() {
//        if (showContent) {
//            bayarPopUp.setVisibility(View.VISIBLE);
//            showContent = false;
//        } else {
//            bayarPopUp.setVisibility(View.INVISIBLE);
//            showContent = true;
//        }
//    }
}