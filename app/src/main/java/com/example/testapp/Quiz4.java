package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz4 extends AppCompatActivity {
    Button btnnext;
    RadioGroup btnradio;
    RadioButton btnradoui;
    RadioButton btnradnon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz4);
        btnradio = findViewById(R.id.radiogrp);
        btnradoui = findViewById(R.id.radioButton);
        btnradnon = findViewById(R.id.radioButton2);
        btnnext = findViewById(R.id.button3);
        btnradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int CheckedButtonId) {
                switch (CheckedButtonId) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
                        btnnext.setOnClickListener(view -> {
                            startActivity(new Intent(Quiz4.this, Quiz5.class));
                        });
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                        btnnext.setOnClickListener(view -> {
                            Quiz1.score+=20;
                            Intent intent=new Intent(Quiz4.this, Quiz5.class);
                            startActivity(intent);
                        });
                        break;
                    default:
                        Toast.makeText(getApplicationContext(), "Please select a one", Toast.LENGTH_SHORT).show();
                        break;


                }
            }
        });
    }
}