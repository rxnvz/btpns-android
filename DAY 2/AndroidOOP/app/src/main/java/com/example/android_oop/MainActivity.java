package com.example.android_oop;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.example.android_oop.model.Nasabah;

import org.w3c.dom.Text;

public class MainActivity extends AppCompatActivity {
    private Nasabah nasabah;
    private EditText namaET, alamatET, passET, tokenET, saldoET;
    private TextView namaTV, alamatTV, passTV, tokenTV, saldoTV, statusTV;
    private TextView refreshTV;
    private Button namaBtn, alamatBtn, passBtn, tokenBtn, saldoBtn, resetBtn, promoBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nasabah = new Nasabah();
        findViewById();
        onClickGroup();
        refreshTV.callOnClick();
    }

    void findViewById() {
        namaET = (EditText) findViewById(R.id.namaET);
        alamatET = (EditText) findViewById(R.id.alamatET);
        passET = (EditText) findViewById(R.id.passET);
        tokenET = (EditText) findViewById(R.id.tokenET);
        saldoET = (EditText) findViewById(R.id.saldoET);

        namaTV = (TextView) findViewById(R.id.namaTV);
        alamatTV = (TextView) findViewById(R.id.alamatTV);
        passTV = (TextView) findViewById(R.id.passTV);
        tokenTV = (TextView) findViewById(R.id.tokenTV);
        saldoTV = (TextView) findViewById(R.id.saldoTV);
        statusTV = (TextView) findViewById(R.id.aktifTV);

        namaBtn = (Button) findViewById(R.id.setNamaBtn);
        alamatBtn = (Button) findViewById(R.id.setAlamatBtn);
        passBtn = (Button) findViewById(R.id.setPassBtn);
        tokenBtn = (Button) findViewById(R.id.setTokenBtn);
        saldoBtn = (Button) findViewById(R.id.setSaldoBtn);

        refreshTV = (TextView) findViewById(R.id.refreshTV);
        resetBtn = (Button) findViewById(R.id.resetPassBtn);
        promoBtn = (Button) findViewById(R.id.promoBtn);
    }

    void onClickGroup() {
        refreshTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                namaTV.setText("Nama: " + nasabah.getNama());
                alamatTV.setText("Alamat: " + nasabah.getAlamat());
                passTV.setText("Password: " + nasabah.getPassword());
                tokenTV.setText("Token: " + nasabah.getToken());
                saldoTV.setText("Saldo: " + nasabah.getSaldo().toString());
                statusTV.setText("Aktif: " + nasabah.getAktif().toString());
            }
        });

        resetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setPassword("1234");
            }
        });

        promoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setSaldo(nasabah.getSaldo() + new Double(100000));
            }
        });

        namaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setNama(namaET.getText().toString());
            }
        });

        alamatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setAlamat(alamatET.getText().toString());
            }
        });

        passBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setPassword(passET.getText().toString());
            }
        });

        tokenBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setToken(tokenET.getText().toString());
            }
        });

        saldoBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                nasabah.setSaldo(new Double((saldoET.getText().toString())));
            }
        });
    }
}