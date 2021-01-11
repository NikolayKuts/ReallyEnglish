package com.example.reallyenglsh.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wrong_v3_verbs")
public class V3Verb {
    private int id;

    @NonNull
    @PrimaryKey()
    private final String verb;

    public V3Verb(int id, @NonNull String verb) {
        this.id = id;
        this.verb = verb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getVerb() {
        return verb;
    }
}
