package com.example.kartsistemi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.kartsistemi.R;
import com.example.kartsistemi.veri_kaynagi;

public class BakiyeYukleActivity extends AppCompatActivity {
    Button yukleBtn;
    EditText id, miktar;
    veri_kaynagi vk;
    TextView gunBakiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakiye_yukle);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tanimlama();
        vk = new veri_kaynagi(this);

        yukleBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(id.getText().toString()) ||
                        TextUtils.isEmpty(miktar.getText().toString())) {
                    Toast.makeText(BakiyeYukleActivity.this, "Lütfen tüm boşlukları doldurun", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        vk.open();
                        String[] sonuc = vk.bakYukle(id.getText().toString(), Float.parseFloat(miktar.getText().toString()));
                        Toast.makeText(BakiyeYukleActivity.this, sonuc[0], Toast.LENGTH_SHORT).show();
                        if (sonuc[1] != null) {
                            gunBakiye.setText("Güncel Bakiye: " + sonuc[1]);
                        }
                    }finally {
                        vk.close();
                    }
                    id.getText().clear();miktar.getText().clear();
                }
            }
        });

    }


    @Override
    protected void onPause() {
        vk.close();
        super.onPause();
    }

    @Override
    protected void onResume() {
        vk.open();
        super.onResume();
    }

    public void tanimlama() {
        yukleBtn = (Button) findViewById(R.id.buttonYukle);
        id = (EditText) findViewById(R.id.editTextIDYukle);
        miktar = (EditText) findViewById(R.id.editTextMiktar);
        gunBakiye = (TextView) findViewById(R.id.textViewYukGunBakiye);
    }
}