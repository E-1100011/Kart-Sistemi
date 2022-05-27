package com.example.kartsistemi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.kartsistemi.R;
import com.example.kartsistemi.kart;
import com.example.kartsistemi.veri_kaynagi;

public class KartEkleActivity extends AppCompatActivity {
    EditText id, isim, parola;
    Button ekle;
    veri_kaynagi vk;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_kart_ekle);
        tanımlama();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        vk = new veri_kaynagi(this);

        ekle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(id.getText().toString()) ||
                        TextUtils.isEmpty(isim.getText().toString()) ||
                        TextUtils.isEmpty(parola.getText().toString())) {
                    Toast.makeText(KartEkleActivity.this, "Lütfen tüm bilgileri doldurun", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        vk.open();
                        kart k = new kart(Integer.parseInt(id.getText().toString()), isim.getText().toString(), parola.getText().toString());
                        String sonuc = vk.kartEkle(k);
                        Toast.makeText(KartEkleActivity.this, sonuc, Toast.LENGTH_SHORT).show();
                    }finally {
                        vk.close();
                    }
                    id.getText().clear();isim.getText().clear();parola.getText().clear();
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

    private void tanımlama() {
        id = (EditText) findViewById(R.id.editTextKartID);
        isim = (EditText) findViewById(R.id.editTextİsim);
        parola = (EditText) findViewById(R.id.editTextPass);
        ekle = (Button) findViewById(R.id.buttonEkle);
    }
}