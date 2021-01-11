package com.example.reallyenglsh.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

@Dao
public interface V3VerbDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertVerb(V3Verb verb);

    @Query("SELECT MAX(id) FROM wrong_v3_verbs")
    int getMaxId();

    @Query("SELECT COUNT(*) FROM wrong_v3_verbs")
    int getCount();

    @Query("SELECT * FROM wrong_v3_verbs WHERE verb = :textV3Verb")
    boolean isV3VerbInDB(String textV3Verb);
}