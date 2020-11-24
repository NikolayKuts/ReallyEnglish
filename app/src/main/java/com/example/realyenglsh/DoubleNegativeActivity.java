package com.example.realyenglsh;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

import java.util.Random;

public class DoubleNegativeActivity extends AppCompatActivity {
    private TextView textViewNegativeSentenceRu;
    private TextView textViewNegativeSentenceEn;
    private Switch switchShowNegativeEn;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_double_negative2);
        textViewNegativeSentenceRu = findViewById(R.id.textViewNegativeSentenceRu);
        textViewNegativeSentenceEn = findViewById(R.id.textViewNegativeSentenceEn);
        switchShowNegativeEn = findViewById(R.id.switchShowNegativeEn);
        switchShowNegativeEn.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    textViewNegativeSentenceEn.setVisibility(View.VISIBLE);
                } else {
                    textViewNegativeSentenceEn.setVisibility(View.INVISIBLE);
                }
            }
        });


    }

    public void onClickNextNegativeSentence(View view) {
        Random random = new Random();
        String[] arrayNegativeSentenceRu = getResources().getStringArray(R.array.negative_sentence_ru_q);
        String[] arrayNegativeSentenceEn = getResources().getStringArray(R.array.negative_sentence_en_a);
        int randomNumber = random.nextInt(arrayNegativeSentenceRu.length);
        textViewNegativeSentenceRu.setText(arrayNegativeSentenceRu[randomNumber]);
        textViewNegativeSentenceEn.setText(arrayNegativeSentenceEn[randomNumber]);

    }
}