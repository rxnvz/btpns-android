package com.example.android_room.model;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "nasabah_table")
public class Nasabah {

    @PrimaryKey(autoGenerate = true)
    private int id;

    private String nama;
    private String alamat;
    private String email;

    public Nasabah (String nama, String alamat, String email) {
        this.nama = nama;
        this.alamat = alamat;
        this.email = email;
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

    public String getAlamat() {
        return alamat;
    }

    public void setAlamat(String alamat) {
        this.alamat = alamat;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
