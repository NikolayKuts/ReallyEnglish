package com.example.reallyenglsh.data;

import androidx.annotation.NonNull;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wrong_sentences")
public class Sentence {
    private int id;

    @NonNull
    @PrimaryKey()
    private String sentence;

    public Sentence(int id, @NonNull String sentence) {
        this.id = id;
        this.sentence = sentence;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @NonNull
    public String getSentence() {
        return sentence;
    }

    public void setSentence(@NonNull String sentence) {
        this.sentence = sentence;
    }
}
