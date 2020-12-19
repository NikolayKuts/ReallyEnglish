package com.example.reallyenglsh;

import java.util.ArrayList;
import java.util.List;

public class MyListOfVerbs {
    private String nameOfList;
    private List<String> listOfVerbsSimple;
    private List<String> listOfVerbsIrregularV1;
    private List<String> listOfVerbsIrregularV2;
    private List<String> listOfVerbsIrregularV3;
    private boolean isChecked;

    public MyListOfVerbs(String nameOfList, boolean b, List<String> listOfVerbsSimple, List<String> listOfVerbsIrregularV1
            , List<String> listOfVerbsIrregularV2, List<String> listOfVerbsIrregularV3) {

        this.nameOfList = nameOfList;
        this.isChecked = b;
        this.listOfVerbsSimple = listOfVerbsSimple;
        this.listOfVerbsIrregularV1 = listOfVerbsIrregularV1;
        this.listOfVerbsIrregularV2 = listOfVerbsIrregularV2;
        this.listOfVerbsIrregularV3 = listOfVerbsIrregularV3;
    }

    public String getNameOfList() {
        return nameOfList;
    }
    public void setNameOfList(String nameOfList) {
        this.nameOfList = nameOfList;
    }

    public List<String> getListOfVerbsSimple() {
        return listOfVerbsSimple;
    }

    public void setListOfVerbsSimple(List<String> listOfVerbsSimple) {
        this.listOfVerbsSimple = listOfVerbsSimple;
    }

    public List<String> getListOfVerbsIrregularV1() {
        return listOfVerbsIrregularV1;
    }

    public void setListOfVerbsIrregularV1(List<String> listOfVerbsIrregularV1) {
        this.listOfVerbsIrregularV1 = listOfVerbsIrregularV1;
    }

    public List<String> getListOfVerbsIrregularV2() {
        return listOfVerbsIrregularV2;
    }

    public void setListOfVerbsIrregularV2(List<String> listOfVerbsIrregularV2) {
        this.listOfVerbsIrregularV2 = listOfVerbsIrregularV2;
    }

    public List<String> getListOfVerbsIrregularV3() {
        return listOfVerbsIrregularV3;
    }

    public void setListOfVerbsIrregularV3(List<String> listOfVerbsIrregularV3) {
        this.listOfVerbsIrregularV3 = listOfVerbsIrregularV3;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
