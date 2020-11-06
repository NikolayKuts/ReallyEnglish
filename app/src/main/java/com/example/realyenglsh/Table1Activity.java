package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class Table1Activity extends AppCompatActivity {
    private TextView textViewSentence;
    private List<String> listOfNames;
    private List<String> listOfAdjective;
    private List<String> listOfVerbsStrong;
    private List<String> listOfVerbsSimpleIrregular;
    private String wrongSentence = "";
    private MainViewModel viewModel;
    private Button buttonPutWrongSentenceIntoDB;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_table1);
        textViewSentence = findViewById(R.id.textViewSentence);
        buttonPutWrongSentenceIntoDB = findViewById(R.id.buttonToTableActivity);
        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        String[] stringArgsNames = getString(R.string.names).split(",");
        String[] stringArgsPersonalPronouns = getResources().getStringArray(R.array.personal_pronouns);
        listOfNames = new ArrayList<>(Arrays.asList(stringArgsNames));
        listOfNames.addAll(Arrays.asList(stringArgsPersonalPronouns));

        String[] stringArgsVerbsStrong = getResources().getStringArray(R.array.strong_verbs);
        listOfVerbsStrong = new ArrayList<>(Arrays.asList(stringArgsVerbsStrong));

        String[] stringArgsVerbsSimple = getResources().getString(R.string.simple_verbs_1).split(",");
        String[] stringArgsVerbsIrregular = getResources().getString(R.string.irregular_verbs_v1_1).split(",");
        listOfVerbsSimpleIrregular = new ArrayList<>(Arrays.asList(stringArgsVerbsSimple));
        listOfVerbsSimpleIrregular.addAll(Arrays.asList(stringArgsVerbsIrregular));

        String[] stringArgsAdjective = getResources().getString(R.string.adjective).split(",");
        listOfAdjective = new ArrayList<>(Arrays.asList(stringArgsAdjective));
        
        buttonPutWrongSentenceIntoDB.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (addWrongSentenceIntoDB(viewModel, Table1Activity.this, wrongSentence)) {
                    onClickNextExample(view);
                }
                return true;
            }
        });

    }

    public void onClickNextExample(View view) {
        String name = getWordFromList(listOfNames);
        String sentence = "";
        Random random = new Random();
        int numberRandom0 = random.nextInt(4);
        wrongSentence = "";

        String simpleIrregularVerb = getWordFromList(listOfVerbsSimpleIrregular);

        if (numberRandom0 == 0) {
            String strongVerb = getWordFromList(listOfVerbsStrong);
            sentence = String.format("%s %s %s", name, strongVerb, simpleIrregularVerb);
            wrongSentence = String.format("%s_%s", strongVerb, simpleIrregularVerb);
//            Log.i("wrongSentence1", "--------" + wrongSentence);
        } else if (numberRandom0 == 1) {
            String simpleIrregularVerb1 = getWordFromList(listOfVerbsSimpleIrregular);
            sentence = String.format("%s %s %s", name, simpleIrregularVerb, simpleIrregularVerb1);
            wrongSentence = String.format("%s_%s", simpleIrregularVerb, simpleIrregularVerb1);
//            Log.i("wrongSentence2", "--------" + wrongSentence);
        } else if (numberRandom0 == 2) {
            String adjective = getWordFromList(listOfAdjective);
            sentence = String.format("%s %s", name, adjective);
        } else {
            if (simpleIrregularVerb.endsWith("e")) {
                simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1);
            } else if (simpleIrregularVerb.endsWith("ie")) {
                simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 2);
            }
            sentence = String.format("%s %sing", name, simpleIrregularVerb);
        }
        if (!viewModel.isSentenceInDB(wrongSentence)) {

//            Log.i("wrongSentenceBoolean", "--------" + viewModel.isSentenceInDB(wrongSentence));
            textViewSentence.setText(sentence);
        } else {
//            Log.i("wrongSentenceBooleanNex", "--------" + viewModel.isSentenceInDB(wrongSentence));
            onClickNextExample(view);
        }
    }

    public static String getWordFromList(List<String> list) {
        Random random = new Random();
        int numberRandom = random.nextInt(list.size());
        return list.get(numberRandom);
    }

    public static boolean addWrongSentenceIntoDB(MainViewModel viewModel, Context context, String wrongSentence) {
        if (!wrongSentence.equals("")) {
            int countFromDB = viewModel.getCountSentences();
            if (countFromDB != 0) {
                int maxId = viewModel.getMaxId();
                viewModel.insertSentence(new Sentence(maxId + 1, wrongSentence));
            } else {
                viewModel.insertSentence(new Sentence(1, wrongSentence));
            }
            Toast.makeText(context, "the sentence is added to the database", Toast.LENGTH_SHORT).show();
            return true;
        } else {
            Toast.makeText(context, "it's impossible to add the sentence to the database", Toast.LENGTH_LONG).show();
        }
        return false;
    }
    
}