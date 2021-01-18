package com.example.reallyenglsh.data;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import java.util.List;

@Dao
public interface CoupleListDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertMyListCouple(CoupleList myListCouples);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    void insertListOfCoupleLists(List<CoupleList> listMyListCouples);

    @Query("SELECT * FROM saved_couple_lists")
    List<CoupleList> getAllSavedCoupleLists();

    @Query("SELECT COUNT(*) FROM saved_couple_lists")
    int getCount();

    @Query("DELETE FROM saved_couple_lists")
    void clearDBSavedCoupleLists();
}
