package com.example.tugas_pulsa.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PulsaResponse {
    @SerializedName("status")
    @Expose
    private String status;

    @SerializedName("code")
    @Expose
    private String code;

    @SerializedName("message")
    @Expose
    private String msg;

    @SerializedName("data")
    @Expose
    private Pulsa data = null;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Pulsa getData() {
        return data;
    }

    public void setData(Pulsa data) {
        this.data = data;
    }
}
