package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.core.content.res.ResourcesCompat;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProviders;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.realyenglsh.Table1Activity.getWordFromList;

public class AlgorithmActivity extends AppCompatActivity {
    private ImageView imageViewTenseObject;
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
    List<Integer> listOfBackgroundImages;

    private String wrongSentence;
    private int randomNumberOfTense = -1;
    private boolean isSwitchShowPromptOn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        imageViewTenseObject = findViewById(R.id.imageTenseObject);
        imageViewTenseObject.setImageResource(R.drawable.coach);
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

        String[] stringArgsNames = getString(R.string.names).split("@");
        String[] stringArgsPersonalPronouns = getResources().getStringArray(R.array.personal_pronouns);
        listOfNames = new ArrayList<>(Arrays.asList(stringArgsNames));
        listOfNames.addAll(Arrays.asList(stringArgsPersonalPronouns));

        String[] stringArgsVerbsSimple = getResources().getString(R.string.simple_verbs).split("@");
        String[] stringArgsVerbsIrregular = getResources().getString(R.string.irregular_verbs).split("@");
        listOfVerbsSimpleIrregular = new ArrayList<>(Arrays.asList(stringArgsVerbsSimple));
        listOfVerbsSimpleIrregular.addAll(Arrays.asList(stringArgsVerbsIrregular));

        listOfVerbsIrregular = new ArrayList<>(Arrays.asList(stringArgsVerbsIrregular));

        String[] stringArgsVerbsIrregularPast = getResources().getString(R.string.irregular_verbs_past).split("@");
        listOfVerbsIrregularPast = new ArrayList<>(Arrays.asList(stringArgsVerbsIrregularPast));

        String[] stringArgsVerbsStrong = getResources().getStringArray(R.array.strong_verbs);
        listOfVerbsStrong = new ArrayList<>(Arrays.asList(stringArgsVerbsStrong));

        String[] stringArgsAdjective = getResources().getString(R.string.adjective).split("@");
        listOfAdjective = new ArrayList<>(Arrays.asList(stringArgsAdjective));

        buttonPutIntoDBWrongSentence.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if (Table1Activity.addWrongSentenceIntoDB(viewModel, AlgorithmActivity.this, wrongSentence)) {
                    onClickNext(v);
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
        textViewIrregularVerbPast.setText("--//--");
        int randomNumberOfSentence = random.nextInt(3);  // 0 - simple verbs, 1 - strong verbs, 2 - ing & adjective
        wrongSentence = "";
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
        }
        if (!viewModel.isSentenceInDB(wrongSentence)) {
            textViewSentence.setText(sentence);
        } else {
            onClickNext(view);
        }
    }

    private void setConstraintLayoutBackgroundImage(boolean b) {
        if (b) {
            constraintLayout.setBackground(getResources().getDrawable(listOfBackgroundImages.get(randomNumberOfTense)));
//                    getWindow().setBackgroundDrawableResource(R.drawable.table_filled);
        } else {
            constraintLayout.setBackground(getResources().getDrawable(R.drawable.tense_way_base));
        }
    }




}