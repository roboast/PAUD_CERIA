package com.example.astidhiyaa.papbl;

import java.io.Serializable;

public class MakananModel implements Serializable{
    private int id;
    private String nama_makanan;
    private String umur;

    public MakananModel() {
    }

    public MakananModel(int id, String nama_makanan, String umur) {
        this.id = id;
        this.nama_makanan = nama_makanan;
        this.umur = umur;
    }

    public MakananModel(String nama_makanan, String umur) {
        this.nama_makanan = nama_makanan;
        this.umur = umur;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setUmur(String umur) {
        this.umur = umur;
    }

    public String getUmur() {

        return umur;
    }

    public void setNama_makanan(String nama_makanan) {

        this.nama_makanan = nama_makanan;
    }

    public String getNama_makanan() {

        return nama_makanan;
    }
}
