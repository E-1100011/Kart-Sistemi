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

public class HarcaActivity extends AppCompatActivity {
    Button harcaBtn;
    EditText id, miktar, parola;
    veri_kaynagi vk;
    TextView guncelBakiye;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_harca);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tanimlama();
        vk = new veri_kaynagi(this);

        harcaBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(id.getText().toString()) ||
                        TextUtils.isEmpty(miktar.getText().toString()) ||
                        TextUtils.isEmpty(parola.getText().toString())) {
                    Toast.makeText(HarcaActivity.this, "Lütfen tüm boşlukları doldurun", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        vk.open();
                        String[] sonuc =  vk.bakHarca(id.getText().toString(), Float.parseFloat(miktar.getText().toString()), parola.getText().toString());
                        Toast.makeText(HarcaActivity.this, sonuc[0], Toast.LENGTH_SHORT).show();
                        if (sonuc[1] != null) {
                            guncelBakiye.setText("Güncel Bakiye: " + sonuc[1]);
                        }

                    }finally {
                        vk.close();
                    }
                    id.getText().clear();miktar.getText().clear();parola.getText().clear();
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
        harcaBtn = (Button) findViewById(R.id.buttonHarca);
        id = (EditText) findViewById(R.id.editTextHarcaID);
        miktar = (EditText) findViewById(R.id.editTextHarcaMiktar);
        parola = (EditText) findViewById(R.id.editTextPassword);
        guncelBakiye = (TextView) findViewById(R.id.textViewHarGunBakiye);
    }
}