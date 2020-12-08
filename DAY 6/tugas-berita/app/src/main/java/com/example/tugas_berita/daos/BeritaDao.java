package com.example.tugas_berita.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.tugas_berita.model.Berita;

import java.util.List;

public interface BeritaDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertRoom(Berita berita);

    @Query("SELECT * FROM news_table")
    LiveData<List<Berita>> getNews();
}
