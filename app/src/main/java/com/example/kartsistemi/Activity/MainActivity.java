package com.example.kartsistemi.Activity;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.kartsistemi.R;

public class MainActivity extends AppCompatActivity {
    Button kartEkle, bakiyeYuk, bakiyeSor, harca;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tanimlama();

        kartEkle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent kartEkleAct = new Intent(getApplicationContext(), KartEkleActivity.class);
                startActivity(kartEkleAct);
            }
        });
        bakiyeYuk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bakiyeYukAct = new Intent(getApplicationContext(), BakiyeYukleActivity.class);
                startActivity(bakiyeYukAct);
            }
        });
        bakiyeSor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent bakiyeSorAct = new Intent(getApplicationContext(), BakiyeSorgulaActivity.class);
                startActivity(bakiyeSorAct);
            }
        });
        harca.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent harcaAct = new Intent(getApplicationContext(), HarcaActivity.class);
                startActivity(harcaAct);
            }
        });

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_about) {

            Intent hakkindaAct = new Intent(getApplicationContext(), HakkindaActivity.class);
            startActivity(hakkindaAct);

            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    public void tanimlama() {
        kartEkle = (Button) findViewById(R.id.kartEkle);
        bakiyeYuk = (Button) findViewById(R.id.bakiyeYuk);
        bakiyeSor = (Button) findViewById(R.id.bakiyeSor);
        harca = (Button) findViewById(R.id.harca);
    }
}