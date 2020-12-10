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

public class CouplesActivity extends AppCompatActivity {

    private TextView textViewCouples;
    private TextView textViewAnswer;
    private TextView textViewChosenLists;


    private List<MyListCouples> listOfMyCouplesList = new ArrayList<>();
    private List<String> listLessonQuestion = new ArrayList<>();
    private List<String> listLessonAnswer = new ArrayList<>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couples);

        textViewCouples = findViewById(R.id.textViewCouples);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewChosenLists = findViewById(R.id.textViewChosenLists);
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

        listOfMyCouplesList.add(getMyListCouples("En to En [4]", true, R.array.couples_english_qu, R.array.couples_english_an));
        listOfMyCouplesList.add(getMyListCouples("En to En WH [4]", false, R.array.couples_english_wh_qu, R.array.couples_english_wh_an));
        listOfMyCouplesList.add(getMyListCouples("En to En advance [4]", false, R.array.couples_english_advanced_qu, R.array.couples_english_advanced_an));
        listOfMyCouplesList.add(getMyListCouples("Ru to En [4]", false, R.array.couples_english_ru_to_en_qu, R.array.couples_english_ru_to_en_an));
        listOfMyCouplesList.add(getMyListCouples("Past En to En [5]", false, R.array.couples_past_en_to_en_qu, R.array.couples_past_en_to_en_an));
        listOfMyCouplesList.add(getMyListCouples("Negative sentences [6]", false, R.array.negative_sentence_ru_qu, R.array.negative_sentence_en_an));
        listOfMyCouplesList.add(getMyListCouples("Adjective intensifiers [7]", false, R.array.adjective_intensifiers_so_very_too_ru_qu, R.array.adjective_intensifiers_so_very_too_en_an));
        listOfMyCouplesList.add(getMyListCouples("Adjective is like verb [7]", false, R.array.adjective_is_like_verb_ru_qu, R.array.adjective_is_like_verb_en_an));
        listOfMyCouplesList.add(getMyListCouples("Much & Many [8]", false, R.array.much_many_qu, R.array.much_many_an));
        listOfMyCouplesList.add(getMyListCouples("Comparison of adjectives [9]", false, R.array.comparison_of_adjectives_qu, R.array.comparison_of_adjectives_an));
        listOfMyCouplesList.add(getMyListCouples("Enough [9]", false, R.array.enough_qu, R.array.enough_an));
        listOfMyCouplesList.add(getMyListCouples("Fake subject [10]", false, R.array.fake_subject_qu, R.array.fake_subject_an));
        listOfMyCouplesList.add(getMyListCouples("Seem / Turn out [11]", false, R.array.seem_turn_out_qu, R.array.seem_turn_out_an));
        listOfMyCouplesList.add(getMyListCouples("Mirroring #1 [12]", false, R.array.mirroring_1_qu, R.array.mirroring_1_an));
        listOfMyCouplesList.add(getMyListCouples("Mirroring #2 [12]", false, R.array.mirroring_2_qu, R.array.mirroring_2_an));
        listOfMyCouplesList.add(getMyListCouples("Have V3", false, R.array.have_v3_qu, R.array.have_v3_an));
        listOfMyCouplesList.add(getMyListCouples("Express # 1", false, R.array.express_1_qu, R.array.express_1_an));
        listOfMyCouplesList.add(getMyListCouples("Object case", false, R.array.object_case_qu, R.array.object_case_an));
        listOfMyCouplesList.add(getMyListCouples("Possessive case", false, R.array.possessive_case_qu, R.array.possessive_case_an));
        listOfMyCouplesList.add(getMyListCouples("Strengthened forms of pos. case", false, R.array.strengthened_forms_of_pos_case_qu, R.array.strengthened_forms_of_pos_case_an));
        listOfMyCouplesList.add(getMyListCouples("Self", false, R.array.self_qu, R.array.self_an));
        listOfMyCouplesList.add(getMyListCouples("Is about", false, R.array.formulas_for_self_assembly_is_about_qu, R.array.formulas_for_self_assembly_is_about_an));
        listOfMyCouplesList.add(getMyListCouples("In a__ way", false, R.array.formulas_for_self_assembly_in_a_way_qu, R.array.formulas_for_self_assembly_i_a_way_an));
        listOfMyCouplesList.add(getMyListCouples("On __ own", false, R.array.formulas_for_self_assembly_on_own_qu, R.array.formulas_for_self_assembly_on_own_an));
        listOfMyCouplesList.add(getMyListCouples("No matter", false, R.array.formulas_for_self_assembly_no_matter_qu, R.array.formulas_for_self_assembly_no_matter_an));
        listOfMyCouplesList.add(getMyListCouples("Out of", false, R.array.formulas_for_self_assembly_out_of_qu, R.array.formulas_for_self_assembly_out_of_an));
        listOfMyCouplesList.add(getMyListCouples("-Ever", false, R.array.formulas_for_self_assembly_ever_qu, R.array.formulas_for_self_assembly_ever_an));

        setContentForLesson();
        setContentForTextViewChosenLists();

        String s = "";

        s = s.replaceAll("\\d+\\.\\t", "");
        Log.i("log", s);


        String[] array = s.split("\\s*\\n\\s*");
        for (String q : array) {
            Log.i("log", "<item>" + q + "</item>");
        }

    }


    private void setContentForLesson() {
        listLessonQuestion.clear();
        listLessonAnswer.clear();

        for (MyListCouples my : listOfMyCouplesList) {
            if (my.isChecked()) {
                listLessonQuestion.addAll(my.getListCouples());
                listLessonAnswer.addAll(my.getListAnswers());
            }
        }

    }

    public void onClickShowDialogLists(View view) {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_couples);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerViewCouplesList);
        MyAdapterForCouples adapter = new MyAdapterForCouples(listOfMyCouplesList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button = dialog.findViewById(R.id.buttonApplyLists);
        dialog.show();
        setSmoothScrollToLastItem(recyclerView);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
                setContentForLesson();
                onClickNextCouples(v);
                setContentForTextViewChosenLists();
            }
        });
    }

    private MyListCouples getMyListCouples(String nameOfList, boolean isChecked, int idResCouples, int idResAnswers) {
        AlgorithmActivity algorithmActivity = new AlgorithmActivity();

        ArrayList<String> listCouples = algorithmActivity.getArrayListFromStringRes(this, idResCouples);
        List<String> listAnswers = algorithmActivity.getArrayListFromStringRes(this, idResAnswers);

        return new MyListCouples(nameOfList, isChecked, listCouples, listAnswers);
    }

    public void onClickNextCouples(View view) {
        Random random = new Random();
        int randomNumber = random.nextInt(listLessonQuestion.size());
        textViewCouples.setText(listLessonQuestion.get(randomNumber));
        textViewAnswer.setText(listLessonAnswer.get(randomNumber));
    }

    private void setContentForTextViewChosenLists() {
        StringBuilder namesChosenLists = new StringBuilder();
        for (MyListCouples my : listOfMyCouplesList) {
            if (my.isChecked()) {
                if (namesChosenLists.length() == 0) {
                    namesChosenLists.append(my.getNameOfList());
                } else {
                    namesChosenLists.append(", ").append(my.getNameOfList());
                }
            }
        }
        textViewChosenLists.setText(namesChosenLists.toString());
    }

    private void setSmoothScrollToLastItem(RecyclerView recyclerView) {
        for (int i = listOfMyCouplesList.size() - 1; i > 0; i--) {
            if (listOfMyCouplesList.get(i).isChecked()) {
                recyclerView.smoothScrollBy(0, i * 56, null, i * 300);
                break;
            }
        }
    }


}