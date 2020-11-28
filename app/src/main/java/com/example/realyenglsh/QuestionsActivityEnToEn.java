package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.util.Log;
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

        listOfMyTranslateList.add(getMyListTranslating("En to En [4]", true, R.array.question_english, R.array.question_english_answers));
        listOfMyTranslateList.add(getMyListTranslating("En to En WH [4]", false, R.array.question_english_wh, R.array.question_english_wh_answers));
        listOfMyTranslateList.add(getMyListTranslating("En to En advance [4]", false, R.array.question_english_advanced, R.array.question_english_advanced_answers));
        listOfMyTranslateList.add(getMyListTranslating("Ru to En [4]", false, R.array.question_english_ru_to_en, R.array.question_english_ru_to_en_answers));
        listOfMyTranslateList.add(getMyListTranslating("Past En to En [5]", false, R.array.question_past_en_to_en, R.array.question_past_en_to_en_answer));
        listOfMyTranslateList.add(getMyListTranslating("Negative sentences [6]", false, R.array.negative_sentence_ru_q, R.array.negative_sentence_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Adjective intensifiers [7]", false, R.array.adjective_intensifiers_so_very_too_ru_q, R.array.adjective_intensifiers_so_very_too_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Adjective likes verb [7]", false, R.array.adjective_likes_verb_ru_q, R.array.adjective_likes_verb_en_a));
        listOfMyTranslateList.add(getMyListTranslating("Much & Many [8]", false, R.array.much_many_q, R.array.much_many_a));
        listOfMyTranslateList.add(getMyListTranslating("Comparison of adjectives [9]", false, R.array.comparison_of_adjectives_q, R.array.comparison_of_adjectives_a));
        listOfMyTranslateList.add(getMyListTranslating("Enough [9]", false, R.array.enough_q, R.array.enough_a));
        listOfMyTranslateList.add(getMyListTranslating("Fake subject [10]", false, R.array.fake_subject_q, R.array.fake_subject_a));
        listOfMyTranslateList.add(getMyListTranslating("Seem / Turn out [11]", false, R.array.seem_turn_out_q, R.array.seem_turn_out_q));


        setContentForLesson();

        String s = "⦁\tIt can take much time.\n" +
                "⦁\tShe thought about her children.\n" +
                "⦁\tThe boss pays you good money.\n" +
                "⦁\tYou were at home. \n" +
                "⦁\tYou will see him there.\n" +
                "⦁\tHe may start the work.\n" +
                "⦁\tThey heard this story.  \n" +
                "⦁\tYou will be able to come tomorrow. \n" +
                "⦁\tShe has so many problems with her son. \n" +
                "⦁\tI need to go there.\n" +
                "⦁\tI had to do it yesterday. \n" +
                "⦁\tHe likes expensive cars.\n" +
                "⦁\tYou may do it.\n" +
                "⦁\tYou had to help these poor people.\n" +
                "⦁\tShe should come to you.\n" +
                "⦁\tHe learns to be a boss.\n" +
                "⦁\tHe wanted so much to work with us.\n" +
                "⦁\tShe eats so much chocolate. She will be fat.\n" +
                "⦁\tWe must eat only healthy food.\n" +
                "⦁\tThey lived in Israel.\n" +
                "⦁\tYou are so smart.\n" +
                "⦁\tYou want to learn this language.\n" +
                "⦁\tYou paid for this pizza 10 $.\n" +
                "⦁\tThey need our help.\n" +
                "⦁\tWe will have to explain them how to work. \n" +
                "⦁\tShe is so happy to see us.\n" +
                "⦁\tThey will be allowed to rest an hour or two.  \n" +
                "⦁\tYou were so tired.   \n" +
                "⦁\tPeople came there to rest.\n" +
                "⦁\tShe wants to tell me a story.\n" +
                "⦁\tWe may rest now. \n";

        s = s.replaceAll("⦁\\t", "");
        Log.i("log", s);

        String[] array = s.split("\\s*\\n\\s*");
        for (String q : array) {
            Log.i("log", "<item>" + q + "</item>");
        }

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
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
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

        ArrayList<String> listQuestions = algorithmActivity.getArrayListFromStringRes(this, idResQuestions);
        List<String> listAnswers = algorithmActivity.getArrayListFromStringRes(this, idResAnswers);

        return new MyListTranslating(nameOfList, isChecked, listQuestions, listAnswers);
    }

    public void onClickNextQuestion(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(listLessonQuestions.size());
        textViewQuestion.setText(listLessonQuestions.get(randomNumber));
        textViewAnswer.setText(listLessonAnswer.get(randomNumber));
    }
}