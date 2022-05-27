package com.example.kartsistemi;

public class kart {
    private int _id;
    private String _isim;
    private float _bakiye;
    private String _parola;

    public kart() {}
    public kart(int id, String isim, String parola) {
        this._id = id;
        this._isim = isim;
        this._bakiye = 0;
        this._parola = parola;
    }
    public kart(int id, String isim, float bakiye, String parola) {
        this._id = id;
        this._isim = isim;
        this._bakiye = bakiye;
        this._parola = parola;
    }

    public String toString() {
        return ""+_id;
    }

    public int getID() {
        return this._id;
    }
    public void setID(int id) {
        this._id = id;
    }

    public String getIsim() {
        return this._isim;
    }
    public void setIsim(String isim) {
        this._isim = isim;
    }

    public float getBakiye() {
        return this._bakiye;
    }
    public void setBakiye(float bakiye) {
        this._bakiye = bakiye;
    }

    public String getParola() {
        return this._parola;
    }
    public void setParola(String parola) {
        this._parola = parola;
    }
}
