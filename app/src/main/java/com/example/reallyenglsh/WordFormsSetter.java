package com.example.reallyenglsh;

import android.content.Context;
import android.widget.TextView;

import com.example.realyenglsh.R;

import java.util.List;

public class WordFormsSetter {
    private final TextView textViewV1;
    private final TextView textViewV2;
    private final TextView textViewV3;
    private final Context context;

    public WordFormsSetter(TextView v1, TextView v2, TextView v3, Context context) {
        textViewV1 = v1;
        textViewV2 = v2;
        textViewV3 = v3;
        this.context = context;
        textViewV1.setTextColor(context.getResources().getColor(R.color.verb_form_v1));
    }

    public void setVerbForms(String verb, List<String> irregularVerbsV1, List<String> irregularVerbsV2, List<String> irregularVerbsV3) {
        if (irregularVerbsV1.contains(verb)) {
            setFormByIrregularVerb(verb, irregularVerbsV1, irregularVerbsV2, irregularVerbsV3);
        } else {
           setFormBySimpleVerb(verb);
        }
    }

    public void setWordForms(String word,List<String> simpleVerbs, List<String> irregularVerbsV1, List<String> irregularVerbsV2, List<String> irregularVerbsV3) {
        if (irregularVerbsV1.contains(word)) {
            setFormByIrregularVerb(word, irregularVerbsV1, irregularVerbsV2, irregularVerbsV3);
        } else if (simpleVerbs.contains(word)) {
            setFormBySimpleVerb(word);
        } else {
            setTextViewsEmpty();
        }
    }

    public void setFormByToBeWord(String word) {
        textViewV1.setText("");
        textViewV2.setText(word);
        textViewV3.setText(R.string.note_adjective);
        setTextViewColor(R.color.verb_form_toBe, R.color.verb_form_note);
    }

    private void setTextViewsEmpty() {
        textViewV1.setText("-//-");
        textViewV2.setText("-//-");
        textViewV3.setText("-//-");
        setColorOnEmptyTextView();
    }

    private void setFormByIrregularVerb(String verb, List<String> irregularVerbsV1, List<String> irregularVerbsV2, List<String> irregularVerbsV3) {
        int index = irregularVerbsV1.indexOf(verb);
        textViewV1.setText(irregularVerbsV1.get(index));
        textViewV2.setText(irregularVerbsV2.get(index));
        textViewV3.setText(irregularVerbsV3.get(index));
        setTextViewColor(R.color.verb_form_v2, R.color.verb_form_v3);
    }

    private void setFormBySimpleVerb(String verb) {
        textViewV1.setText(verb);
        if (verb.endsWith("e")) {
            verb = verb + "d";
        } else if (verb.matches("\\w+[aeiouy]y")) {
            verb = verb.substring(0, verb.length() - 1) + "ied";
        } else {          //else if (simpleIrregularVerb.matches("\\w+[aeiouy]y"))
            verb = verb + "ed";
        }
        textViewV2.setText(verb);
        textViewV3.setText(R.string.note_regular);
        setTextViewColor(R.color.verb_form_v2, R.color.verb_form_note);
    }

    private void setTextViewColor(int idResColorV2, int idResColorV3) {
        textViewV2.setTextColor(context.getResources().getColor(idResColorV2));
        textViewV3.setTextColor(context.getResources().getColor(idResColorV3));
    }

    private void setColorOnEmptyTextView() {
        textViewV1.setTextColor(context.getResources().getColor(R.color.verb_form_note));
        setTextViewColor(R.color.verb_form_note, R.color.verb_form_note);
    }
}
