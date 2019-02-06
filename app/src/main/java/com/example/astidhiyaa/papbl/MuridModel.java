package com.example.astidhiyaa.papbl;

import java.io.Serializable;

public class MuridModel implements Serializable {
    private int id;
    private String nama;
    private String no_induk;

    public MuridModel() {

    }

    public MuridModel(String nama, String no_induk) {
        this.nama = nama;
        this.no_induk = no_induk;
    }

    public MuridModel(int id, String nama, String no_induk) {
        this.id = id;
        this.nama = nama;
        this.no_induk = no_induk;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getNo_induk() {
        return no_induk;
    }

    public void setNo_induk(String no_induk) {
        this.no_induk = no_induk;
    }
}
