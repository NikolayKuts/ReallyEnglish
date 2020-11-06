package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
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

    private List<String> arrayListQuestion;
    private List<String> arrayListAnswer;
    private List<String> arrayListQuestionWH;
    private List<String> arrayListAnswerWH;
    private List<String> arrayListQuestionAdvanced;
    private List<String> arrayListAnswerAdvanced;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_en_to_ru);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        switchShowAnswer = findViewById(R.id.switchShowAnswer);

        arrayListQuestion = Arrays.asList(getResources().getStringArray(R.array.question_english));
        arrayListAnswer = Arrays.asList(getResources().getStringArray(R.array.question_english_answers));

        arrayListQuestionWH = Arrays.asList(getResources().getStringArray(R.array.question_english_wh));
        arrayListAnswerWH = Arrays.asList(getResources().getStringArray(R.array.question_english_wh_answer));

        arrayListQuestionAdvanced = Arrays.asList(getResources().getStringArray(R.array.question_english_advanced));
        arrayListAnswerAdvanced = Arrays.asList(getResources().getStringArray(R.array.question_english_advanced_answer));


        

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


        String string = "";

        String[] array = string.split("(\\s+)?\\n(\\n)?");
        Log.i("log", Arrays.asList(array).toString());
        Log.i("log", "--------length--------" +array.length);
        int i = 0;
        for (String s : array) {
            if (s.matches(".+[А-Яа-я].\\?")) {
                Log.i("log", "<item>" + s + "</item>");
            }
        }

        Log.i("log", "--------length--------" +array.length);
        i = 0;
        for (String s : array) {
            if (s.matches(".+[^А-Яа-я].\\?")) {
                Log.i("log", "<item>" + s + "</item>");
            }
        }
    }


    public void onClickNextQuestion(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(arrayListQuestion.size());
        textViewQuestion.setText(arrayListQuestion.get(randomNumber));
        textViewAnswer.setText(arrayListAnswer.get(randomNumber));
    }
}