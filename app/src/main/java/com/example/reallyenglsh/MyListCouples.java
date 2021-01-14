package com.example.reallyenglsh;

import java.util.List;

public class MyListCouples implements IAdapterHelper {
    private final String nameOfList;
    private final List<String> listQuestion;
    private final List<String> listAnswers;
    private boolean checked;

    public MyListCouples(String nameOfList, boolean checked, List<String> listQuestion, List<String> listAnswers) {
        this.nameOfList = nameOfList;
        this.checked = checked;
        this.listQuestion = listQuestion;
        this.listAnswers = listAnswers;
    }

    public String getNameList() {
        return nameOfList;
    }

    public List<String> getListQuestion() {
        return listQuestion;
    }

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public boolean isChecked() {
        return checked;
    }

    public void setChecked(boolean checked) {
        this.checked = checked;
    }
}
