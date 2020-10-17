package com.example.realyenglsh;

import androidx.annotation.NonNull;
import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "wrong_sentences")
public class Sentence {
    private int id;

    @NonNull
    @PrimaryKey(autoGenerate = false)
    private String sentence;

    public Sentence(int id, String sentence) {
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

    public void setSentence(String sentence) {
        this.sentence = sentence;
    }

    @Override
    public String toString() {
        return "Sentence{" +
                "id=" + id +
                ", sentence='" + sentence + '\'' +
                '}';
    }
}
