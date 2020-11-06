package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.realyenglsh.Table1Activity.getWordFromList;

public class AlgorithmActivity extends AppCompatActivity {
    private ImageView imageViewTenseObject, imageViewTypeOfSentence;
    private TextView textViewSentence;
    private TextView textViewV1, textViewV2, textViewV3;
    private CheckBox checkBoxTenseFuture, checkBoxTensePresent, checkBoxTensePast;
    private CheckBox checkBoxTypeOfVerbSimple, checkBoxTypeOfVerbStrong, checkBoxTypeOfVerbToBe;
    private Switch switchShowPrompt, switchIrregularPastVerb;
    private MainViewModel viewModel;
    private ConstraintLayout constraintLayout;
    private Button buttonPutIntoDBWrongSentence;

    private LinearLayout linearLayoutVerbsForm;
    private Button buttonTestDialog;


    private List<MyListOfVerbs> listOfMyList = new ArrayList<>();
    private List<String> listOfLessonVerbsSimple;
    private List<String> listOfLessonVerbsIrregularV1;
    private List<String> listOfLessonVerbsIrregularV2;
    private List<String> listOfLessonVerbsIrregularV3;
    //    private List<List<String>> listOfLessonVerbsIrregularV1;
//    private List<List<String>> listOfLessonVerbsIrregularV2;
//    private List<List<String>> listOfLessonVerbsIrregularV3;
//
    ;


    private List<Integer> idTenseObject, listOfBackgroundImages, listOfImageTypeOfSentence;
    private List<String> listOfNames;

    private List<String> listOfVerbsStrong;
    private List<String> listOfAdjective;

    private String wrongSentence = "", wrongV3PassiveVerb = "";
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

        textViewV1 = findViewById(R.id.textViewV1);
        textViewV2 = findViewById(R.id.textViewV2);
        textViewV3 = findViewById(R.id.textViewV3);
        linearLayoutVerbsForm = findViewById(R.id.linearLayoutVerbForms);
        buttonTestDialog = findViewById(R.id.buttonShowDialog);

        buttonPutIntoDBWrongSentence = findViewById(R.id.buttonPutIntoDBWrongSentence);
        switchIrregularPastVerb = findViewById(R.id.switchIrregularVerb);
        switchShowPrompt = findViewById(R.id.switchShowPrompt);
        constraintLayout = findViewById(R.id.windowAlgorithmActivity);
        checkBoxTenseFuture = findViewById(R.id.checkBoxTenseFuture);
        checkBoxTensePresent = findViewById(R.id.checkBoxTensePresent);
        checkBoxTensePast = findViewById(R.id.checkBoxTensePast);
        checkBoxTypeOfVerbSimple = findViewById(R.id.checkBoxSimple);
        checkBoxTypeOfVerbStrong = findViewById(R.id.checkBoxStrong);
        checkBoxTypeOfVerbToBe = findViewById(R.id.checkBoxToBe);

        checkBoxTenseFuture.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTenseFuture, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));
        checkBoxTensePresent.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTensePresent, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));
        checkBoxTensePast.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTensePast, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));

        checkBoxTypeOfVerbSimple.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbSimple, R.color.check_box_checked_color, R.color.check_box_unchecked_color));
        checkBoxTypeOfVerbStrong.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbStrong, R.color.check_box_checked_color, R.color.check_box_unchecked_color));
        checkBoxTypeOfVerbToBe.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbToBe, R.color.check_box_checked_color, R.color.check_box_unchecked_color));

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

        listOfNames = getArrayListFromStringResources(R.array.personal_pronouns, R.string.names);
        listOfVerbsStrong = getArrayListFromStringResources(R.array.strong_verbs);
        listOfAdjective = getArrayListFromStringResources(null, R.string.adjective);


        List<String> listOfVerbsSimple_1 = getArrayListFromStringResources(null, R.string.simple_verbs_1);
        List<String> listOfVerbsIrregularV1 = getArrayListFromStringResources(null, R.string.irregular_verbs_v1_1);
        List<String> listOfVerbsIrregularV2 = getArrayListFromStringResources(null, R.string.irregular_verbs_v2_1);
        List<String> listOfVerbsIrregularV3 = getArrayListFromStringResources(null, R.string.irregular_verbs_v3_1);

        List<String> listOfVerbsSimple_2 = getArrayListFromStringResources(null, R.string.simple_verbs_2);
        List<String> listOfVerbsIrregularV1_2 = getArrayListFromStringResources(null, R.string.irregular_verbs_v1_2);
        List<String> listOfVerbsIrregularV2_2 = getArrayListFromStringResources(null, R.string.irregular_verbs_v2_2);
        List<String> listOfVerbsIrregularV3_2 = getArrayListFromStringResources(null, R.string.irregular_verbs_v3_2);

        listOfLessonVerbsSimple = new ArrayList<>();
        listOfLessonVerbsIrregularV1 = new ArrayList<>();
        listOfLessonVerbsIrregularV2 = new ArrayList<>();
        listOfLessonVerbsIrregularV3 = new ArrayList<>();

        listOfMyList.add(new MyListOfVerbs("Verbs # 1 (1 - 50)", true, listOfVerbsSimple_1, listOfVerbsIrregularV1, listOfVerbsIrregularV2, listOfVerbsIrregularV3));
        listOfMyList.add(new MyListOfVerbs("Verbs # 2 (51 - 100)", listOfVerbsSimple_2, listOfVerbsIrregularV1_2, listOfVerbsIrregularV2_2, listOfVerbsIrregularV3_2));
        listOfMyList.add(new MyListOfVerbs("Verbs # 3", listOfVerbsSimple_2, listOfVerbsIrregularV1_2, listOfVerbsIrregularV2_2, listOfVerbsIrregularV3_2));
        listOfMyList.add(new MyListOfVerbs("Verbs # 4", listOfVerbsSimple_2, listOfVerbsIrregularV1_2, listOfVerbsIrregularV2_2, listOfVerbsIrregularV3_2));
        listOfMyList.add(new MyListOfVerbs("Verbs # 5", listOfVerbsSimple_2, listOfVerbsIrregularV1_2, listOfVerbsIrregularV2_2, listOfVerbsIrregularV3_2));
        listOfMyList.add(new MyListOfVerbs("Verbs # 6", listOfVerbsSimple_2, listOfVerbsIrregularV1_2, listOfVerbsIrregularV2_2, listOfVerbsIrregularV3_2));

        setCheckedMyListOfVerbs();


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
                    linearLayoutVerbsForm.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutVerbsForm.setVisibility(View.INVISIBLE);
                }
            }
        });
    }

    public void onClickNext(View view) {
        Random random = new Random();
        setConstraintLayoutBackgroundImage(isSwitchShowPromptOn);
        randomNumberOfTense = getRandomNumberOfTenseOrTypeOfVerb(checkBoxTenseFuture, checkBoxTensePresent, checkBoxTensePast);  // 0 - future, 1 - present, 2 - past
        if (randomNumberOfTense != -1) {
            imageViewTenseObject.setVisibility(View.VISIBLE);
            imageViewTenseObject.setImageResource(idTenseObject.get(randomNumberOfTense));
            imageViewTenseObject.setVisibility(View.VISIBLE);
        } else {
            imageViewTenseObject.setVisibility(View.INVISIBLE);
        }
//        imageViewTypeOfSentence.setImageResource(listOfImageTypeOfSentence.get(randomNumberImageTypeOfSentence));
        setEmptyOnTextViewOfFormsOfIrregularVerb();
        wrongSentence = "";
        wrongV3PassiveVerb = "";
        String sentence = "";
        String name = getWordFromList(listOfNames);
        ArrayList<String> listOfLessonVerbsSimpleIrregular = new ArrayList<>(listOfLessonVerbsSimple);
        listOfLessonVerbsSimpleIrregular.addAll(listOfLessonVerbsIrregularV1);
        String simpleIrregularVerb = getWordFromList(listOfLessonVerbsSimpleIrregular);

        int randomNumberOfSentence = getRandomNumberOfTenseOrTypeOfVerb(checkBoxTypeOfVerbSimple, checkBoxTypeOfVerbStrong, checkBoxTypeOfVerbToBe);   // 0 - simple verbs, 1 - strong verbs, 2 (else) - ing & adjective
        if (randomNumberOfSentence == -1) {
            showToast();
        } else {
            if (randomNumberOfSentence == 0) {   // simple
                sentence = getSentenceWithSimpleVerb(name, simpleIrregularVerb, listOfLessonVerbsSimpleIrregular);
                setFormsOfIrregularVerb(simpleIrregularVerb);
            } else if (randomNumberOfSentence == 1) {    // strong
                sentence = getSentenceWithStrongVerb(name, simpleIrregularVerb);
                setFormsOfIrregularVerb(simpleIrregularVerb);
            } else {    // ToBe
                int randomNumberOfToBe = random.nextInt(3);

                if (randomNumberOfToBe == 0) {  // - ing
                    sentence = getSentenceWithToBeIngForm(name, simpleIrregularVerb);
                } else if (randomNumberOfToBe == 1) {  // adjective
                    String adjective = getWordFromList(listOfAdjective);
                    sentence = String.format("%s %s", name, adjective);
                } else {  // passive verb
                    if (listOfLessonVerbsIrregularV1.contains(simpleIrregularVerb)) {
                        sentence = getSentenceWithToBeV3Verb(name, simpleIrregularVerb);
                        setFormsOfIrregularVerb(simpleIrregularVerb);
                    } else {  // simple -ed
                        sentence = getSentenceWithToBeSimplePassiveVerb(name, simpleIrregularVerb);
                    }
                }
            }
        }

        if (!viewModel.isSentenceInDB(wrongSentence)) {
            textViewSentence.setText(sentence);
        } else if (!viewModel.isV3VerbInDB(wrongV3PassiveVerb)) {
            textViewSentence.setText(sentence);
        } else {
            onClickNext(view);
        }
    }

    private void showToast() {
        LayoutInflater inflater = getLayoutInflater();
        View layout = inflater.inflate(R.layout.toast_layout, (ViewGroup) findViewById(R.id.toast_root));

        Toast toast = new Toast(getApplicationContext());
        toast.setGravity(Gravity.CENTER, 0, -550);
        toast.setDuration(Toast.LENGTH_LONG);
        toast.setView(layout);
        toast.show();
    }

    private void setEmptyOnTextViewOfFormsOfIrregularVerb() {
        textViewV1.setText("-//-");
        textViewV2.setText("-//-");
        textViewV3.setText("-//-");
    }

    private void setFormsOfIrregularVerb(String verb) {
        if (listOfLessonVerbsIrregularV1.contains(verb)) {
            int index = listOfLessonVerbsIrregularV1.indexOf(verb);
            textViewV1.setText(listOfLessonVerbsIrregularV1.get(index));
            textViewV2.setText(listOfLessonVerbsIrregularV2.get(index));
            textViewV3.setText(listOfLessonVerbsIrregularV3.get(index));
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

    public String[] getArrayFromArrayResources(int id) {
        return getResources().getStringArray(id);
    }

    public String[] getArrayFromResources(int id) {
        return getString(id).split(",");
    }

    public ArrayList<String> getArrayListFromStringResources(Integer idArrayResource, int... idResource) {
        List<String[]> listOfArgsId = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();

        if (idArrayResource != null) {
            listOfArgsId.add(getArrayFromArrayResources(idArrayResource));
        }
        for (int id : idResource) {
            listOfArgsId.add(getArrayFromResources(id));
        }
        for (String[] s : listOfArgsId) {
            stringList.addAll(Arrays.asList(s));
        }
        return stringList;
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

    private int getRandomNumberOfTenseOrTypeOfVerb(CheckBox cb1, CheckBox cb2, CheckBox cb3) {
        Random random = new Random();
        List<Integer> list = new ArrayList<>();
        if (cb1.isChecked()) {
            list.add(0);
        }
        if (cb2.isChecked()) {
            list.add(1);
        }
        if (cb3.isChecked()) {
            list.add(2);
        }
        if (list.size() != 0) {
            return list.get(random.nextInt(list.size()));
        }
        return -1;
    }

    public void onClickShowDialog(View view) {
        Dialog dialog = new Dialog(AlgorithmActivity.this);
        dialog.setContentView(R.layout.layout_dialog);
        ListView listView = dialog.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, listOfMyList);
        listView.setAdapter(adapter);

        Button button = dialog.findViewById(R.id.buttonDialogApply);
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                adapter.notifyDataSetChanged();
                dialog.dismiss();
                AlgorithmActivity.this.onResume();
            }
        });
    }

    private class TextColorOnCheckedSetter implements View.OnClickListener {
        private CheckBox checkBox;
        private int idColorOnChecked;
        private int idColorOnUnChecked;

        TextColorOnCheckedSetter(CheckBox checkBox, int idColorOnChecked, int idColorOnUnChecked) {
            this.checkBox = checkBox;
            this.idColorOnChecked = idColorOnChecked;
            this.idColorOnUnChecked = idColorOnUnChecked;
        }

        @Override
        public void onClick(View v) {
            if (checkBox.isChecked()) {
                checkBox.setTextColor(getResources().getColor(idColorOnChecked));
            } else {
                checkBox.setTextColor(getResources().getColor(idColorOnUnChecked));
            }
        }
    }

    private void setContentForLesson(MyListOfVerbs myListOfVerbs) {
        listOfLessonVerbsSimple.addAll(myListOfVerbs.getListOfVerbsSimple());
        listOfLessonVerbsIrregularV1.addAll(myListOfVerbs.getListOfVerbsIrregularV1());
        listOfLessonVerbsIrregularV2.addAll(myListOfVerbs.getListOfVerbsIrregularV2());
        listOfLessonVerbsIrregularV3.addAll(myListOfVerbs.getListOfVerbsIrregularV3());
    }

    private void setCheckedMyListOfVerbs() {
        listOfLessonVerbsSimple.clear();
        listOfLessonVerbsIrregularV1.clear();
        listOfLessonVerbsIrregularV2.clear();
        listOfLessonVerbsIrregularV3.clear();

        for (MyListOfVerbs my : listOfMyList) {
            if (my.isChecked()) {
                setContentForLesson(my);
            }
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        setCheckedMyListOfVerbs();
    }

    private String getSentenceWithSimpleVerb(String name, String simpleIrregularVerb, ArrayList<String> listOfLessonVerbsSimpleIrregular) {
        String simpleIrregularVerb1 = getWordFromList(listOfLessonVerbsSimpleIrregular);
        wrongSentence = String.format("%s_%s", simpleIrregularVerb, simpleIrregularVerb1);
        return String.format("%s %s %s", name, simpleIrregularVerb, simpleIrregularVerb1);
    }

    private String getSentenceWithStrongVerb(String name, String simpleIrregularVerb) {
        String strongVerb = getWordFromList(listOfVerbsStrong);
        if (randomNumberOfTense != 1 && (strongVerb.equals("would") || strongVerb.equals("should"))) {
            strongVerb = getWordFromList(Arrays.asList("can", "may", "must"));
        }
        wrongSentence = String.format("%s_%s", strongVerb, simpleIrregularVerb);
        return String.format("%s %s %s", name, strongVerb, simpleIrregularVerb);
    }

    private String getSentenceWithToBeIngForm(String name, String simpleIrregularVerb) {
        if (simpleIrregularVerb.endsWith("e")) {
            simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1);
        } else if (simpleIrregularVerb.endsWith("ie")) {
            simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 2);
        }
        return String.format("%s %sing", name, simpleIrregularVerb);
    }

    private String getSentenceWithToBeV3Verb(String name, String simpleIrregularVerb) {
        int index = listOfLessonVerbsIrregularV1.indexOf(simpleIrregularVerb);
        String irregularPastVerbV3 = listOfLessonVerbsIrregularV3.get(index);
        wrongV3PassiveVerb = irregularPastVerbV3;
        return String.format("%s %s", name, irregularPastVerbV3);
    }

    private String getSentenceWithToBeSimplePassiveVerb(String name, String simpleIrregularVerb) {
        String sentence = "";
        if (simpleIrregularVerb.endsWith("e")) {
            simpleIrregularVerb = simpleIrregularVerb + "d";
            sentence = String.format("%s %s", name, simpleIrregularVerb);
        } else if (simpleIrregularVerb.matches("\\w+[^aeiouy]y")) {
            simpleIrregularVerb = simpleIrregularVerb.substring(0, simpleIrregularVerb.length() - 1) + "ied";
            sentence = String.format("%s %s", name, simpleIrregularVerb);
        } else {          //else if (simpleIrregularVerb.matches("\\w+[aeiouy]y"))
            simpleIrregularVerb = simpleIrregularVerb + "ed";
            sentence = String.format("%s %s", name, simpleIrregularVerb);
        }
        wrongV3PassiveVerb = simpleIrregularVerb;
        return sentence;
    }
}