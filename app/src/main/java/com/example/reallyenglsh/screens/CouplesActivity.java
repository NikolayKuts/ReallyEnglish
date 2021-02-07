package com.example.reallyenglsh.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.LinearLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.reallyenglsh.StringResourcesAssembler;
import com.example.reallyenglsh.adapters.MyAdapterListCouples;
import com.example.reallyenglsh.data.CoupleList;
import com.example.reallyenglsh.data.MainViewModel;
import com.example.realyenglsh.R;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CouplesActivity extends AppCompatActivity {

    private TextView textViewQuestion;
    private TextView textViewAnswer;
    private TextView textViewChosenLists;

    private List<CoupleList> listOfCoupleList = new ArrayList<>();
    private List<String> listLessonQuestion = new ArrayList<>();
    private List<String> listLessonAnswer = new ArrayList<>();
    private List<Integer> listSavedIndexesOfLearnedCouples = new ArrayList<>();

    private LinearLayout linearLayoutCounter;
    private TextView textViewQuantity;
    private TextView textViewRest;

    private MainViewModel viewModel;
    private boolean firstEnter = true;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_couples);

        textViewQuestion = findViewById(R.id.textViewQuestion);
        textViewAnswer = findViewById(R.id.textViewAnswer);
        textViewChosenLists = findViewById(R.id.textViewChosenLists);

        linearLayoutCounter = findViewById(R.id.linearLayoutCounter);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        textViewRest = findViewById(R.id.textViewRest);

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        @SuppressLint("UseSwitchCompatOrMaterialCode")
        Switch switchShowAnswer = findViewById(R.id.switchShowAnswer);

        switchShowAnswer.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                textViewAnswer.clearAnimation();
                if (isChecked) {
                    textViewAnswer.setVisibility(View.VISIBLE);
                } else {
                    textViewAnswer.setVisibility(View.INVISIBLE);
                }
            }
        });

        listOfCoupleList.add(getCoupleList("En to En [4]", true, R.array.couples_english_qu, R.array.couples_english_an));
        listOfCoupleList.add(getCoupleList("En to En WH [4]", false, R.array.couples_english_wh_qu, R.array.couples_english_wh_an));
        listOfCoupleList.add(getCoupleList("En to En advance [4]", false, R.array.couples_english_advanced_qu, R.array.couples_english_advanced_an));
        listOfCoupleList.add(getCoupleList("Ru to En [4]", false, R.array.couples_english_ru_to_en_qu, R.array.couples_english_ru_to_en_an));
        listOfCoupleList.add(getCoupleList("Past En to En ? [5]", false, R.array.couples_past_en_to_en_qu, R.array.couples_past_en_to_en_an));
        listOfCoupleList.add(getCoupleList("Negative sentences [6]", false, R.array.negative_sentence_ru_qu, R.array.negative_sentence_en_an));
        listOfCoupleList.add(getCoupleList("Adjective intensifiers [7]", false, R.array.adjective_intensifiers_so_very_too_ru_qu, R.array.adjective_intensifiers_so_very_too_en_an));
        listOfCoupleList.add(getCoupleList("Adjective is like verb [7]", false, R.array.adjective_is_like_verb_ru_qu, R.array.adjective_is_like_verb_en_an));
        listOfCoupleList.add(getCoupleList("Much & Many [8]", false, R.array.much_many_qu, R.array.much_many_an));
        listOfCoupleList.add(getCoupleList("Comparison of adjectives [9]", false, R.array.comparison_of_adjectives_qu, R.array.comparison_of_adjectives_an));
        listOfCoupleList.add(getCoupleList("Enough [9]", false, R.array.enough_qu, R.array.enough_an));
        listOfCoupleList.add(getCoupleList("Fake subject [10]", false, R.array.fake_subject_qu, R.array.fake_subject_an));
        listOfCoupleList.add(getCoupleList("Seem / Turn out [11]", false, R.array.seem_turn_out_qu, R.array.seem_turn_out_an));
        listOfCoupleList.add(getCoupleList("Mirroring #1 [12]", false, R.array.mirroring_1_qu, R.array.mirroring_1_an));
        listOfCoupleList.add(getCoupleList("Mirroring #2 [12]", false, R.array.mirroring_2_qu, R.array.mirroring_2_an));
        listOfCoupleList.add(getCoupleList("Have V3 [13]", false, R.array.have_v3_qu, R.array.have_v3_an));
        listOfCoupleList.add(getCoupleList("Express #1 [14]", false, R.array.express_1_qu, R.array.express_1_an));
        listOfCoupleList.add(getCoupleList("Object case [15]", false, R.array.object_case_qu, R.array.object_case_an));
        listOfCoupleList.add(getCoupleList("Possessive case [15]", false, R.array.possessive_case_qu, R.array.possessive_case_an));
        listOfCoupleList.add(getCoupleList("Strengthened forms of pos. case [15]", false, R.array.strengthened_forms_of_pos_case_qu, R.array.strengthened_forms_of_pos_case_an));
        listOfCoupleList.add(getCoupleList("Self [15]", false, R.array.self_qu, R.array.self_an));
        listOfCoupleList.add(getCoupleList("Is about [15]", false, R.array.formulas_for_self_assembly_is_about_qu, R.array.formulas_for_self_assembly_is_about_an));
        listOfCoupleList.add(getCoupleList("In a __ way [15]", false, R.array.formulas_for_self_assembly_in_a_way_qu, R.array.formulas_for_self_assembly_in_a_way_an));
        listOfCoupleList.add(getCoupleList("On __ own [15]", false, R.array.formulas_for_self_assembly_on_own_qu, R.array.formulas_for_self_assembly_on_own_an));
        listOfCoupleList.add(getCoupleList("No matter [15]", false, R.array.formulas_for_self_assembly_no_matter_qu, R.array.formulas_for_self_assembly_no_matter_an));
        listOfCoupleList.add(getCoupleList("Out of [15]", false, R.array.formulas_for_self_assembly_out_of_qu, R.array.formulas_for_self_assembly_out_of_an));
        listOfCoupleList.add(getCoupleList("- Ever [15]", false, R.array.formulas_for_self_assembly_ever_qu, R.array.formulas_for_self_assembly_ever_an));
        listOfCoupleList.add(getCoupleList("Bridges [16]", false, R.array.bridges_qu, R.array.bridges_an));
        listOfCoupleList.add(getCoupleList("Condition [17]", false, R.array.condition_qu, R.array.condition_an));
        listOfCoupleList.add(getCoupleList("Preps [18]", false, R.array.preps_qu, R.array.preps_an));
        listOfCoupleList.add(getCoupleList("In On At [19]", false, R.array.in_on_at_qu, R.array.in_on_at_an));
        listOfCoupleList.add(getCoupleList("Phrasal verbs [19]", false, R.array.phrasal_verbs_qu, R.array.phrasal_verbs_an));
        listOfCoupleList.add(getCoupleList("Preps + verb-ing [20]", false, R.array.preps_verb_ing_qu, R.array.preps_verb_ing_an));
        listOfCoupleList.add(getCoupleList("Starting from infinitive [21]", false, R.array.starting_from_infinitive_qu, R.array.starting_from_infinitive_an));
        listOfCoupleList.add(getCoupleList("Sequence of tenses [22]", false, R.array.sequence_of_tenses_qu, R.array.sequence_of_tenses_an));
        listOfCoupleList.add(getCoupleList("Miscellaneous [22]", false, R.array.miscellaneous_qu, R.array.miscellaneous_an));
        listOfCoupleList.add(getCoupleList("Degetization [23]", false, R.array.degetization_qu, R.array.degetization_an));
        listOfCoupleList.add(getCoupleList("Make & Have [23]", false, R.array.make_have_qu, R.array.make_have_an));
        listOfCoupleList.add(getCoupleList("Word formation # 1 [24]", false, R.array.word_formation_1_qu, R.array.word_formation_1_an));
        listOfCoupleList.add(getCoupleList("Word formation # 2 [24]", false, R.array.word_formation_2_qu, R.array.word_formation_2_an));
        listOfCoupleList.add(getCoupleList("Twins [25]", false, R.array.twins_qu, R.array.twins_an));
        listOfCoupleList.add(getCoupleList("Triangle [26]", false, R.array.triangle_qu, R.array.triangle_an));
        listOfCoupleList.add(getCoupleList("Twins # 2 [27]", false, R.array.twins_2_qu, R.array.twins_2_an));
        listOfCoupleList.add(getCoupleList("Openers [30]", false, R.array.openers_qu, R.array.openers_an));


        saveChosenListsIfDBIsEmpty();
        setCheckedCoupleListsBySavedInDB(firstEnter);
        firstEnter = false;

        setContentForLesson();
        setContentForTextViewChosenLists();
        onClickNextCouples(textViewAnswer);


    }


    private void setContentForLesson() {
        listLessonQuestion.clear();
        listLessonAnswer.clear();

        for (CoupleList my : listOfCoupleList) {
            if (my.isChecked()) {
                listLessonQuestion.addAll(my.getListQuestion());
                listLessonAnswer.addAll(my.getListAnswers());
            }
        }
    }

    public void onClickShowDialogLists(View view) {
        Dialog dialog = new Dialog(this);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.setContentView(R.layout.dialog_couples);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerViewCouplesList);
        MyAdapterListCouples adapter = new MyAdapterListCouples(listOfCoupleList);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        Button button = dialog.findViewById(R.id.buttonApplyLists);
        dialog.show();
        setSmoothScrollToLastItem(recyclerView);

        button.setOnClickListener(v -> {
            dialog.dismiss();
            saveChosenLists();
            setCheckedCoupleListsBySavedInDB(firstEnter);
            setOneCheckedIfAbsentChecked(listOfCoupleList);
            setContentForLesson();
            listSavedIndexesOfLearnedCouples = getFullListBySizeOfLessonList();
            onClickNextCouples(v);
            setContentForTextViewChosenLists();
        });
    }

    private CoupleList getCoupleList(String nameOfList, boolean isChecked, int idResCouples, int idResAnswers) {
        StringResourcesAssembler assembler = new StringResourcesAssembler(this);
        List<String> listCouples = assembler.getListFormArrayRes(idResCouples);
        List<String> listAnswers = assembler.getListFormArrayRes(idResAnswers);

        return new CoupleList(nameOfList, isChecked, listCouples, listAnswers);
    }

    private void setContentCounter() {
        textViewQuantity.setText(String.format("%s", listLessonQuestion.size()));
        textViewRest.setText(String.format("%s", listSavedIndexesOfLearnedCouples.size()));
    }

    public void onClickNextCouples(View view) {
        int randomNumber = getRandomNumberOnWasNot();
        textViewQuestion.setText(listLessonQuestion.get(randomNumber));
        textViewAnswer.setText(listLessonAnswer.get(randomNumber));
        setContentCounter();

        actAnimationOnTextViewRest();
        actAnimationOnNewIteration();
        actAnimationOnTextViewCouples();
        actAnimationOnTextViewAnswer();
    }

    private void actAnimationOnNewIteration() {
        if (listLessonQuestion.size() - 1 == listSavedIndexesOfLearnedCouples.size()) {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.blink_anim);
            linearLayoutCounter.startAnimation(animation);
        }
    }

    private void actAnimationOnTextViewCouples() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sample_anim);
        textViewQuestion.startAnimation(animation);
    }

    private void actAnimationOnTextViewAnswer() {
        if (textViewAnswer.getVisibility() == View.VISIBLE) {
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.sample_anim_from_right);
            textViewAnswer.startAnimation(animation);
        }
    }

    private void actAnimationOnTextViewRest() {
        Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
        textViewRest.startAnimation(animation);
    }

    private int getRandomNumberOnWasNot() {
        Random random = new Random();
        int randomNumber;

        if (!listSavedIndexesOfLearnedCouples.isEmpty()) {
            randomNumber = listSavedIndexesOfLearnedCouples.get(random.nextInt(listSavedIndexesOfLearnedCouples.size()));
        } else {
            listSavedIndexesOfLearnedCouples = getFullListBySizeOfLessonList();
            randomNumber = random.nextInt(listSavedIndexesOfLearnedCouples.size());
        }
        listSavedIndexesOfLearnedCouples.remove(listSavedIndexesOfLearnedCouples.indexOf(randomNumber));
        return randomNumber;
    }

    private ArrayList<Integer> getFullListBySizeOfLessonList() {
        ArrayList<Integer> list = new ArrayList<>();
        for (int i = 0; i < listLessonQuestion.size(); i++) {
            list.add(i);
        }
        return list;
    }

    private void setContentForTextViewChosenLists() {
        StringBuilder namesChosenLists = new StringBuilder();
        for (CoupleList my : listOfCoupleList) {
            if (my.isChecked()) {
                if (namesChosenLists.length() == 0) {
                    namesChosenLists.append(my.getNameList());
                } else {
                    namesChosenLists.append(",  ").append(my.getNameList());
                }
            }
        }
        textViewChosenLists.setText(namesChosenLists.toString());
    }

    private void setSmoothScrollToLastItem(RecyclerView recyclerView) {
        for (int i = listOfCoupleList.size() - 1; i > 0; i--) {
            if (listOfCoupleList.get(i).isChecked()) {
                recyclerView.smoothScrollBy(0, i * 80, null, i * 100);
                break;
            }
        }
    }

    private void saveChosenLists() {
        viewModel.insertListOfCoupleList(listOfCoupleList);
    }

    private void setCheckedCoupleListsBySavedInDB(boolean firstEnter) {
        if (firstEnter) {
            List<CoupleList> listOfCoupleListFromDB = viewModel.getListOfSavedCoupleListInDB();
            for (int i = 0; i < listOfCoupleList.size(); i++) {
                CoupleList coupleList = listOfCoupleList.get(i);
                CoupleList savedCoupleListInDB = listOfCoupleListFromDB.get(i);
                coupleList.setChecked(savedCoupleListInDB.isChecked());
            }
        }
    }

    private void saveChosenListsIfDBIsEmpty() {
        List<CoupleList> listOfSavedCoupleList = viewModel.getListOfSavedCoupleListInDB();
        boolean checked = isThereChecked(listOfSavedCoupleList);
        if (viewModel.isDBSavedCoupleListsEmpty() || !checked) {
            saveChosenLists();
        }
    }

    private boolean isThereChecked(List<CoupleList> listOfCoupleList) {
        boolean checked = false;
        for (CoupleList coupleList : listOfCoupleList) {
            if (coupleList.isChecked()) {
                checked = true;
                break;
            }
        }
        return checked;
    }

    private void setOneCheckedIfAbsentChecked(List<CoupleList> listOfCoupleList) {
        if (!isThereChecked(listOfCoupleList)) {
            listOfCoupleList.get(0).setChecked(true);
        }
    }

}