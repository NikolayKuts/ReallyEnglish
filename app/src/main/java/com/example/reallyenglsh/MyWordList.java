package com.example.reallyenglsh;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.Ignore;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity(tableName = "saved_word_list")
public class MyWordList {
    @NonNull
    @PrimaryKey
    private final String name;
    private boolean checked;
    @Ignore
    private final List<String> list;

    public MyWordList(@NonNull String name, boolean checked) {
        this.name = name;
        this.checked = checked;
        list = null;
    }

    @Ignore
    public MyWordList(@NonNull String name, boolean isChecked, List<String> listWords) {
        this.name = name;
        this.checked = isChecked;
        this.list = listWords;
    }

    public String getName() {
        return name;
    }

    public boolean isChecked() {
        return checked;
    }

    public List<String> getListWords() {
        return list;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }

}
