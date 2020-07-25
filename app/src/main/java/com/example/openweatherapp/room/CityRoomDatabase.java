package com.example.openweatherapp.room;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {CityEntity.class}, version = 1)
public abstract class CityRoomDatabase extends RoomDatabase {

    public abstract CityDao cityDao();
    private static volatile CityRoomDatabase INSTANCE;

    public static CityRoomDatabase getDatabase(final Context context) {

        if (INSTANCE == null) {
            synchronized (CityRoomDatabase.class) {

                if (INSTANCE == null) {
                    INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                            CityRoomDatabase.class, "weatherdb").build();
                }
            }
        }
        return INSTANCE;
    }
}
