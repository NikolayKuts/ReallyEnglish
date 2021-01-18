package com.example.reallyenglsh.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import com.example.reallyenglsh.IAdapterHelper;

import java.util.List;

@Entity(tableName = "saved_couple_lists")
public class CoupleList implements IAdapterHelper {
    @NonNull
    @PrimaryKey
    private final String nameList;

    private int checked;

    @Ignore
    private final List<String> listQuestion;
    @Ignore
    private final List<String> listAnswers;


    public CoupleList(@NonNull String nameList, int checked) {
        this.nameList = nameList;
        this.checked = checked;
        this.listQuestion = null;
        this.listAnswers = null;
    }

    @Ignore
    public CoupleList(@NonNull String nameList, boolean checked, List<String> listQuestion, List<String> listAnswers) {
        this.nameList = nameList;
        this.checked = transform(checked);
        this.listQuestion = listQuestion;
        this.listAnswers = listAnswers;
    }

    @NonNull
    public String getNameList() {
        return nameList;
    }

    @Override
    public boolean isChecked() {
        return checked > 0;
    }

    public List<String> getListQuestion() {
        return listQuestion;
    }

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public int getChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = transform(checked);
    }

    private int transform(boolean checked) {
        if (checked) {
            return 1;
        }
        return 0;
    }
}
