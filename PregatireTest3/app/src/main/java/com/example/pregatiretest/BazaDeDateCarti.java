package com.example.pregatiretest;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Carte.class}, version = 1)

public abstract class BazaDeDateCarti extends RoomDatabase {
    public abstract InterfataDao getDaoObject();
}
