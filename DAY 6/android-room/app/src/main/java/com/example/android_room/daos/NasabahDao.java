package com.example.android_room.daos;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.android_room.model.Nasabah;

import java.util.List;

@Dao
public interface NasabahDao {

    @Insert
    void insert(Nasabah nasabah);

    @Update
    void update(Nasabah nasabah);

    @Delete
    void delete(Nasabah nasabah);

    @Query("DELETE FROM nasabah_table")
    void deleteAll();

    @Query("SELECT * FROM nasabah_table ORDER BY nama ASC")
    LiveData<List<Nasabah>> getAllData();
}
