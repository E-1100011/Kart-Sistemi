package com.example.kartsistemi;

import static com.example.kartsistemi.sqlite_katmani.KEY_BALANCE;
import static com.example.kartsistemi.sqlite_katmani.KEY_ID;
import static com.example.kartsistemi.sqlite_katmani.KEY_NAME;
import static com.example.kartsistemi.sqlite_katmani.KEY_PASS;
import static com.example.kartsistemi.sqlite_katmani.TABLO_KARTLAR;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class veri_kaynagi {
    SQLiteDatabase db;
    sqlite_katmani mdb;
    public veri_kaynagi(Context c) {
        mdb = new sqlite_katmani(c);
    }
    public void open() {
        db = mdb.getWritableDatabase();
    }
    public void close() {
        mdb.close();
    }



    public String kartEkle(kart k) {
        kart varmık = sorgula(String.valueOf(k.getID()));

        if (varmık == null) {
            ContentValues values = new ContentValues();
            values.put(KEY_ID, k.getID());
            values.put(KEY_NAME, k.getIsim());
            values.put(KEY_PASS, k.getParola());

            db.insert(TABLO_KARTLAR, null, values);
            return "Başarılı";
        } else {
            return "Bu ID'ye sahip bir kart mevcut";
        }
    }


    public kart sorgula(String SId) {
        kart k = null;
        Cursor c = db.query(true, TABLO_KARTLAR, new String[]{KEY_NAME, KEY_BALANCE, KEY_PASS}, KEY_ID + "=?", new String[] {SId}, null, null, null, null);
        if (c.getCount() > 0) {
            c.moveToFirst();
            k = new kart();
            k.setID(Integer.parseInt(SId));
            k.setIsim(c.getString(c.getColumnIndex(KEY_NAME)));
            k.setBakiye(c.getFloat(c.getColumnIndex(KEY_BALANCE)));
            k.setParola(c.getString(c.getColumnIndex(KEY_PASS)));
        }
        c.close();
        return k;
    }

    public String[] bakYukle(String SId, Float miktar) {

        kart k = sorgula(SId);
        if (k != null) {
            k.setBakiye(k.getBakiye() + miktar);

            ContentValues cv = new ContentValues();
            cv.put(KEY_BALANCE, k.getBakiye());
            db.update(TABLO_KARTLAR, cv, KEY_ID + " = ?", new String[]{SId});

            return new String[]{"Başarılı", String.valueOf(k.getBakiye())};
        } else {
            return new String[]{"Bu ID'ye sahip bir kart yok", null};
        }
    }

    public String[] bakHarca(String SId, Float miktar, String parola) {

        kart k = sorgula(SId);

        if (k != null) {
            float bak = k.getBakiye();

            if (parola.equals(k.getParola())) {
                if (bak >= miktar) {
                    k.setBakiye(bak - miktar);

                    ContentValues cv = new ContentValues();
                    cv.put(KEY_BALANCE, k.getBakiye());
                    db.update(TABLO_KARTLAR, cv, KEY_ID + " = ?", new String[]{SId});

                    return new String[]{"Başarılı", String.valueOf(k.getBakiye())};
                } else {
                    return new String[]{"Yetersiz bakiye", String.valueOf(k.getBakiye())};
                }
            } else {
                return new String[]{"Girilen bilgiler hatalı", null};
            }
        } else {
            return new String[]{"Girilen bilgiler hatalı", null};
        }
    }

}
