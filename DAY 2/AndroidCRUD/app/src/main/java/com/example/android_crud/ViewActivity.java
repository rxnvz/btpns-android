package com.example.android_crud;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import com.example.android_crud.model.Nasabah;
import com.example.android_crud.viewmodels.NasabahViewModel;

public class ViewActivity extends AppCompatActivity {
    private String id, nama, alamat, email;
    private Nasabah nb = new Nasabah();

    EditText namaET, alamatET, emailET;
    Button saveBtn, delBtn;
    String mode = "add";
    NasabahViewModel nasabahVM;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        findViewById();
        initData();
        onClickGroup();
    }

    void findViewById() {
        namaET = (EditText) findViewById(R.id.namaET);
        alamatET = (EditText) findViewById(R.id.alamatET);
        emailET = (EditText) findViewById(R.id.emailET);

        saveBtn = (Button) findViewById(R.id.updateBtn);
        delBtn = (Button) findViewById(R.id.deleteBtn);
    }

    void initData() {
        nasabahVM = ViewModelProviders.of(this).get(NasabahViewModel.class);
        Bundle bundle = getIntent().getExtras();
        mode = bundle.getString("mode", "add");
        id = bundle.getString("id", "");
        nama = bundle.getString("nama", "");
        alamat = bundle.getString("alamat", "");
        email = bundle.getString("email", "");

        Toast.makeText(getApplicationContext(), nama, Toast.LENGTH_SHORT).show();

        if (mode.equals("edit")) {
            getNasabah(id);
        }
    }

    void onClickGroup() {
        saveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Nasabah nasabahPayload = new Nasabah();
                nasabahPayload.setNama(namaET.getText().toString());
                nasabahPayload.setAlamat(alamatET.getText().toString());
                nasabahPayload.setEmail(emailET.getText().toString());
                if (mode.equals("edit")) {
                    putNasabah(id, nasabahPayload);
                } else {
                    postNasabah(nasabahPayload);
                }
            }
        });
        delBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                deleteNasabah(id);
                finish();
            }
        });
    }

    private void getNasabah(String id) {
        nasabahVM.getSingleRepo(id).observe(this, nasabahResponse -> {
            nb = nasabahResponse.getData();
            loadData();
        });
    }

    void loadData() {
        namaET.setText(nb.getNama());
        alamatET.setText(nb.getAlamat());
        emailET.setText(nb.getEmail());
    }

    private void putNasabah(String id, Nasabah nasabahPayload) {
        nasabahVM.putNasabahRepo(id, nasabahPayload).observe(this, nasabahResponse -> {
            nb = nasabahResponse.getData();
            finish();
        });
    }

    private void postNasabah(Nasabah nasabahPayload) {
        nasabahVM.postNasabahRepo(nasabahPayload).observe(this, nasabahResponse -> {
            nb = nasabahResponse.getData();
            finish();
        });
    }

    private void deleteNasabah(String id) {
        nasabahVM.deleteNasabahRepo(id).observe(this, nasabahResponse -> {
            nb = nasabahResponse.getData();
            finish();
        });
    }
}
