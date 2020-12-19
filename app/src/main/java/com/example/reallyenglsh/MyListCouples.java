package com.example.reallyenglsh;

import java.util.List;

public class MyListCouples {
    private String nameOfList;
    private List<String> listCouples;
    private List<String> listAnswers;
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

    public void setNameOfList(String nameOfList) {
        this.nameOfList = nameOfList;
    }

    public List<String> getListCouples() {
        return listCouples;
    }

    public void setListCouples(List<String> listCouples) {
        this.listCouples = listCouples;
    }

    public List<String> getListAnswers() {
        return listAnswers;
    }

    public void setListAnswers(List<String> listAnswers) {
        this.listAnswers = listAnswers;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
