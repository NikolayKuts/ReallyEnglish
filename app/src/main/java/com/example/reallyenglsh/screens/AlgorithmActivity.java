  package com.example.reallyenglsh.screens;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.lifecycle.ViewModelProviders;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.Toast;

import com.example.reallyenglsh.DownLoader;
import com.example.reallyenglsh.IOnCallbackHelper;
import com.example.reallyenglsh.MainViewModel;
import com.example.reallyenglsh.MyAdapterAdjectiveList;
import com.example.reallyenglsh.MyLoaderCallbacks;
import com.example.reallyenglsh.data.V3Verb;
import com.example.reallyenglsh.MyAdapter;
import com.example.reallyenglsh.MyListAdjective;
import com.example.reallyenglsh.MyListOfVerbs;
import com.example.reallyenglsh.OnClickAudioContentPlayer;
import com.example.realyenglsh.R;
import com.example.reallyenglsh.data.Sentence;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import static com.example.reallyenglsh.screens.Table1Activity.getWordFromList;

public class AlgorithmActivity extends AppCompatActivity {
    private ImageView imageViewTenseObject, imageViewTypeOfSentence;
    private TextView textViewSentence;
    private TextView textViewV1, textViewV2, textViewV3;
    private CheckBox checkBoxTenseFuture, checkBoxTensePresent, checkBoxTensePast;
    private CheckBox checkBoxTypeOfVerbSimple, checkBoxTypeOfVerbStrong, checkBoxTypeOfVerbToBe;
    private CheckBox checkBoxTypeOfSentenceMinus, checkBoxTypeOfSentencePlus, checkBoxTypeOfSentenceQu, checkBoxTypeOfSentenceMinusQu;
    private Switch switchShowPrompt, switchShowHelpContent;
    private MainViewModel viewModel;
    private ConstraintLayout constraintLayout;
    private Button buttonPutIntoDBWrongSentence;
    private ImageButton imageButtonShowDialogVerbs, imageButtonShowDialogAdjective;

    private LinearLayout linearLayoutVerbsForm;

    private TextView textViewTranslation;

    private LoaderManager loaderManager;
    private MyLoaderCallbacks myLoaderCallbacks;

    private List<MyListOfVerbs> listOfMyList = new ArrayList<>();
    private List<MyListAdjective> listOfMyAdjectiveList = new ArrayList<>();
    private List<String> listOfLessonVerbsSimple;
    private List<String> listOfLessonVerbsIrregularV1;
    private List<String> listOfLessonVerbsIrregularV2;
    private List<String> listOfLessonVerbsIrregularV3;

    private List<Integer> listIdTenseObject, listIdBackgroundImages, listIdImageTypeOfSentence, listIdBackgroundImageNegativeSentence
            , listIdBackgroundImageQuestionSentence, listIdBackgroundImageNegativeQuestionSentence;

    private List<String> listOfNames;
    private List<String> listOfVerbsStrong;
    private List<String> listOfLessonAdjective = new ArrayList<>();

    private String wrongSentence = "", wrongV3PassiveVerb = "";
    private int randomNumberOfTense = -1, randomNumberTypeOfSentence = -1;
    private boolean isSwitchShowPromptOn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_algorithm);
        loaderManager = LoaderManager.getInstance(this);
        myLoaderCallbacks = new MyLoaderCallbacks(getApplicationContext(), 1);

        imageViewTenseObject = findViewById(R.id.imageTenseObject);
        imageViewTenseObject.setImageResource(R.drawable.coach);
        imageViewTypeOfSentence = findViewById(R.id.imageViewTypeOfSentence);

        textViewSentence = findViewById(R.id.textViewSentence);
        textViewSentence = findViewById(R.id.textViewSentence);
        textViewV1 = findViewById(R.id.textViewV1);
        textViewV2 = findViewById(R.id.textViewV2);
        textViewV3 = findViewById(R.id.textViewV3);


        textViewTranslation = findViewById(R.id.textViewTranslation);

        buttonPutIntoDBWrongSentence = findViewById(R.id.buttonToTableActivity);

        imageButtonShowDialogVerbs = findViewById(R.id.imageButtonShowDialogVerbs);
        imageButtonShowDialogAdjective = findViewById(R.id.imageButtonShowDialogAdjectives);

        linearLayoutVerbsForm = findViewById(R.id.linearLayoutVerbForms);
        constraintLayout = findViewById(R.id.windowAlgorithmActivity);

        switchShowHelpContent = findViewById(R.id.switchIrregularVerb);
        switchShowPrompt = findViewById(R.id.switchShowPrompt);

        checkBoxTenseFuture = findViewById(R.id.checkBoxTenseFuture);
        checkBoxTensePresent = findViewById(R.id.checkBoxTensePresent);
        checkBoxTensePast = findViewById(R.id.checkBoxTensePast);
        checkBoxTypeOfVerbSimple = findViewById(R.id.checkBoxSimple);
        checkBoxTypeOfVerbStrong = findViewById(R.id.checkBoxStrong);
        checkBoxTypeOfVerbToBe = findViewById(R.id.checkBoxToBe);
        checkBoxTypeOfSentenceMinus = findViewById(R.id.checkBoxMinus);
        checkBoxTypeOfSentencePlus = findViewById(R.id.checkBoxPlus);
        checkBoxTypeOfSentenceQu = findViewById(R.id.checkBoxQuestion);
        checkBoxTypeOfSentenceMinusQu = findViewById(R.id.checkBoxMinusQuestion);


        checkBoxTenseFuture.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTenseFuture, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));
        checkBoxTensePresent.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTensePresent, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));
        checkBoxTensePast.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTensePast, R.color.check_box_tense_checked_color, R.color.check_box_tense_unchecked_color));

        checkBoxTypeOfVerbSimple.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbSimple, R.color.check_box_checked_color, R.color.check_box_unchecked_color));
        checkBoxTypeOfVerbStrong.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbStrong, R.color.check_box_checked_color, R.color.check_box_unchecked_color));
        checkBoxTypeOfVerbToBe.setOnClickListener(new TextColorOnCheckedSetter(checkBoxTypeOfVerbToBe, R.color.check_box_checked_color, R.color.check_box_unchecked_color));

        viewModel = ViewModelProviders.of(this).get(MainViewModel.class);

        listIdTenseObject = getListIdDrawableRes(R.drawable.spaceship, R.drawable.car, R.drawable.coach);
        listIdImageTypeOfSentence = getListIdDrawableRes(R.drawable.minus, R.drawable.plus, R.drawable.qu_mark, R.drawable.qu_mark_minus);

        listIdBackgroundImages = getListIdDrawableRes(R.drawable.tense_way_future, R.drawable.tense_way_present, R.drawable.tense_way_past);
        listIdBackgroundImageNegativeSentence = getListIdDrawableRes(R.drawable.tense_way_future_negative, R.drawable.tense_way_present_negative, R.drawable.tense_way_past_negative);
        listIdBackgroundImageQuestionSentence = getListIdDrawableRes(R.drawable.tense_way_future_qu, R.drawable.tense_way_present_qu, R.drawable.tense_way_past_qu);
        listIdBackgroundImageNegativeQuestionSentence = getListIdDrawableRes(R.drawable.tense_way_future_negative_qu, R.drawable.tense_way_present_negative_qu, R.drawable.tense_way_past_negative_qu);

        listOfNames = getArrayListFromStringRes(this, R.array.personal_pronouns, R.string.names);
        listOfVerbsStrong = getArrayListFromStringRes(this, R.array.strong_verbs);
//        listOfAdjective = getArrayListFromStringResources(this, null, R.string.adjective);

        listOfLessonVerbsSimple = new ArrayList<>();
        listOfLessonVerbsIrregularV1 = new ArrayList<>();
        listOfLessonVerbsIrregularV2 = new ArrayList<>();
        listOfLessonVerbsIrregularV3 = new ArrayList<>();

        listOfMyList.add(getMyListOfVerbs("Verbs # 1 (1 - 50)", true, R.string.simple_verbs_1, R.string.irregular_verbs_v1_1, R.string.irregular_verbs_v2_1, R.string.irregular_verbs_v3_1));
        listOfMyList.add(getMyListOfVerbs("Verbs # 2 (51 - 100)", false, R.string.simple_verbs_2, R.string.irregular_verbs_v1_2, R.string.irregular_verbs_v2_2, R.string.irregular_verbs_v3_2));
        listOfMyList.add(getMyListOfVerbs("Verbs # 3 (101 - 150)", false, R.string.simple_verbs_3, R.string.irregular_verbs_v1_3, R.string.irregular_verbs_v2_3, R.string.irregular_verbs_v3_3));
        listOfMyList.add(getMyListOfVerbs("Verbs # 4 (151 - 200)", false, R.string.simple_verbs_4, R.string.irregular_verbs_v1_4, R.string.irregular_verbs_v2_4, R.string.irregular_verbs_v3_4));
        listOfMyList.add(getMyListOfVerbs("Verbs # 5 (201 - 250)", false, R.string.simple_verbs_5, R.string.irregular_verbs_v1_5, R.string.irregular_verbs_v2_5, R.string.irregular_verbs_v3_5));
        listOfMyList.add(getMyListOfVerbs("Verbs # 6 (251 - 300", false, R.string.simple_verbs_6, R.string.irregular_verbs_v1_5, R.string.irregular_verbs_v2_6, R.string.irregular_verbs_v2_6));

        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #1", true, R.string.adjective_1_1));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #2", false, R.string.adjective_1_2));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #3", false, R.string.adjective_1_3));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #4", false, R.string.adjective_1_4));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #5", true, R.string.adjective_2_1));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #6", false, R.string.adjective_2_2));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #7", false, R.string.adjective_2_3));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #8", false, R.string.adjective_2_4));
        listOfMyAdjectiveList.add(getMyAdjectiveList("Adjectives #9", false, R.string.adjective_2_5));

        setCheckedMyListOfVerbs();
        setCheckedMyListAdjective();

        myLoaderCallbacks.setHelper(new IOnCallbackHelper() {
            @Override
            public void onLoadFinished(String data) {
                textViewTranslation.setText(data);
                loaderManager.destroyLoader(myLoaderCallbacks.getId());
            }
        });

//        loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);


        imageButtonShowDialogVerbs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickShowDialogVerbs();
//                Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.right_to_left);
//                imageButtonShowDialogVerbs.startAnimation(animation);
            }
        });

        imageButtonShowDialogAdjective.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.setContentView(R.layout.dialog_adjective);

                Button button = dialog.findViewById(R.id.buttonApplyDialogAdjective);

                RecyclerView recyclerView = dialog.findViewById(R.id.recyclerViewDialogAdjective);
                recyclerView.setLayoutManager(new LinearLayoutManager(v.getContext()));
                recyclerView.setAdapter(new MyAdapterAdjectiveList(listOfMyAdjectiveList));

                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
                dialog.show();

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        setCheckedMyListAdjective();
                        dialog.dismiss();
                    }
                });
            }
        });

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

        switchShowHelpContent.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
                if (b) {
                    linearLayoutVerbsForm.setVisibility(View.VISIBLE);
                    textViewTranslation.setVisibility(View.VISIBLE);
                } else {
                    linearLayoutVerbsForm.setVisibility(View.INVISIBLE);
                    textViewTranslation.setVisibility(View.INVISIBLE);
                }
            }
        });

        textViewV1.setOnClickListener(new OnClickAudioContentPlayer());
        textViewV2.setOnClickListener(new OnClickAudioContentPlayer());
        textViewV3.setOnClickListener(new OnClickAudioContentPlayer());




    }

    public void onClickNext(View view) {
        Random random = new Random();
        randomNumberTypeOfSentence = getRandomNumberOnChecked(checkBoxTypeOfSentenceMinus, checkBoxTypeOfSentencePlus, checkBoxTypeOfSentenceQu, checkBoxTypeOfSentenceMinusQu);
        randomNumberOfTense = getRandomNumberOnChecked(checkBoxTenseFuture, checkBoxTensePresent, checkBoxTensePast);   // 0 - future, 1 - present, 2 - past
        setConstraintLayoutBackgroundImage(isSwitchShowPromptOn);
        int randomNumberOfToBe = random.nextInt(3);

        setImage(imageViewTenseObject, listIdTenseObject, randomNumberOfTense);
        setImage(imageViewTypeOfSentence, listIdImageTypeOfSentence, randomNumberTypeOfSentence);

        wrongSentence = "";
        wrongV3PassiveVerb = "";
        String sentence = "";
        String name = getWordFromList(listOfNames);
        ArrayList<String> listOfLessonVerbsSimpleIrregular = new ArrayList<>(listOfLessonVerbsSimple);
        listOfLessonVerbsSimpleIrregular.addAll(listOfLessonVerbsIrregularV1);
        String simpleIrregularVerb = getWordFromList(listOfLessonVerbsSimpleIrregular);

        int randomNumberOfWay = getRandomNumberOnChecked(checkBoxTypeOfVerbSimple, checkBoxTypeOfVerbStrong, checkBoxTypeOfVerbToBe);    // 0 - simple verbs, 1 - strong verbs, 2 (else) - ing & adjective
        if (randomNumberOfWay == -1) {
            showToast();
        } else {
            if (randomNumberOfWay == 0) {   // simple
                sentence = getSentenceWithSimpleVerb(name, simpleIrregularVerb, listOfLessonVerbsSimpleIrregular);
            } else if (randomNumberOfWay == 1) {    // strong
                sentence = getSentenceWithStrongVerb(name, simpleIrregularVerb);
            } else {    // ToBe

                if (randomNumberOfToBe == 0) {   // - ing
                    sentence = getSentenceWithToBeIngForm(name, simpleIrregularVerb);
                } else if (randomNumberOfToBe == 1) {  // adjective
                    String adjective = getWordFromList(listOfLessonAdjective);
                    sentence = String.format("%s %s", name, adjective);
                    loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(adjective), myLoaderCallbacks);
                    setWordOfToBeAdjective(adjective);
//                    textViewTranslation.setText(getTranslateContent(adjective));
                } else {  // passive verb
                    if (listOfLessonVerbsIrregularV1.contains(simpleIrregularVerb)) {
                        sentence = getSentenceWithToBeV3Verb(name, simpleIrregularVerb);
                    } else {  // simple -ed
                        sentence = getSentenceWithToBeSimplePassiveVerb(name, simpleIrregularVerb);
                    }
                }
            }
        }

        if (randomNumberOfWay != 2 || randomNumberOfToBe != 1) {
            loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(simpleIrregularVerb), myLoaderCallbacks);
            setFormsOfIrregularVerb(simpleIrregularVerb);
//            textViewTranslation.setText(getTranslateContent(simpleIrregularVerb));
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

//    private void setEmptyOnTextViewOfFormsOfIrregularVerb() {
//        textViewV1.setText("-//-");
//        textViewV2.setText("-//-");
//        textViewV3.setText("-//-");
//        setTextViewVColor(R.color.textView_color_v1, R.color.textView_color_v1);
//    }

    private void setFormsOfIrregularVerb(String verb) {
        if (listOfLessonVerbsIrregularV1.contains(verb)) {
            int index = listOfLessonVerbsIrregularV1.indexOf(verb);
            textViewV1.setText(listOfLessonVerbsIrregularV1.get(index));
            textViewV2.setText(listOfLessonVerbsIrregularV2.get(index));
            textViewV3.setText(listOfLessonVerbsIrregularV3.get(index));
            setTextViewVColor(R.color.textView_color_v2, R.color.textView_color_v3);
        } else {
            textViewV1.setText(verb);

            if (verb.endsWith("e")) {
                verb = verb + "d";
            } else if (verb.matches("\\w+[^aeiouy]y")) {
                verb = verb.substring(0, verb.length() - 1) + "ied";
            } else {          //else if (simpleIrregularVerb.matches("\\w+[aeiouy]y"))
                verb = verb + "ed";
            }
            textViewV2.setText(verb);
            textViewV3.setText("[reg]");
            setTextViewVColor(R.color.textView_color_v2, R.color.textView_color_v3_note);
        }
    }

    private void setWordOfToBeAdjective(String word) {
        textViewV1.setText("");
        textViewV2.setText(word);
        textViewV3.setText("[adj]");
        setTextViewVColor(R.color.textView_color_toBe_word, R.color.textView_color_v3_note);
    }

    private void setTextViewVColor(int idResColorV2, int idResColorV3) {
        textViewV2.setTextColor(getResources().getColor(idResColorV2));
        textViewV3.setTextColor(getResources().getColor(idResColorV3));
    }

    @SuppressLint("UseCompatLoadingForDrawables")
    private void setConstraintLayoutBackgroundImage(boolean b) {
        if (b && randomNumberOfTense != -1) {
            if (randomNumberTypeOfSentence == 0) {  // [-]
                constraintLayout.setBackground(getResources().getDrawable(listIdBackgroundImageNegativeSentence.get(randomNumberOfTense)));
            } else if (randomNumberTypeOfSentence == 1) {   // [+]
                constraintLayout.setBackground(getResources().getDrawable(listIdBackgroundImages.get(randomNumberOfTense)));
            } else if (randomNumberTypeOfSentence == 2) {   // [?]
                constraintLayout.setBackground(getResources().getDrawable(listIdBackgroundImageQuestionSentence.get(randomNumberOfTense)));
            } else if (randomNumberTypeOfSentence == 3) {    // [-?]
                constraintLayout.setBackground(getResources().getDrawable(listIdBackgroundImageNegativeQuestionSentence.get(randomNumberOfTense)));
            } else {
                constraintLayout.setBackground(getResources().getDrawable(R.drawable.tense_way_base));
            }
//                    getWindow().setBackgroundDrawableResource(R.drawable.table_filled);
        } else {
            constraintLayout.setBackground(getResources().getDrawable(R.drawable.tense_way_base));
        }
    }

    public String[] getArrayFromArrayResources(Context context, int id) {
        return context.getResources().getStringArray(id);
    }

    public String[] getArrayFromResources(int id) {
        return getString(id).split(",");
    }

    public ArrayList<String> getArrayListFromStringRes(Context context, Integer idArrayResource, int... idResource) {
        List<String[]> listOfArgsId = new ArrayList<>();
        ArrayList<String> stringList = new ArrayList<>();

        if (idArrayResource != null) {
            listOfArgsId.add(getArrayFromArrayResources(context, idArrayResource));
        }
        for (int id : idResource) {
            listOfArgsId.add(getArrayFromResources(id));
        }
        for (String[] s : listOfArgsId) {
            stringList.addAll(Arrays.asList(s));
        }
        return stringList;
    }

    private ArrayList<Integer> getListIdDrawableRes(int... id) {
        ArrayList<Integer> arrayList = new ArrayList<>();
        for (int i : id) {
            arrayList.add(i);
        }
        return arrayList;
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

    private int getRandomNumberOnChecked(CheckBox... checkBoxes) {
        Random random = new Random();
        ArrayList<Integer> arrayList = new ArrayList<>();

        for (int i = 0; i < checkBoxes.length; i++) {
            if (checkBoxes[i].isChecked()) {
                arrayList.add(i);
            }
        }
        if (!arrayList.isEmpty()) {
            return arrayList.get(random.nextInt(arrayList.size()));
        }
        return -1;
    }

    private void onClickShowDialogVerbs() {
        Dialog dialog = new Dialog(AlgorithmActivity.this);
        dialog.setContentView(R.layout.dialog_verbs);
        ListView listView = dialog.findViewById(R.id.listView);
        MyAdapter adapter = new MyAdapter(this, listOfMyList);
        listView.setAdapter(adapter);

        Button button = dialog.findViewById(R.id.buttonApplyDialogVerbs);
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
            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.bounce);
            checkBox.startAnimation(animation);
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

    private MyListOfVerbs getMyListOfVerbs(String name, boolean b, int idResSimpleVerb, int idResIrregularV1, int idResIrregularV2, int idResIrregularV3) {
        List<String> listOfVerbsSimple = getArrayListFromStringRes(this, null, idResSimpleVerb);
        List<String> listOfVerbsIrregularV1 = getArrayListFromStringRes(this, null, idResIrregularV1);
        List<String> listOfVerbsIrregularV2 = getArrayListFromStringRes(this, null, idResIrregularV2);
        List<String> listOfVerbsIrregularV3 = getArrayListFromStringRes(this, null, idResIrregularV3);

        return new MyListOfVerbs(name, b, listOfVerbsSimple, listOfVerbsIrregularV1, listOfVerbsIrregularV2, listOfVerbsIrregularV3);
    }

    private MyListAdjective getMyAdjectiveList(String nameList, boolean isChecked, int idResAdjective) {
        List<String> listAdjective = getArrayListFromStringRes(this, null, idResAdjective);

        return new MyListAdjective(nameList, isChecked, listAdjective);
    }

    private void setImage(ImageView imageView, List<Integer> list, int randomNumber) {
        if (randomNumber != -1) {
            imageView.setImageResource(list.get(randomNumber));
            imageView.setVisibility(View.VISIBLE);
        } else {
            imageView.setVisibility(View.INVISIBLE);
        }

    }

//    private class OnClickAudioContentPlayer implements View.OnClickListener {
//        @Override
//        public void onClick(View v) {
//
//            Animation animation = AnimationUtils.loadAnimation(getApplicationContext(), R.anim.fadein);
//            v.startAnimation(animation);
//
//            String word = ((TextView) v).getText().toString();
//            Log.i("log_word", word);
//            MediaPlayer player = new MediaPlayer();
//            try {
//                player.setDataSource(String.format("https://wooordhunt.ru/data/sound/sow/us/%s.mp3", word));
//
//                player.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
//                    @Override
//                    public void onPrepared(MediaPlayer mp) {
//                        mp.start();
//                    }
//                });
//
//                player.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
//                    @Override
//                    public void onCompletion(MediaPlayer mp) {
//                        mp.release();
//                        v.clearAnimation();
//                    }
////            v.clearAnimation();
//                });
//                player.prepareAsync();
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//    }

    private void setCheckedMyListAdjective() {
        listOfLessonAdjective.clear();

        for (MyListAdjective my : listOfMyAdjectiveList) {
            if (my.isChecked()) {
                listOfLessonAdjective.addAll(my.getListAdjective());
            }
        }
    }

    private Bundle getBundleWord (String word) {
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        return bundle;
    }


//    private static class DownLoadTask extends AsyncTask<String, Void, String> {
//        URL url;
//        StringBuilder sb = new StringBuilder();
//        HttpURLConnection urlConnection;
//
//        @Override
//        protected String doInBackground(String... strings) {
//            try {
//                url = new URL(strings[0]);
//                urlConnection = (HttpURLConnection) url.openConnection();
//                InputStream inputStream = urlConnection.getInputStream();
//                InputStreamReader reader = new InputStreamReader(inputStream);
//                BufferedReader bufferedReader = new BufferedReader(reader);
//                String line = bufferedReader.readLine();
//
//                while (line != null) {
//                    sb.append(line);
//                    line = bufferedReader.readLine();
//                }
//
//            } catch (MalformedURLException e) {
//                e.printStackTrace();
//            } catch (IOException e) {
//                e.printStackTrace();
//            } finally {
//                if (urlConnection != null) {
//                    urlConnection.disconnect();
//                }
//            }
//            return sb.toString();
//        }
//    }
//    private String getTranslateContent(String word) {
//        DownLoadTask loadTask = new DownLoadTask();
//        String url = String.format("https://wooordhunt.ru/word/%s", word);
//        String content = "";
//        StringBuilder result = new StringBuilder();
//        try {
//            content = loadTask.execute(url).get();
//
//        } catch (ExecutionException e) {
//            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//
//        Pattern pattern = Pattern.compile("<span class=\"t_inline_en\">([а-я,\\s]*)</span>");
//        Matcher matcher = pattern.matcher(content);
//
//        while (matcher.find()) {
//            result.append(matcher.group(1));
//        }
//        return result.toString();
//    }
}