package com.example.reallyenglsh.screens;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.reallyenglsh.data.CoupleList;
import com.example.reallyenglsh.data.MainViewModel;
import com.example.realyenglsh.R;

import java.util.ArrayList;
import java.util.Collections;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        MainViewModel viewModel = ViewModelProviders.of(this).get(MainViewModel.class);
//        viewModel.clearAllSavedCouples();
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