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

    public List<String> getListV1Simple(int idSimple, int idIrregularV1) {
        return getListFromStringRes(idSimple, idIrregularV1);
    }

    public List<String> getListFormArrayRes(int idArrayRes) {
        return getListFromArrayRes(idArrayRes);
    }

    public List<String> getListFromMixRes(int idArrayRes, int idStringRes) {
        List<String> result = new ArrayList<>();
        result.addAll(getListFromArrayRes(idArrayRes));
        result.addAll(getListFromStringRes(idStringRes));
        return result;
    }

    private List<String> getListFromStringRes(int id) {
        return Arrays.asList(context.getString(id).split("\\s*,\\s*"));
    }

    private List<String> getListFromArrayRes(int id) {
        return Arrays.asList(context.getResources().getStringArray(id));
    }

    public List<String> getListFromStringRes(int...idRes) {
        List<String> result = new ArrayList<>();
        for (int id : idRes) {
            result.addAll(getListFromStringRes(id));
        }
        return result;
    }
}
