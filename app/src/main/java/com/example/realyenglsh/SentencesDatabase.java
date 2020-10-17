package com.example.realyenglsh;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sentence.class}, version = 1, exportSchema = false)
public abstract class SentencesDatabase extends RoomDatabase {
    private static SentencesDatabase database;
    private static final String DB_NAME = "really_english.db";
    private static final Object LOCK = new Object();

    public static SentencesDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, SentencesDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }

    public abstract SentencesDao sentencesDao();
}
