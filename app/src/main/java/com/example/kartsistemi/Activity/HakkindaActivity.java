package com.example.kartsistemi.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import com.example.kartsistemi.R;

public class HakkindaActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hakkinda);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

    }
}