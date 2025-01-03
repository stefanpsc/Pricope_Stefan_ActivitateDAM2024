package com.example.cartev2;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;
@Dao
public interface InterfataDao{
    @Insert
void insert(Carte carte);
@Query("SELECT * FROM Carti")
List<Carte> getAllCarti();

@Delete
    void delete(Carte carte);
}

