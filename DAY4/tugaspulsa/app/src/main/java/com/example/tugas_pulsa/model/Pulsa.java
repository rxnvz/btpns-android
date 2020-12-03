package com.example.tugas_pulsa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pulsa {
    @SerializedName("id")
    @Expose
    private int id;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("phone_number")
    @Expose
    private String num;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }
}
