package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.realyenglsh.Table1Activity.getWordFromList;

public class AlgorithmActivity extends AppCompatActivity {
    private ImageView imageViewTenseObject;
    private ImageView imageViewTypeOfSentence;
    private List<Integer> idTenseObject;
    private TextView textViewSentence;
    private TextView textViewIrregularVerbPast;
    private MainViewModel viewModel;
    private Button buttonPutIntoDBWrongSentence;
    private Switch switchShowPrompt;
    private Switch switchIrregularPastVerb;
    private ConstraintLayout constraintLayout;

    private List<String> listOfNames;
    private List<String> listOfVerbsSimpleIrregular;
    private List<String> listOfVerbsIrregular;
    private List<String> listOfVerbsIrregularPast;
    private List<String> listOfVerbsStrong;
    private List<String> listOfAdjective;
    private List<String> listOfVerbsIrregularV3;
    private List<Integer> listOfBackgroundImages;
    private List<Integer> listOfImageTypeOfSentence;

    private String wrongSentence = "";
    private String wrongV3PassiveVerb = "";
    private int randomNumberOfTense = -1;
    private boolean isSwitchShowPromptOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        imageViewTenseObject = findViewById(R.id.imageTenseObject);
        imageViewTenseObject.setImageResource(R.drawable.coach);
        imageViewTypeOfSentence = findViewById(R.id.imageViewTypeOfSentence);
        textViewSentence = findViewById(R.id.textViewSentence);
        textViewSentence = findViewById(R.id.textViewSentence);
        textViewIrregularVerbPast = findViewById(R.id.textViewIrregularVerbPast);
        buttonPutIntoDBWrongSentence = findViewById(R.id.buttonPutIntoDBWrongSentence);
        switchIrregularPastVerb = findViewById(R.id.switchIrregularVerb);
        switchShowPrompt = findViewById(R.id.switchShowPrompt);
        constraintLayout = findViewById(R.id.windowAlgorithmActivity);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        idTenseObject = new ArrayList<>();
        idTenseObject.add(R.drawable.spaceship);
        idTenseObject.add(R.drawable.car);
        idTenseObject.add(R.drawable.coach);

        listOfBackgroundImages = new ArrayList<>();
        listOfBackgroundImages.add(R.drawable.tense_way_future);
        listOfBackgroundImages.add(R.drawable.tense_way_present);
        listOfBackgroundImages.add(R.drawable.tense_way_past);

        listOfImageTypeOfSentence = new ArrayList<>();
        addDrawableResources(listOfImageTypeOfSentence, R.drawable.minus, R.drawable.plus, R.drawable.qa_mark);


        String[] stringArgsNames = getArrayFromResources(R.string.names);
        String[] stringArgsPersonalPronouns = getArrayFromArrayResources(R.array.personal_pronouns);
        listOfNames = getArrayListFromArgs(stringArgsNames, stringArgsPersonalPronouns);

        String[] stringArgsVerbsSimple = getArrayFromResources(R.string.simple_verbs_1);
        String[] stringArgsVerbsIrregular = getArrayFromResources(R.string.irregular_verbs_1);
        listOfVerbsSimpleIrregular = getArrayListFromArgs(stringArgsVerbsSimple, stringArgsVerbsIrregular);
        listOfVerbsIrregular = getArrayListFromArgs(stringArgsVerbsIrregular);

        String[] stringArgsVerbsIrregularPast = getArrayFromResources(R.string.irregular_verbs_past_1);
        listOfVerbsIrregularPast = getArrayListFromArgs(stringArgsVerbsIrregularPast);

        String[] stringArgsVerbsStrong = getArrayFromArrayResources(R.array.strong_verbs);
        listOfVerbsStrong = getArrayListFromArgs(stringArgsVerbsStrong);

        String[] stringArgsAdjective = getArrayFromResources(R.string.adjective);
        listOfAdjective = getArrayListFromArgs(stringArgsAdjective);

        String[] stringArgsVerbsIrregularV3 = getArrayFromResources(R.string.irregular_verbs_v3_1);
        listOfVerbsIrregularV3 = getArrayListFromArgs(stringArgsVerbsIrregularV3);

        buttonPutIntoDBWrongSentence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (!wrongSentence.equals("")) {
                    addWrongSentenceToDB(wrongSentence);
                    onClickNext(v);
                } else if (!wrongV3PassiveVerb.equals("")) {
                    addWrongV3VerbToDB(wrongV3PassiveVerb);
                    onClickNext(v);
                } else {
                    Toast.makeText(getApplicationContext(), "it's impossible to add the sentence to the database", Toast.LENGTH_LONG).show();
                }
                return true;
            }
        });

        switchShowPrompt.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                isSwitchShowPromptOn = b;
                if (randomNumberOfTense != -1) {
                    setConstraintLayoutBackgroundImage(isSwitchShowPromptOn);
                }
            }
        });

        switchIrregularPastVerb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    textViewIrregularVerbPast.setVisibility(View.VISIBLE);
                } else {
                    textViewIrregularVerbPast.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onClickNext(View view) {
        Random random = new Random();
        randomNumberOfTense = random.nextInt(3);  // 0 - future, 1 - present, 2 - past
        setConstraintLayoutBackgroundImage(isSwitchShowPromptOn);
        imageViewTenseObject.setImageResource(idTenseObject.get(randomNumberOfTense));
        imageViewTenseObject.setVisibility(View.VISIBLE);
//        int randomNumberImageTypeOfSentence = random.nextInt(listOfImageTypeOfSentence.size());
//        imageViewTypeOfSentence.setImageResource(listOfImageTypeOfSentence.get(randomNumberImageTypeOfSentence));
        textViewIrregularVerbPast.setText("--//--");
        int randomNumberOfSentence = random.nextInt(3);  // 0 - simple verbs, 1 - strong verbs, 2 - ing & adjective
        wrongSentence = "";
        wrongV3PassiveVerb = "";
        String sentence = "";
        String name = getWordFromList(listOfNames);
        String simpleIrregularVerb = getWordFromList(listOfVerbsSimpleIrregular);

        if (randomNumberOfSentence == 0) {
            String simpleIrregularVerb1 = getWordFromList(listOfVerbsSimpleIrregular);
            sentence = String.format("%s %s %s", name, simpleIrregularVerb, simpleIrregularVerb1);
            wrongSentence = String.format("%s_%s", simpleIrregularVerb, simpleIrregularVerb1);

            if (listOfVerbsIrregular.contains(simpleIrregularVerb) && randomNumberOfTense == 2) {
                int index = listOfVerbsIrregular.indexOf(simpleIrregularVerb);
                textViewIrregularVerbPast.setText(listOfVerbsIrregularPast.get(index));
            }
        } else if (randomNumberOfSentence == 1) {
            String strongVerb = getWordFromList(listOfVerbsStrong);
            if (randomNumberOfTense != 1 && (strongVerb.equals("would") || strongVerb.equals("should"))) {
                strongVerb = getWordFromList(Arrays.asList("can", "may", "must"));
            }
            sentence = String.format("%s %s %s", name, strongVerb, simpleIrregularVerb);
            wrongSentence = String.format("%s_%s", strongVerb, simpleIrregularVerb);
        } else {
            int randomNumberOfToBe = random.nextInt(3);
            if (randomNumberOfToBe == 0) {
                if (simpleIrregularVerb.endsWith("e")) {
                    simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1);
                } else if (simpleIrregularVerb.endsWith("ie")) {
                    simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 2);
                }
                sentence = String.format("%s %sing", name, simpleIrregularVerb);
            } else if (randomNumberOfToBe == 1) {
                String adjective = getWordFromList(listOfAdjective);
                sentence = String.format("%s %s", name, adjective);
            } else {
                if (listOfVerbsIrregular.contains(simpleIrregularVerb)) {
                    int index = listOfVerbsIrregular.indexOf(simpleIrregularVerb);
                    String irregularPastVerbV3 = listOfVerbsIrregularV3.get(index);
                    sentence = String.format("%s %s", name, irregularPastVerbV3);
                    textViewIrregularVerbPast.setText(listOfVerbsIrregularPast.get(index));
                    wrongV3PassiveVerb = irregularPastVerbV3;
                } else {
                    if (simpleIrregularVerb.endsWith("e")) {
                        simpleIrregularVerb = simpleIrregularVerb + "d";
                        sentence = String.format("%s %s", name, simpleIrregularVerb);
                    } else if (simpleIrregularVerb.matches("\\w+[aeiouy]y")) {
                        sentence = String.format("%s %s", name, simpleIrregularVerb + "ed");
                    } else if (simpleIrregularVerb.matches("\\w+[^aeiouy]y")) {
                        simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1) + "ied";
                        sentence = String.format("%s %s", name, simpleIrregularVerb);
                    } else {
                        sentence = String.format("%s %s", name, simpleIrregularVerb + "ed");
                    }
                    wrongV3PassiveVerb = simpleIrregularVerb;
                }
            }
        }

        if (!viewModel.isSentenceInDB(wrongSentence)) {
            textViewSentence.setText(sentence);
        } else if (!viewModel.isV3VerbInDB(wrongV3PassiveVerb)){
            textViewSentence.setText(sentence);
        } else {
            onClickNext(view);
        }
    }

    private void setImageTypeOfSentence() {

    }

    private void setConstraintLayoutBackgroundImage(boolean b) {
        if (b) {
            constraintLayout.setBackground(getResources().getDrawable(listOfBackgroundImages.get(randomNumberOfTense)));
//                    getWindow().setBackgroundDrawableResource(R.drawable.table_filled);
        } else {
            constraintLayout.setBackground(getResources().getDrawable(R.drawable.tense_way_base));
        }
    }

    public String[] getArrayFromResources(int id) {
        return getString(id).split(",");
    }

    public String[] getArrayFromArrayResources(int id) {
        return getResources().getStringArray(id);
    }

    public ArrayList<String> getArrayListFromArgs(String[]... array) {
        ArrayList<String> list = new ArrayList<>();
        for (String[] s : array) {
            list.addAll(Arrays.asList(s));
        }
        return list;
    }

    private void addDrawableResources(List<Integer> list, int... id) {
        for (int i : id) {
            list.add(i);
        }
    }

    private void addWrongV3VerbToDB(String wrongV3Verb) {
        int maxIdV3Verb = viewModel.getMaxIdOfV3Verb();
        viewModel.insertV3Verb(new V3Verb(maxIdV3Verb + 1, wrongV3Verb));
        Toast.makeText(getApplicationContext(), "the sentence is added to the database", Toast.LENGTH_SHORT).show();
    }

    public void addWrongSentenceToDB(String wrongSentence) {
        int maxId = viewModel.getMaxId();
        viewModel.insertSentence(new Sentence(maxId + 1, wrongSentence));
        Toast.makeText(getApplicationContext(), "the sentence is added to the database", Toast.LENGTH_SHORT).show();
    }
}