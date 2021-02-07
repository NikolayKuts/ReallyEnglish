package com.example.reallyenglsh.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reallyenglsh.IOnCallbackHelper;
import com.example.reallyenglsh.MyListWords;
import com.example.reallyenglsh.MyLoaderCallbacks;
import com.example.reallyenglsh.DownLoader;
import com.example.reallyenglsh.OnClickAudioContentPlayer;
import com.example.reallyenglsh.StringResourcesAssembler;
import com.example.reallyenglsh.WordFormsSetter;
import com.example.reallyenglsh.adapters.MyAdapterListWords;
import com.example.realyenglsh.R;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Nullable;

public class RepeatZoneActivity extends AppCompatActivity {

    private TextView textViewWord;
    private TextView textViewTranslation;
    private TextView textViewTranscription;
    private TextView textViewCount;
    private TextView textViewQuantity;
    private LinearLayout linearLayoutCounter;
    private ProgressBar progressBar;

    private TextView textViewV1Form, textViewV2Form, textViewV3Form;
    private List<String> irregularVerbsV1, irregularVerbsV2, irregularVerbsV3;
    private List<String> simpleVerbs;

    private LoaderManager loaderManager;
    private MyLoaderCallbacks myLoaderCallbacks;

    private List<MyListWords> lists = new ArrayList<>();
    private List<String> listLesson = new ArrayList<>();
    private int indexWord = -1;
    private String word;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repeat_zone);

        loaderManager = LoaderManager.getInstance(this);

        textViewWord = findViewById(R.id.textViewWord);
        textViewTranslation = findViewById(R.id.textViewTranslation);
        textViewTranscription = findViewById(R.id.textViewTranscription);
        textViewCount = findViewById(R.id.textViewCount);
        textViewQuantity = findViewById(R.id.textViewQuantity);
        linearLayoutCounter = findViewById(R.id.linearLayoutCounter);
        progressBar = findViewById(R.id.progressBar);

        textViewV1Form = findViewById(R.id.textViewV1Form);
        textViewV2Form = findViewById(R.id.textViewV2Form);
        textViewV3Form = findViewById(R.id.textViewV3Form);

        textViewV1Form.setOnClickListener(new OnClickAudioContentPlayer());
        textViewV2Form.setOnClickListener(new OnClickAudioContentPlayer());
        textViewV3Form.setOnClickListener(new OnClickAudioContentPlayer());


        textViewWord.setOnClickListener(new OnClickAudioContentPlayer());


        StringResourcesAssembler assembler = new StringResourcesAssembler(this);

        lists.add(new MyListWords("verbs # 1", true, assembler.getListV1Simple(R.string.simple_verbs_1, R.string.irregular_verbs_v1_1)));
        lists.add(new MyListWords("verbs # 2", false, assembler.getListV1Simple(R.string.simple_verbs_2, R.string.irregular_verbs_v1_2)));
        lists.add(new MyListWords("verbs # 3", false, assembler.getListV1Simple(R.string.simple_verbs_3, R.string.irregular_verbs_v1_3)));
        lists.add(new MyListWords("verbs # 4", false, assembler.getListV1Simple(R.string.simple_verbs_4, R.string.irregular_verbs_v1_4)));
        lists.add(new MyListWords("verbs # 5", false, assembler.getListV1Simple(R.string.simple_verbs_5, R.string.irregular_verbs_v1_5)));
        lists.add(new MyListWords("verbs # 6", false, assembler.getListV1Simple(R.string.simple_verbs_6, R.string.irregular_verbs_v1_6)));

        lists.add(new MyListWords("adjective # 1", false, assembler.getListFromStringRes(R.string.adjective_1)));
        lists.add(new MyListWords("adjective # 2", false, assembler.getListFromStringRes(R.string.adjective_2)));
        lists.add(new MyListWords("adjective # 3", false, assembler.getListFromStringRes(R.string.adjective_3)));
        lists.add(new MyListWords("adjective # 4", false, assembler.getListFromStringRes(R.string.adjective_4)));

        lists.add(new MyListWords("Adjectives like verbs", false, assembler.getListFromStringRes(R.string.adjective_like_verbs)));
        lists.add(new MyListWords("Twins", false, assembler.getListFromStringRes(R.string.twins)));

        setCheckedLists();
        irregularVerbsV1 = assembler.getListFromStringRes(R.string.irregular_verbs_v1_1, R.string.irregular_verbs_v1_2,
                R.string.irregular_verbs_v1_3,R.string.irregular_verbs_v1_4,
                R.string.irregular_verbs_v1_5, R.string.irregular_verbs_v1_6);

        irregularVerbsV2 = assembler.getListFromStringRes(R.string.irregular_verbs_v2_1, R.string.irregular_verbs_v2_2,
                R.string.irregular_verbs_v2_3, R.string.irregular_verbs_v2_4,
                R.string.irregular_verbs_v2_5, R.string.irregular_verbs_v2_6);

        irregularVerbsV3 = assembler.getListFromStringRes(R.string.irregular_verbs_v3_1, R.string.irregular_verbs_v3_2,
                R.string.irregular_verbs_v3_3, R.string.irregular_verbs_v3_4,
                R.string.irregular_verbs_v3_5, R.string.irregular_verbs_v3_6);

        simpleVerbs = assembler.getListFromStringRes(R.string.simple_verbs_1, R.string.simple_verbs_2,
                R.string.simple_verbs_3, R.string.simple_verbs_4,
                R.string.simple_verbs_5, R.string.simple_verbs_6);

        myLoaderCallbacks = new MyLoaderCallbacks(getApplicationContext(), 1);
        myLoaderCallbacks.setHelper(new IOnCallbackHelper() {
            @Override
            public Loader<String> onCreateLoader(@Nullable Bundle args) {
                progressBar.setVisibility(View.VISIBLE);
                return new DownLoader(getApplicationContext(), args);
            }

            @Override
            public void onLoadFinished(Loader<String> loader, String data) {
                DownLoader downLoader = (DownLoader) loader;
                setWordForms();
                setTextContent(downLoader.getTranslation(data), downLoader.getTranscription(data));
                loaderManager.destroyLoader(myLoaderCallbacks.getId());
                progressBar.setVisibility(View.INVISIBLE);
            }

        });


//        loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);

        onClickNext(textViewWord);

        linearLayoutCounter.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(v.getContext());
                dialog.setContentView(R.layout.dialog_counter);
                EditText editText = dialog.findViewById(R.id.editTextNumberCounter);
                Button button = dialog.findViewById(R.id.buttonCounter);
                dialog.show();
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String stringNumber = editText.getText().toString();
                        if (stringNumber.isEmpty()) {
                            stringNumber = "1";
                        }
                        int number = Integer.parseInt(stringNumber);
                        if (number <= listLesson.size()) {
                            indexWord = number - 1;
                            setWord();
                            loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);
                            setCounter();
                            dialog.dismiss();
                        }
                    }
                });
            }
        });


    }

    public void onClickNext(View view) {
        indexWord++;
        setWord();
        loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);
        setCounter();
    }

    public void onClickBack(View view) {
        indexWord--;
        setWord();
        loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);
        setCounter();
    }

    private void setWord() {
        if (indexWord >= listLesson.size()) {
            indexWord = 0;
        } else if (indexWord < 0) {
            indexWord = listLesson.size() - 1;
        }
            word = listLesson.get(indexWord);
    }

    private void setWordForms() {
        WordFormsSetter formsSetter = new WordFormsSetter(textViewV1Form, textViewV2Form, textViewV3Form, this);
        formsSetter.setWordForms(word, simpleVerbs, irregularVerbsV1, irregularVerbsV2, irregularVerbsV3);
    }

    private void setTextContent(String translation, String transcription) {
        textViewTranslation.setText(translation);
        textViewTranscription.setText(transcription);
        textViewWord.setText(word);
    }

    private void setCounter() {
        textViewCount.setText(String.format("%s", indexWord + 1));
        textViewQuantity.setText(String.format("%s", listLesson.size()));

    }

    public void onClickChooseList(View view) {
        Dialog dialog = new Dialog(view.getContext());
        dialog.setContentView(R.layout.dialog_repeat_zone);
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

        Button button = dialog.findViewById(R.id.buttonApply);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyAdapterListWords(lists));
        dialog.show();

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                setCheckedLists();
                indexWord = -1;
                onClickNext(v);
                setCounter();
                dialog.dismiss();
            }
        });
    }

    private Bundle getBundleWord() {
        Bundle bundle = new Bundle();
        bundle.putString("word", word);
        return bundle;
    }

    private void setCheckedLists() {
        listLesson.clear();
        for (MyListWords my : lists) {
            if (my.isChecked()) {
                listLesson.addAll(my.getListWords());
            }
        }
    }
}