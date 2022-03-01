package com.example.testactivity.database;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;

import com.example.testactivity.dao.DictionaryDao;
import com.example.testactivity.entities.Dictionary;

@Database(entities = Dictionary.class, version = 1, exportSchema = false)
public abstract class DictionariesDatabase extends RoomDatabase {

    private static DictionariesDatabase dictionaryDatabase;

    public static synchronized DictionariesDatabase getDatabase(Context context){
        if(dictionaryDatabase == null){
            dictionaryDatabase = Room.databaseBuilder(
                    context,
                    DictionariesDatabase.class,
                    "dictionaries_database"
            ).build();
        }
        return dictionaryDatabase;
    }

    public abstract DictionaryDao dictionaryDao();
}
