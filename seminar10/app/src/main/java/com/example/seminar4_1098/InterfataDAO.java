package com.example.seminar4_1098;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface InterfataDAO {
    @Insert
    void insert(Carte carte);

    @Query("SELECT * FROM Carti")
    List<Carte> getAllCarti();

    @Delete
    void delete(Carte carte);
}


