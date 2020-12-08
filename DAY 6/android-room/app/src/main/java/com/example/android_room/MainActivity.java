package com.example.android_room;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.example.android_room.adapter.NasabahAdapter;
import com.example.android_room.model.Nasabah;
import com.example.android_room.viewmodel.NasabahViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    public static final int ADD_NASABAH_REQUEST = 1;
    public static final int EDIT_NASABAH_REQUEST = 2;

    private NasabahViewModel nbVM;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton addNewBtn = findViewById(R.id.newBtn);
        addNewBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddEditNasabahActivity.class);
                startActivityForResult(intent, ADD_NASABAH_REQUEST);
            }
        });

        RecyclerView recyclerView = findViewById(R.id.nbRV);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setHasFixedSize(true);

        final NasabahAdapter adapter = new NasabahAdapter();
        recyclerView.setAdapter(adapter);

        nbVM = ViewModelProviders.of(this).get(NasabahViewModel.class);
        nbVM.getAllData().observe(this, new Observer<List<Nasabah>>() {
            @Override
            public void onChanged(List<Nasabah> nasabahs) {
                adapter.submitList(nasabahs);
            }
        });

        new ItemTouchHelper(new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT | ItemTouchHelper.RIGHT) {
            @Override
            public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
                return false;
            }

            @Override
            public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
                nbVM.delete(adapter.getNasabahAt(viewHolder.getAdapterPosition()));
                Toast.makeText(MainActivity.this, "Nasabah berhasil dihapus!", Toast.LENGTH_SHORT).show();
            }
        }).attachToRecyclerView(recyclerView);

        adapter.setOnItemClickListener(new NasabahAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(Nasabah note) {
                Intent intent = new Intent(MainActivity.this, AddEditNasabahActivity.class);
                intent.putExtra(AddEditNasabahActivity.EXTRA_ID, note.getId());
                Log.d("Isi note.getID: ", String.valueOf(note.getId()));
                Log.d("Isi note.getID: ", String.valueOf(note.getId()));
                intent.putExtra(AddEditNasabahActivity.EXTRA_NAMA, note.getNama());
                intent.putExtra(AddEditNasabahActivity.EXTRA_ALAMAT, note.getAlamat());
                intent.putExtra(AddEditNasabahActivity.EXTRA_EMAIL, note.getEmail());
                startActivityForResult(intent, EDIT_NASABAH_REQUEST);
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == ADD_NASABAH_REQUEST && resultCode == RESULT_OK) {
            String nama = data.getStringExtra(AddEditNasabahActivity.EXTRA_NAMA);
            String alamat = data.getStringExtra(AddEditNasabahActivity.EXTRA_ALAMAT);
            String email = data.getStringExtra(AddEditNasabahActivity.EXTRA_EMAIL);

            Nasabah nasabah = new Nasabah(nama, alamat, email);
            nbVM.insert(nasabah);

            Toast.makeText(this, "Nasabah Berhasil disimpan.", Toast.LENGTH_SHORT).show();
        } else  if (requestCode == EDIT_NASABAH_REQUEST && resultCode == RESULT_OK) {

            int id = data.getIntExtra(AddEditNasabahActivity.EXTRA_ID, -1);

            System.out.println("Isi ID: " + id);

            if (id == -1) {
                System.out.println("Isi ID dalam IF: " + id);
                Toast.makeText(this, "Nasabah tidak bisa diubah", Toast.LENGTH_SHORT).show();
                return;
            }

            String nama = data.getStringExtra(AddEditNasabahActivity.EXTRA_NAMA);
            String alamat = data.getStringExtra(AddEditNasabahActivity.EXTRA_ALAMAT);
            String email = data.getStringExtra(AddEditNasabahActivity.EXTRA_EMAIL);

            Nasabah nasabah = new Nasabah(nama, alamat, email);
            nasabah.setId(id);
            nbVM.update(nasabah);

            Toast.makeText(this, "Data Nasabah berhasil dirubah!", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Data tidak tersimpan", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.main_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.dellAll:
                nbVM.deleteAll();
                Toast.makeText(this, "Seluruh nasabah berhasil dihapus.", Toast.LENGTH_SHORT).show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }
}