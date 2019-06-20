package com.example.thithu.database.database;


import android.content.Context;

import com.example.thithu.database.dao.MarkDao;
import com.example.thithu.database.entity.Mark;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;


@Database(entities = {Mark.class}, version = 1, exportSchema = false)
public abstract class AppDataBase extends RoomDatabase {
    private static AppDataBase instance;

    public abstract MarkDao MarkDao();
    public static AppDataBase getAppDatabase(Context context) {
        if (instance == null) {
            instance = Room.databaseBuilder(context.getApplicationContext(),
                    AppDataBase.class,
                    "database_mark")
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }
}
