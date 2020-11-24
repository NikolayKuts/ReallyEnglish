package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class QuestionsActivityEnToEn extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewAnswer;


    private List<MyListTranslating> listOfMyTranslateList = new ArrayList<>();
    private List<String> listLessonQuestions = new ArrayList<>();
    private List<String> listLessonAnswer = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_questions_en_to_ru);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        Switch switchShowAnswer = findViewById(R.id.switchShowAnswer);

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

        listOfMyTranslateList.add(getMyListTranslating("En to En", true, R.array.question_english, R.array.question_english_answers));
        listOfMyTranslateList.add(getMyListTranslating("En to En WH", false, R.array.question_english_wh, R.array.question_english_wh_answers));
        listOfMyTranslateList.add(getMyListTranslating("En to En advance", false, R.array.question_english_advanced, R.array.question_english_advanced_answers));
        listOfMyTranslateList.add(getMyListTranslating("Ru to En", false, R.array.question_english_ru_to_en, R.array.question_english_ru_to_en_answers));
        listOfMyTranslateList.add(getMyListTranslating("Past En to En [5]", false, R.array.question_past_en_to_en, R.array.question_past_en_to_en_answer));
        listOfMyTranslateList.add(getMyListTranslating("Negative sentences [6]", false, R.array.negative_sentence_ru_q, R.array.negative_sentence_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Adjective intensifiers [7]", false, R.array.adjective_intensifiers_so_very_too_ru_q, R.array.adjective_intensifiers_so_very_too_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Adjective likes verb [7]", false, R.array.adjective_likes_verb_ru_q, R.array.adjective_likes_verb_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Much & Many [8]", false, R.array.much_many_q, R.array.much_many_a));
        listOfMyTranslateList.add(getMyListTranslating("Comparison of adjectives [9]", false, R.array.comparison_of_adjectives_q, R.array.comparison_of_adjectives_a));
        listOfMyTranslateList.add(getMyListTranslating("Enough [9]", false, R.array.enough_q, R.array.enough_a));


        setContentForLesson();




    }


    private void setContentForLesson() {
        listLessonQuestions.clear();
        listLessonAnswer.clear();

        for (MyListTranslating my : listOfMyTranslateList) {
            if (my.isChecked()) {
                listLessonQuestions.addAll(my.getListQuestions());
                listLessonAnswer.addAll(my.getListAnswers());
            }
        }

    }

    public void onClickShowDialogLists(View view) {
        Dialog dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog_questions);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerViewQuestionsList);
        MyAdapterForTranslateList adapter = new MyAdapterForTranslateList(listOfMyTranslateList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button = dialog.findViewById(R.id.buttonApplyLists);
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setContentForLesson();
            }
        });
    }

    private MyListTranslating getMyListTranslating(String nameOfList, boolean isChecked, int idResQuestions, int idResAnswers) {
        AlgorithmActivity algorithmActivity = new AlgorithmActivity();

        ArrayList<String> listQuestions = algorithmActivity.getArrayListFromStringResources(this, idResQuestions);
        List<String> listAnswers = algorithmActivity.getArrayListFromStringResources(this, idResAnswers);

        return new MyListTranslating(nameOfList, isChecked, listQuestions, listAnswers);
    }

    public void onClickNextQuestion(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(listLessonQuestions.size());
        textViewQuestion.setText(listLessonQuestions.get(randomNumber));
        textViewAnswer.setText(listLessonAnswer.get(randomNumber));
    }
}