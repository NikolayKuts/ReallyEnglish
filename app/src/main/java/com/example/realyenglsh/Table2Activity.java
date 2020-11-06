package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.realyenglsh.Table1Activity.getWordFromList;

public class Table2Activity extends AppCompatActivity {
    public TextView textView0, textView1, textView2, textView3
            , textView4, textView5, textView6, textView7, textView8;
    private TextView textViewSentence;
    private TextView textViewHistory;
    private TextView textViewIrregularVerb;
    private Switch switchShowTable;
    private Switch switchIrregularVerb;
    private Button buttonPutIntoDBWrongSentence;

    private List<String> listOfNames;
    private List<String> listOfVerbsStrong;
//    private List<String> listOfVerbsSimple;
    private List<String> listOfVerbsIrregular;
    private List<String> listOfVerbsIrregularPast;
    private List<String> listOfVerbsSimpleIrregular;
    private List<String> listOfAdjective;
    private String wrongSentence;
    private MainViewModel viewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table2);
        textView0 = findViewById(R.id.textView0);
        textView1 = findViewById(R.id.textView1);
        textView2 = findViewById(R.id.textView2);
        textView3 = findViewById(R.id.textView3);
        textView4 = findViewById(R.id.textView4);
        textView5 = findViewById(R.id.textView5);
        textView6 = findViewById(R.id.textView6);
        textView7 = findViewById(R.id.textView7);
        textView8 = findViewById(R.id.textView8);
        textViewSentence = findViewById(R.id.textViewSentence);
        textViewHistory = findViewById(R.id.textView0);
        textViewIrregularVerb = findViewById(R.id.textViewIrregularVerb);
        switchShowTable = findViewById(R.id.switch1);
        switchIrregularVerb = findViewById(R.id.switchIrregularVerb);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
        buttonPutIntoDBWrongSentence = findViewById(R.id.buttonToTableActivity);

        String[] stringArgsNames = getString(R.string.names).split(",");
        String[] stringArgsPersonalPronouns = getResources().getStringArray(R.array.personal_pronouns);
        listOfNames = new ArrayList<>(Arrays.asList(stringArgsNames));
        listOfNames.addAll(Arrays.asList(stringArgsPersonalPronouns));

        String[] stringArgsVerbsSimple = getResources().getString(R.string.simple_verbs_1).split(",");
        String[] stringArgsVerbsIrregular = getResources().getString(R.string.irregular_verbs_v1_1).split(",");
        listOfVerbsSimpleIrregular = new ArrayList<>(Arrays.asList(stringArgsVerbsSimple));
        listOfVerbsSimpleIrregular.addAll(Arrays.asList(stringArgsVerbsIrregular));

        listOfVerbsIrregular = new ArrayList<>(Arrays.asList(stringArgsVerbsIrregular));

        String[] stringArgsVerbsIrregularV2 = getResources().getString(R.string.irregular_verbs_v2_1).split(",");
        listOfVerbsIrregularPast = new ArrayList<>(Arrays.asList(stringArgsVerbsIrregularV2));

        String[] stringArgsVerbsStrong = getResources().getStringArray(R.array.strong_verbs);
        listOfVerbsStrong = new ArrayList<>(Arrays.asList(stringArgsVerbsStrong));

        String[] stringArgsAdjective = getResources().getString(R.string.adjective).split(",");
        listOfAdjective = new ArrayList<>(Arrays.asList(stringArgsAdjective));


        switchShowTable.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                ConstraintLayout cl = findViewById(R.id.window);
                if (b) {
                    cl.setBackground(getResources().getDrawable(R.drawable.table_filled));
//                    getWindow().setBackgroundDrawableResource(R.drawable.table_filled);
                } else {
                    cl.setBackground(getResources().getDrawable(R.drawable.empty_table));
                }
            }
        });

        switchIrregularVerb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    textViewIrregularVerb.setVisibility(View.VISIBLE);
                } else {
                    textViewIrregularVerb.setVisibility(View.INVISIBLE);
                }
            }
        });

        buttonPutIntoDBWrongSentence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (Table1Activity.addWrongSentenceIntoDB(viewModel, Table2Activity.this, wrongSentence)) {
                    onClickNextSentence(view);
                }
                return true;
            }
        });
    }

    public void onClickNextSentence(View view) {
        textViewIrregularVerb.setText("");
        wrongSentence = "";
        Random random = new Random();
        int numberRandom = random.nextInt(9);
        String sentence = "";
        String name = getWordFromList(listOfNames);
        String simpleIrregularVerb = getWordFromList(listOfVerbsSimpleIrregular);
        switch (numberRandom) {
            case 0 : setTextViewBackGroundColor(textView0);
            break;
            case 1 : setTextViewBackGroundColor(textView1);
            break;
            case 2 : setTextViewBackGroundColor(textView2);
            break;
            case 3 : setTextViewBackGroundColor(textView3);
            break;
            case 4 : setTextViewBackGroundColor(textView4);
            break;
            case 5 : setTextViewBackGroundColor(textView5);
            break;
            case 6 : setTextViewBackGroundColor(textView6);
            break;
            case 7 : setTextViewBackGroundColor(textView7);
            break;
            case 8 : setTextViewBackGroundColor(textView8);
            break;
        }

        if (numberRandom == 0 || numberRandom ==3 || numberRandom == 6) {
            if (random.nextInt(2) == 0) {
                if (simpleIrregularVerb.endsWith("e")) {
                    simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1);
                } else if (simpleIrregularVerb.endsWith("ie")) {
                    simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 2);
                }
                sentence = String.format("%s %sing", name, simpleIrregularVerb);
            } else {
                String adjective = getWordFromList(listOfAdjective);
                sentence = String.format("%s %s", name, adjective);
            }
        } else if (numberRandom == 1 ||numberRandom == 4 || numberRandom == 7) {
            String strongVerb = getWordFromList(listOfVerbsStrong);
            if (numberRandom != 4 && (strongVerb.equals("would") || strongVerb.equals("should"))) {
                strongVerb = getWordFromList(Arrays.asList("can", "may", "must"));
            }
                sentence = String.format("%s %s %s", name, strongVerb, simpleIrregularVerb);
                wrongSentence = String.format("%s_%s", strongVerb, simpleIrregularVerb);
        } else {
             String simpleIrregularVerb1 = getWordFromList(listOfVerbsSimpleIrregular);
             sentence = String.format("%s %s %s", name, simpleIrregularVerb, simpleIrregularVerb1);
             wrongSentence = String.format("%s_%s", simpleIrregularVerb, simpleIrregularVerb1);

             if (listOfVerbsIrregular.contains(simpleIrregularVerb) && numberRandom == 8) {
                 int index = listOfVerbsIrregular.indexOf(simpleIrregularVerb);
                 textViewIrregularVerb.setText(listOfVerbsIrregularPast.get(index));
             }
        }
        if (!viewModel.isSentenceInDB(wrongSentence)) {
            textViewSentence.setText(sentence);
        } else {
            onClickNextSentence(view);
        }
    }


    private void setTextViewBackGroundColor(TextView textView) {
        textViewHistory.setBackgroundColor(getResources().getColor(R.color.my_background_color_for_textVew));
        textView.setBackgroundColor(getResources().getColor(R.color.my_color_for_textView));
        textViewHistory = textView;
    }
}