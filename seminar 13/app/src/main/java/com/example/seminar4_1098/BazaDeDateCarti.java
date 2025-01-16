package com.example.seminar4_1098;

import androidx.room.Database;
import androidx.room.RoomDatabase;

@Database(entities = {Carte.class}, version = 1)
public abstract class BazaDeDateCarti extends RoomDatabase {
    public abstract InterfataDAO getDaoObject();


}
