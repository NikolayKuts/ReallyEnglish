package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class QuestionsActivityEnToEn extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewAnswer;
    private Switch switchShowAnswer;

    private List<Integer> arrayListCheckBoxes = new ArrayList<>();

    private List<String> arrayListOfLessonQuestions = new ArrayList<>();
    private List<String> arrayListOfLessonAnswer = new ArrayList<>();

    private CheckBox checkBoxEnToRu;
    private CheckBox checkBoxEnToRuWH;
    private CheckBox checkBoxEnToRuAdvance;
    private CheckBox checkBoxRuToEn;
    private CheckBox checkBoxPastEnToEn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_en_to_ru);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        switchShowAnswer = findViewById(R.id.switchShowAnswer);
        checkBoxEnToRu = findViewById(R.id.checkBoxEnToEn);
        checkBoxEnToRuWH = findViewById(R.id.checkBoxEnToEnWH);
        checkBoxEnToRuAdvance = findViewById(R.id.checkBoxEnToEnAdvance);
        checkBoxRuToEn = findViewById(R.id.checkBoxRuToEn);
        checkBoxPastEnToEn = findViewById(R.id.checkBoxPastEnToEn);

        checkBoxEnToRu.setOnClickListener(new OnCheckBoxChangeListener(0, checkBoxEnToRu, arrayListCheckBoxes));
        checkBoxEnToRuWH.setOnClickListener(new OnCheckBoxChangeListener(1, checkBoxEnToRuWH, arrayListCheckBoxes));
        checkBoxEnToRuAdvance.setOnClickListener(new OnCheckBoxChangeListener(2, checkBoxEnToRuAdvance, arrayListCheckBoxes));
        checkBoxRuToEn.setOnClickListener(new OnCheckBoxChangeListener(3, checkBoxRuToEn, arrayListCheckBoxes));

        checkBoxPastEnToEn.setOnClickListener(new OnCheckBoxChangeListener_0(4, checkBoxPastEnToEn));


        switchShowAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewAnswer.setVisibility(View.VISIBLE);
                } else {
                    textViewAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });

        arrayListCheckBoxes.add(0);
        setContentLesson();


    }

    private void setContentLesson() {
        arrayListOfLessonQuestions.clear();
        arrayListOfLessonAnswer.clear();

        if (arrayListCheckBoxes.contains(0)) {
            addFromResources(R.array.question_english, R.array.question_english_answers);
        }
        if (arrayListCheckBoxes.contains(1)) {
            addFromResources(R.array.question_english_wh, R.array.question_english_wh_answers);
        }
        if (arrayListCheckBoxes.contains(2)) {
            addFromResources(R.array.question_english_advanced, R.array.question_english_advanced_answers);
        }
        if (arrayListCheckBoxes.contains(3)) {
            addFromResources(R.array.question_english_ru_to_en, R.array.question_english_ru_to_en_answers);
        }
        if (arrayListCheckBoxes.contains(4)) {
            addFromResources(R.array.question_past_en_to_en, R.array.question_past_en_to_en_answer);
        }
    }

    private void addFromResources(int idQuestion, int idAnswer) {
        arrayListOfLessonQuestions.addAll(Arrays.asList(getResources().getStringArray(idQuestion)));
        arrayListOfLessonAnswer.addAll(Arrays.asList(getResources().getStringArray(idAnswer)));
    }


    public void onClickNextQuestion(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(arrayListOfLessonQuestions.size());
        textViewQuestion.setText(arrayListOfLessonQuestions.get(randomNumber));
        textViewAnswer.setText(arrayListOfLessonAnswer.get(randomNumber));
    }

    private class OnCheckBoxChangeListener implements View.OnClickListener{
        private int id;
        private CheckBox checkBox;
        private List<Integer> listOfCheckBoxes;

        public OnCheckBoxChangeListener(int id, CheckBox checkBox, List<Integer> listOfCheckBoxes) {
            this.id = id;
            this.checkBox = checkBox;
            this.listOfCheckBoxes = listOfCheckBoxes;
        }

        @Override
        public void onClick(View v) {
            if (checkBox.isChecked()) {
                listOfCheckBoxes.add(id);
            } else {
                listOfCheckBoxes.remove(listOfCheckBoxes.indexOf(id));
            }
            setContentLesson();
        }
    }

    private class OnCheckBoxChangeListener_0 implements View.OnClickListener {
        private int id;
        private CheckBox checkBox;

        OnCheckBoxChangeListener_0(int id, CheckBox checkBox) {
            this.id = id;
            this.checkBox = checkBox;
        }

        @Override
        public void onClick(View v) {
            if (checkBox.isChecked()) {
                arrayListCheckBoxes.add(id);
            } else {
                arrayListCheckBoxes.remove(arrayListCheckBoxes.indexOf(id));
            }
            setContentLesson();
        }
    }
}