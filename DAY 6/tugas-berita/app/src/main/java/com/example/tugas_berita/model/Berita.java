package com.example.tugas_berita.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

@Entity(tableName = "news_table", indices = {
        @Index("FirstIndex"),@Index(value = "id", unique = true),
        @Index("SecondIndex"),@Index(value = "title", unique = true)
})
public class Berita {
    @SerializedName("id")
    @Expose
    @PrimaryKey
    private int id;

    @SerializedName("title")
    @Expose
    private String title;

    @SerializedName("category")
    @Expose
    private String category;

    @SerializedName("url")
    @Expose
    private String url;

    public Berita() {

    }

    public Berita(String title, String category, String url) {
        this.title = title;
        this.category = category;
        this.url = url;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
