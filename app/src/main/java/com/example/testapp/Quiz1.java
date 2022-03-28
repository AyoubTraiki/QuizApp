package com.example.testapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

public class Quiz1 extends AppCompatActivity {
    Button btnnext;
    RadioGroup btnradio;
    RadioButton btnradoui;
    RadioButton btnradnon;
    static int score = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz1);
        score=0;
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
                            startActivity(new Intent(Quiz1.this, Quiz2.class));
                        });
                        break;
                    case R.id.radioButton2:
                        Toast.makeText(getApplicationContext(), "Correct answer", Toast.LENGTH_SHORT).show();
                        btnnext.setOnClickListener(view -> {
                            score+=20;
                            Intent intent=new Intent(Quiz1.this, Quiz2.class);
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
  /* public void btnchecked(){
       int radioId=btnradio.getCheckedRadioButtonId();
       btnrad=findViewById(radioId);
        // no radio buttons are
        if (radioId == -1)
        {


        }
        // one of the radio buttons is checked
        else if(btnrad==btnradoui)
        {

        }
        else if(btnrad==btnradnon){

        }



        }

   } */

