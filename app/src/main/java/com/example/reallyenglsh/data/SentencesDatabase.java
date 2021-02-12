package com.example.reallyenglsh.data;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.room.migration.Migration;
import androidx.sqlite.db.SupportSQLiteDatabase;

import com.example.reallyenglsh.MyWordList;


@Database(entities = {Sentence.class, V3Verb.class, CoupleList.class, MyWordList.class}, version = 5, exportSchema = false)
public abstract class SentencesDatabase extends RoomDatabase {
    private static SentencesDatabase database;
    private static final String DB_NAME = "really_english.db";
    private static final Object LOCK = new Object();

    private final static Migration MIGRATION_1_2 = new Migration(1, 2) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS saved_couple (" +
                    "nameCouple TEXT PRIMARY KEY NOT NULL, " +
                    "checked INTEGER NOT NULL);");
        }
    };

    private static final Migration MIGRATION_2_3 = new Migration(2, 3) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS saved_couple_lists (" +
                    "nameList TEXT PRIMARY KEY NOT NULL, " +
                    "checked INTEGER NOT NULL);");
        }
    };

    private static final Migration MIGRATION_3_4 = new Migration(3, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("DROP TABLE saved_couple");
        }
    };

    private static final Migration MIGRATION_1_4 = new Migration(1, 4) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS saved_couple_lists (" +
                    "nameList TEXT PRIMARY KEY NOT NULL, " +
                    "checked INTEGER NOT NULL);");
        }
    };

    private static final Migration MIGRATION_4_5 = new Migration(4, 5) {
        @Override
        public void migrate(@NonNull SupportSQLiteDatabase database) {
            database.execSQL("CREATE TABLE IF NOT EXISTS saved_word_list (" +
                    "name TEXT PRIMARY KEY NOT NULL, " +
                    "checked INTEGER NOT NULL);");
        }
    };

    public static SentencesDatabase getInstance(Context context) {
        synchronized (LOCK) {
            if (database == null) {
                database = Room.databaseBuilder(context, SentencesDatabase.class, DB_NAME)
                        .addMigrations(MIGRATION_1_2, MIGRATION_2_3, MIGRATION_3_4, MIGRATION_1_4, MIGRATION_4_5)
                        .build();
            }
        }
        return database;
    }

    public abstract SentencesDao sentencesDao();
    public abstract V3VerbDao v3VerbDao();
    public abstract CoupleListDao coupleListDao();
    public abstract MyWordListDao myWordListDao();
}