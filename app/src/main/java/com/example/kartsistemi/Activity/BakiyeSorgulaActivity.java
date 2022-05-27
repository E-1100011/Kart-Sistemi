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
import com.example.kartsistemi.kart;
import com.example.kartsistemi.veri_kaynagi;

public class BakiyeSorgulaActivity extends AppCompatActivity {

    Button sorgula;
    EditText ID;
    String IDs;
    TextView bakiye;
    veri_kaynagi vk;
    kart k;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bakiye_sorgula);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        tanimlama();
        vk = new veri_kaynagi(this);

        sorgula.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                IDs = ID.getText().toString();
                if (TextUtils.isEmpty(IDs)) {
                    Toast.makeText(BakiyeSorgulaActivity.this, "Lütfen bir ID girin", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    try {
                        vk.open();
                        k = vk.sorgula(IDs);
                        if(k == null) {
                            bakiye.setText("");
                            Toast.makeText(BakiyeSorgulaActivity.this, "Lütfen girilen ID'yi kontrol edin", Toast.LENGTH_SHORT).show();
                        } else {
                            bakiye.setText("İsim Soyisim: "+ k.getIsim() +"\nBakiye: "+ k.getBakiye());
                        }
                    }finally {
                        vk.close();
                    }
                    ID.getText().clear();
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
        sorgula = (Button) findViewById(R.id.sorgula);
        ID = (EditText) findViewById(R.id.editTextIDSorgu);
        bakiye = (TextView) findViewById(R.id.textViewBakiye);
    }
}