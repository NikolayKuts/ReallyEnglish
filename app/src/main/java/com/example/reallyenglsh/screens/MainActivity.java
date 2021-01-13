package com.example.reallyenglsh.screens;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.realyenglsh.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ArrayList<String> arrayListTest = getArrayListFromResources(getResources().getString(R.string.simple_verbs_1));
        Log.i("arrayList", arrayListTest.toString());
    }

    private ArrayList<String> getArrayListFromResources(String stringResources) {
        ArrayList<String> arrayList = new ArrayList<>();
        String[] stringArgs = stringResources.split(",");
        Collections.addAll(arrayList, stringArgs);
        return arrayList;
    }

    public void onClickToAlgorithmLesson(View view) {
        Intent intent = new Intent(this, AlgorithmActivity.class);
        startActivity(intent);
    }

    public void onClickToCouplesActivityEnToEn(View view) {
        Intent intent = new Intent(this, CouplesActivity.class);
        startActivity(intent);
    }

    public void onClickToRepeatZoneActivity(View view) {
        Intent intent = new Intent(this, RepeatZoneActivity.class);
        startActivity(intent);
    }
}