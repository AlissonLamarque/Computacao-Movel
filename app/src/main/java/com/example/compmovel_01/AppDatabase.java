package com.example.compmovel_01;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Peca.class}, version = 1)
public abstract class AppDatabase extends RoomDatabase {

    public abstract PecaDaoRoom pecaDaoRoom();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(
                            context.getApplicationContext(),
                            AppDatabase.class,
                            "pecas_database"
                    ).allowMainThreadQueries()
                    .build();
        }
        return INSTANCE;
    }
}