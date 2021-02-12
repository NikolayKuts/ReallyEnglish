package com.example.reallyenglsh.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.reallyenglsh.MyWordList;

import java.util.List;

@Dao
public interface MyWordListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListOfMyWordList(List<MyWordList> lists);

    @Query("SELECT * FROM saved_word_list")
    List<MyWordList> getAllMyWordLists();

}
