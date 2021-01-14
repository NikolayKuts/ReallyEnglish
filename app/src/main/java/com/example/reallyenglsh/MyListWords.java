package com.example.reallyenglsh;

import java.util.List;

public class MyListWords {
    private final String name;
    private boolean isChecked;
    private final List<String> list;

    public MyListWords(String name, boolean isChecked, List<String> listWords) {
        this.name = name;
        this.isChecked = isChecked;
        this.list = listWords;
    }

    public String getName() {
        return name;
    }

    public List<String> getListWords() {
        return list;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
