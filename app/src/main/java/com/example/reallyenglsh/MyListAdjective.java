package com.example.reallyenglsh;

import java.util.List;

public class MyListAdjective {
    private final String nameList;
    private final List<String> listAdjective;
    private boolean isChecked;

    public MyListAdjective(String nameList, boolean isChecked, List<String> listAdjective) {
        this.nameList = nameList;
        this.isChecked = isChecked;
        this.listAdjective = listAdjective;
    }

    public String getNameList() {
        return nameList;
    }

    public List<String> getListAdjective() {
        return listAdjective;
    }

    public boolean isChecked() {
        return isChecked;
    }

    public void setChecked(boolean checked) {
        isChecked = checked;
    }
}
