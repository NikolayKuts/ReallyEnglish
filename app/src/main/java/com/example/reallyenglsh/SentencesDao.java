package com.example.reallyenglsh;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface SentencesDao {
    @Query("SELECT * FROM wrong_sentences")
    List<Sentence> getAllSentences();

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertSentence(Sentence sentence);

    @Delete
    void deleteNote(Sentence sentence);

    @Query("DELETE FROM wrong_sentences")
    void deleteAllSentences();

    @Query("SELECT MAX(id) FROM wrong_sentences")
    int getMaxId();

    @Query("SELECT COUNT(*) FROM wrong_sentences")
    int getCountSentences();

    @Query("SELECT * FROM wrong_sentences WHERE sentence = :textSentence")
    boolean isSentenceInDB(String textSentence);

}
