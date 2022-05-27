package com.example.kartsistemi;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class sqlite_katmani extends SQLiteOpenHelper {
    private static final String VERİ_TABANI_ADI = "veritabani_kart";
    private static int VERİTABANI_SURUMU = 1;
    public static final String TABLO_KARTLAR = "kartlar";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "isim";
    public static final String KEY_BALANCE = "bakiye";
    public static final String KEY_PASS = "parola";

    public sqlite_katmani(Context context){
        super(context, VERİ_TABANI_ADI, null, VERİTABANI_SURUMU);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = " CREATE TABLE " + TABLO_KARTLAR + " ( " + KEY_ID + " INTEGER PRİMARY KEY, " + KEY_NAME + " TEXT, "+ KEY_BALANCE + " REAL, " + KEY_PASS + " TEXT" + ")";
        db.execSQL(sql);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLO_KARTLAR);
        onCreate(db);
    }
}
