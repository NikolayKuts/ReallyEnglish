package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {
//    private SentencesDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

//        database = SentencesDatabase.getInstance(this);
//
//        this.deleteDatabase("really_english.db");

        ArrayList<String> arrayListTest = getArrayListFromResources(getResources().getString(R.string.simple_verbs_1));
        Log.i("arrayList", arrayListTest.toString());
    }

    private ArrayList<String> getArrayListFromResources(String stringResources) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArgs = stringResources.split(",");
        Collections.addAll(arrayList, stringArgs);
        return arrayList;
    }

    public void onClickNextActivity(View view) {
        Intent intent = new Intent(this, Table1Activity.class);
        startActivity(intent);
    }

    public void onClickToTable2(View view) {
        Intent intent = new Intent(this, Table2Activity.class);
        startActivity(intent);
    }

    public void onClickToAlgorithmLesson(View view) {
        Intent intent = new Intent(this, AlgorithmActivity.class);
        startActivity(intent);
    }

    public void onClickToCouplesActivityEnToEn(View view) {
        Intent intent = new Intent(this, CouplesActivity.class);
        startActivity(intent);
    }

    public void onClickToDoubleNegativeActivity(View view) {
        Intent intent = new Intent(this, DoubleNegativeActivity.class);
        startActivity(intent);
    }
}