package com.example.material_bottomnavigation;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Switch;

import com.google.android.material.badge.BadgeDrawable;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {
    Menu optionsMenu;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bttmNavi = (BottomNavigationView) findViewById(R.id.bottomNav);
        bttmNavi.setBackground(null);
        bttmNavi.setSelectedItemId(R.id.bintang);
        BadgeDrawable badge = bttmNavi.getOrCreateBadge(R.id.bintang);
        badge.setNumber(9999999);
        badge.setVisible(true);

        bttmNavi.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.bintang:
                        Log.d("Bintang", "Clicked");
                        return true;

                    case R.id.star:
                        Log.d("Star", "Clicked");
                        return true;

                    case R.id.estella:
                        Log.d("Estella", "Clicked");
                        return true;

                    case R.id.byeol:
                        Log.d("별", "Clicked");
                        return true;
                }
                return true;
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.bottom_navigation_menu, menu);
        optionsMenu = menu;
        return true;
    }
}