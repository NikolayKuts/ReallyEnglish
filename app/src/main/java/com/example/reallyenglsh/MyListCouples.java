package com.example.reallyenglsh;

import java.util.List;

public class MyListCouples {
    private final String nameOfList;
    private final List<String> listCouples;
    private final List<String> listAnswers;
    private boolean isChecked;

    public MyListCouples(String nameOfList, boolean isChecked, List<String> listCouples, List<String> listAnswers) {
        this.nameOfList = nameOfList;
        this.isChecked = isChecked;
        this.listCouples = listCouples;
        this.listAnswers = listAnswers;
    }

    public String getNameOfList() {
        return nameOfList;
    }

    public List<String> getListCouples() {
        return listCouples;
    }

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
