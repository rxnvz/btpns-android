package com.example.android_room;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.Toast;

public class AddEditNasabahActivity extends AppCompatActivity {

    public static final String EXTRA_ID = "com.example.android_room.EXTRA_ID";
    public static final String EXTRA_NAMA = "com.example.android_room.EXTRA_NAMA";
    public static final String EXTRA_ALAMAT = "com.example.android_room.EXTRA_ALAMAT";
    public static final String EXTRA_EMAIL = "com.example.android_room.EXTRA_EMAIL";

    private EditText namaET;
    private EditText alamatET;
    private EditText emailET;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_edit_nasabah);

        namaET = findViewById(R.id.namaET);
        alamatET = findViewById(R.id.alamatET);
        emailET = findViewById(R.id.emailET);

        getSupportActionBar().setHomeAsUpIndicator(R.drawable.ic_close);

        Intent intent = getIntent();
        if (intent.hasExtra(EXTRA_ID)) {
            setTitle("Edit Data Nasabah");
            namaET.setText(intent.getStringExtra(EXTRA_NAMA));
            alamatET.setText(intent.getStringExtra(EXTRA_ALAMAT));
            emailET.setText(intent.getStringExtra(EXTRA_EMAIL));
        } else {
            setTitle("Tambah Nasabah Baru");
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.add_nasabah_menu, menu);
        return true;
    }

    private void saveNB() {
        String nama = namaET.getText().toString();
        String alamat = alamatET.getText().toString();
        String email = emailET.getText().toString();

        if (nama.trim().isEmpty() || alamat.trim().isEmpty()) {
            Toast.makeText(this, "Lengkapi data anda!", Toast.LENGTH_SHORT).show();
            return;
        }

        Intent data = new Intent();
        data.putExtra(EXTRA_NAMA, nama);
        data.putExtra(EXTRA_ALAMAT, alamat);
        data.putExtra(EXTRA_EMAIL, email);

        int id = getIntent().getIntExtra(EXTRA_ID, -1);
        Log.d("ID", String.valueOf(id));
        if (id != -1) {
            Log.d("Kirim extraID", String.valueOf(id));
            data.putExtra(EXTRA_ID, id);
        }

        setResult(RESULT_OK, data);
        finish();
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.save:
                saveNB();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}