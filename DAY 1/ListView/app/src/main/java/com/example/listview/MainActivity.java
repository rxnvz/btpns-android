package com.example.listview;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView textViewSelected;
    ListView listView;
    ArrayAdapter adapterKota;
    String[] listKota = { "Jakarta Selatan", "Jakarta Barat", "Jakarta Utara",
                        "Medan", "Bandung", "Surabaya", "Semarang", "Bekasi",
                        "Jogjakarta", "Denpasar"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textViewSelected = findViewById(R.id.selectedTextView);
        listView = (ListView) findViewById(R.id.kotaListView);

        adapterKota = new ArrayAdapter(getApplicationContext(), R.layout.item_row, listKota);
        listView.setAdapter(adapterKota);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(getApplicationContext(), String.valueOf(position+1),
                        Toast.LENGTH_SHORT).show();
                textViewSelected.setText("Kota: " + listKota[position]);
            }
        });
    }
}