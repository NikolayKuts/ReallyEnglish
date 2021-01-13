package com.example.reallyenglsh;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class MyListAllWords {
    private String name;
    private boolean isChecked;
    private List<String> list;

    public MyListAllWords(String name, boolean isChecked, List<String> listWords) {
        this.name = name;
        this.isChecked = isChecked;
        this.list = listWords;
    }

//    private ArrayList<String> getArrayListFromRes(Context context, int...idRes) {
//        String[] array = context.getResources().getString(idRes).split(",");
//        return new ArrayList<>(Arrays.asList(array));
//    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<String> getList() {
        return list;
    }

    public void setList(List<String> list) {
        this.list = list;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
