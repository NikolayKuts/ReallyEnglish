package com.example.reallyenglsh.data;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Sentence.class, V3Verb.class}, version = 1, exportSchema = false)
public abstract class SentencesDatabase extends RoomDatabase {
    private static SentencesDatabase database;
    private static final String DB_NAME = "really_english.db";
    private static final Object LOCK = new Object();

    public static SentencesDatabase getInstance(Context context) {
//        final Migration  MIGRATION_1_2 = new Migration(1,2) {
//            @Override
//            public void migrate(@NonNull SupportSQLiteDatabase database) {
//                database.execSQL("CREATE TABLE IF NOT EXISTS wrong_v3_verbs(_ID)");
//            }
//        };
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, SentencesDatabase.class, DB_NAME).build();
            }
        }
        return database;
    }





    public abstract SentencesDao sentencesDao();
    public abstract V3VerbDao v3VerbDao();
}
