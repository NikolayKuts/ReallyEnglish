package com.example.realyenglsh;

import java.util.List;

public class MyListTranslating {
    private String nameOfList;
    private List<String> listQuestions;
    private List<String> listAnswers;
    private boolean isChecked;

    public MyListTranslating(String nameOfList, boolean isChecked, List<String> listQuestions, List<String> listAnswers) {
        this.nameOfList = nameOfList;
        this.isChecked = isChecked;
        this.listQuestions = listQuestions;
        this.listAnswers = listAnswers;
    }

    public String getNameOfList() {
        return nameOfList;
    }

    public void setNameOfList(String nameOfList) {
        this.nameOfList = nameOfList;
    }

    public List<String> getListQuestions() {
        return listQuestions;
    }

    public void setListQuestions(List<String> listQuestions) {
        this.listQuestions = listQuestions;
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
