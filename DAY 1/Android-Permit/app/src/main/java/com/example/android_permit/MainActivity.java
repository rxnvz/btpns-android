package com.example.android_permit;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.annotation.TargetApi;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    TextView file_tv;
    Button browse_button;
    ImageView file_iv;
    int REQUEST_CODE_PATTERN = 276;
    int REQUEST_CODE_FILE = 277;
    private Object permissions;
    private int REQUEST_CODE_PERMITTION;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        initClick();
        if(shouldAskPermissions()) {
            askPermissions();
        }
    }

    private void initView(){
        file_tv = (TextView) findViewById(R.id.file_tv);
        file_iv = (ImageView) findViewById(R.id.file_iv);;
        browse_button = (Button) findViewById(R.id.browse_button);
    }

    private void initClick(){
        browse_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Button clicked", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
                intent.setType("image/*");
                startActivityForResult(intent, REQUEST_CODE_FILE);
            }
        });
    }

    protected boolean shouldAskPermissions(){
        return(Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }

    @TargetApi(23)
    protected void askPermissions(){
        String[] permissions = {
                "android.permission.READ_EXTERNAL_STORAGE",
        };
        ActivityCompat.requestPermissions(MainActivity.this,permissions,REQUEST_CODE_PERMITTION);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        try {
            super.onActivityResult(requestCode, resultCode, data);

            if(requestCode == REQUEST_CODE_FILE && resultCode == RESULT_OK){
                file_tv.setText(data.getData().toString());
                file_iv.setImageURI(data.getData());
            }

        } catch (Exception e) {
            Toast.makeText(getApplicationContext(), e.toString(),
                    Toast.LENGTH_SHORT).show();
        }
    }

}