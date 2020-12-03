package com.example.android_butterknife.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class NasabahResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private List<Nasabah> data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public List<Nasabah> getData() {
        return data;
    }

    public void setData(List<Nasabah> data) {
        this.data = data;
    }
}
