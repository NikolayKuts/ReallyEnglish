package com.example.reallyenglsh;

import java.util.List;

public class MyListCouples {
    private final String nameOfList;
    private final List<String> listQuestion;
    private final List<String> listAnswers;
    private boolean isChecked;

    public MyListCouples(String nameOfList, boolean isChecked, List<String> listQuestion, List<String> listAnswers) {
        this.nameOfList = nameOfList;
        this.isChecked = isChecked;
        this.listQuestion = listQuestion;
        this.listAnswers = listAnswers;
    }

    public String getNameOfList() {
        return nameOfList;
    }

    public List<String> getListQuestion() {
        return listQuestion;
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
