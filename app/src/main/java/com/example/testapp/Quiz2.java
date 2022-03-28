package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz2 extends AppCompatActivity {
    Button btnnext;
    RadioGroup btnradio;
    RadioButton btnradoui;
    RadioButton btnradnon;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz2);
        btnradio = findViewById(R.id.radiogrp);
        btnradoui = findViewById(R.id.radioButton);
        btnradnon = findViewById(R.id.radioButton2);
        btnnext = findViewById(R.id.button3);
        btnradio.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, int CheckedButtonId) {
                switch (CheckedButtonId) {
                    case R.id.radioButton:
                        Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                        btnnext.setOnClickListener(view -> {
                            Quiz1.score+=20;
                            Intent intent=new Intent(Quiz2.this, Quiz3.class);
                            startActivity(intent);
                        });
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(), "Wrong answer", Toast.LENGTH_SHORT).show();
                        btnnext.setOnClickListener(view -> {
                            startActivity(new Intent(Quiz2.this, Quiz3.class));
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