package com.example.reallyenglsh.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wrong_v3_verbs")
public class V3Verb {
    private int id;

    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String verb;

    public V3Verb(int id, String verb) {
        this.id = id;
        this.verb = verb;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getVerb() {
        return verb;
    }

    public void setVerb(String verb) {
        this.verb = verb;
    }
}
