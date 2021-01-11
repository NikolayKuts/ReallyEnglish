package com.example.reallyenglsh;

import java.util.List;

public class MyListOfVerbs {
    private final String nameOfList;
    private final List<String> listOfVerbsSimple;
    private final List<String> listOfVerbsIrregularV1;
    private final List<String> listOfVerbsIrregularV2;
    private final List<String> listOfVerbsIrregularV3;
    private  boolean isChecked;

    public MyListOfVerbs(String nameOfList, boolean b, List<String> listOfVerbsSimple, List<String> listOfVerbsIrregularV1, List<String> listOfVerbsIrregularV2, List<String> listOfVerbsIrregularV3) {
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

    public List<String> getListOfVerbsSimple() {
        return listOfVerbsSimple;
    }

    public List<String> getListOfVerbsIrregularV1() {
        return listOfVerbsIrregularV1;
    }

    public List<String> getListOfVerbsIrregularV2() {
        return listOfVerbsIrregularV2;
    }

    public List<String> getListOfVerbsIrregularV3() {
        return listOfVerbsIrregularV3;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }

}
