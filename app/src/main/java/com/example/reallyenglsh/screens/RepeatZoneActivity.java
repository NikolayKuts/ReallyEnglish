package com.example.reallyenglsh.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.loader.app.LoaderManager;
import androidx.loader.content.Loader;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.reallyenglsh.IOnCallbackHelper;
import com.example.reallyenglsh.MyListAllWords;
import com.example.reallyenglsh.MyLoaderCallbacks;
import com.example.reallyenglsh.DownLoader;
import com.example.reallyenglsh.OnClickAudioContentPlayer;
import com.example.reallyenglsh.StringResourcesAssembler;
import com.example.reallyenglsh.adapters.MyAdapter;
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

    private LoaderManager loaderManager;
    private MyLoaderCallbacks myLoaderCallbacks;
    private MyLoaderCallbacks transcriptionLoader;

    private List<MyListAllWords> lists = new ArrayList<>();
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

        textViewWord.setOnClickListener(new OnClickAudioContentPlayer());


        StringResourcesAssembler assembler = new StringResourcesAssembler(this);

//        lists.add(new MyListAllWords("adjective like verb", false,));
        lists.add(new MyListAllWords("adjective #1.1", false, assembler.getListAdjective(R.string.adjective_1_1)));
        lists.add(new MyListAllWords("adjective #1.2", false, assembler.getListAdjective(R.string.adjective_1_2)));
        lists.add(new MyListAllWords("adjective #2.1", false, assembler.getListAdjective(R.string.adjective_1_3)));
        lists.add(new MyListAllWords("adjective #2.2", false, assembler.getListAdjective(R.string.adjective_1_4)));
        lists.add(new MyListAllWords("adjective #1.1", false, assembler.getListAdjective(R.string.adjective_2_1)));
        lists.add(new MyListAllWords("adjective #1.2", false, assembler.getListAdjective(R.string.adjective_2_2)));
        lists.add(new MyListAllWords("adjective #2.1", false, assembler.getListAdjective(R.string.adjective_2_3)));
        lists.add(new MyListAllWords("adjective #2.2", false, assembler.getListAdjective(R.string.adjective_2_4)));
        lists.add(new MyListAllWords("adjective #2.2", false, assembler.getListAdjective(R.string.adjective_2_5)));


        lists.add(new MyListAllWords("verbs # 1", true, assembler.getListVerbs(R.string.simple_verbs_1, R.string.irregular_verbs_v1_1, R.string.irregular_verbs_v2_1, R.string.irregular_verbs_v3_1)));
        lists.add(new MyListAllWords("verbs # 2", false, assembler.getListVerbs(R.string.simple_verbs_2, R.string.irregular_verbs_v1_2, R.string.irregular_verbs_v2_2, R.string.irregular_verbs_v3_2)));
        lists.add(new MyListAllWords("verbs # 3", false, assembler.getListVerbs(R.string.simple_verbs_3, R.string.irregular_verbs_v1_3, R.string.irregular_verbs_v2_3, R.string.irregular_verbs_v3_3)));
        lists.add(new MyListAllWords("verbs # 4", false, assembler.getListVerbs(R.string.simple_verbs_4, R.string.irregular_verbs_v1_4, R.string.irregular_verbs_v2_4, R.string.irregular_verbs_v3_4)));
        lists.add(new MyListAllWords("verbs # 5", false, assembler.getListVerbs(R.string.simple_verbs_5, R.string.irregular_verbs_v1_5, R.string.irregular_verbs_v2_5, R.string.irregular_verbs_v3_5)));
        lists.add(new MyListAllWords("verbs # 6", false, assembler.getListVerbs(R.string.simple_verbs_6, R.string.irregular_verbs_v1_6, R.string.irregular_verbs_v2_6, R.string.irregular_verbs_v3_6)));
//        lists.add(new MyListAllWords("Twins", R.string.twins, false, this));

        setCheckedLists();

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
                setTextContent(downLoader.getTranslation(data), downLoader.getTranscription(data));
                loaderManager.destroyLoader(myLoaderCallbacks.getId());
                progressBar.setVisibility(View.INVISIBLE);
            }

//            @Override
//            public Loader<HashMap<String, String>> onCreateGetLoader(Bundle bundle) {
//                progressBar.setVisibility(View.VISIBLE);
//                return new ContentLoader(getApplicationContext(), bundle);
//            }
//
//            @Override
//            public void onLoadFinished(HashMap<String, String> data) {
//                setTextContent(data);
//                loaderManager.destroyLoader(myLoaderCallbacksTranslation.getId());
//                progressBar.setVisibility(View.INVISIBLE);
//            }
        });


        loaderManager.restartLoader(myLoaderCallbacks.getId(), getBundleWord(), myLoaderCallbacks);
//        loaderManager.restartLoader(transcriptionLoader.getId(),getBundleWord(),transcriptionLoader);

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
                        int numberWord = Integer.parseInt(editText.getText().toString());
                        if (numberWord <= listLesson.size()) {
                            indexWord = numberWord - 1;
                            setWord();
//                            setTextContent();
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
            word = listLesson.get(indexWord);
        } else if (indexWord < 0) {
            indexWord = listLesson.size() - 1;
            word = listLesson.get(indexWord);
        } else {
            word = listLesson.get(indexWord);
        }
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
        dialog.setContentView(R.layout.dialog_lists);

        Button button = dialog.findViewById(R.id.buttonApply);

        RecyclerView recyclerView = dialog.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        recyclerView.setAdapter(new MyAdapter(lists));
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

        for (MyListAllWords my : lists) {
            if (my.isChecked()) {
                listLesson.addAll(my.getList());
            }
        }
    }

    private MyListAllWords getMyListAllWordsAdjectives(String name, boolean isChecked, int...idRes) {
        StringResourcesAssembler assembler = new StringResourcesAssembler(this);
        List<String> list = assembler.getListAdjective(idRes);
        return new MyListAllWords(name, isChecked, list);
    }

    private MyListAllWords getMyListAllWordsVerbs(String name, boolean isChecked, int idSimple, int idIrregularV1, int idIrregularV2, int idIrregularV3) {
        StringResourcesAssembler assembler = new StringResourcesAssembler(this);
        List<String> list = assembler.getListVerbs(idSimple, idIrregularV1, idIrregularV2, idIrregularV3);
        return new MyListAllWords(name, isChecked, list);
    }
}