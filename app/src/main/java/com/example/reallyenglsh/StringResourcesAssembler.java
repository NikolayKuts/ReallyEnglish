package com.example.reallyenglsh;

import android.content.Context;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StringResourcesAssembler {
    private final Context context;

    public StringResourcesAssembler(Context context) {
        this.context = context;
    }

//    public List<String> getListAdjective(int...idResource) {
//        return getListByArgsIdRes(idResource);
//    }

    public List<String> getListVerbsOfAllTypes(int idSimple, int idIrregularV1, int idIrregularV2, int idIrregularV3) {
        return getListByArgsIdRes(idSimple, idIrregularV1, idIrregularV2, idIrregularV3);
    }

//    public List<String> getListFromStringArrayRes(int...idResource) {
//        List<String> result = new ArrayList<>();
//        for (int id : idResource) {
//            result.addAll(getListFromArrayRes(id));
//        }
//        return result;
//    }
    public List<String> getListFormArrayRes(int idArrayRes) {
        return getListFromArrayRes(idArrayRes);
    }

    public List<String> getListFromStringRes(int id) {
        return Arrays.asList(context.getString(id).split(","));
    }

    public List<String> getListFromMixRes(int idArrayRes, int idStringRes) {
        List<String> result = new ArrayList<>();
        result.addAll(getListFromArrayRes(idArrayRes));
        result.addAll(getListFromStringRes(idStringRes));
        return result;
    }

    private List<String> getListFromArrayRes(int id) {
        return Arrays.asList(context.getResources().getStringArray(id));
    }

    private List<String> getListByArgsIdRes(int...idRes) {
        List<String> result = new ArrayList<>();
        for (int id : idRes) {
            result.addAll(getListFromStringRes(id));
        }
        return result;
    }
}
