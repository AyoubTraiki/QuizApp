package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.Toast;

import com.github.lzyzsd.circleprogress.DonutProgress;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ScoreQuiz extends AppCompatActivity {
    Button btnlogout;
    Button btntryagain;
    DonutProgress donutProgress;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_quiz);
        btnlogout=findViewById(R.id.button2);
        btntryagain=findViewById(R.id.button);
        donutProgress=findViewById(R.id.donut_progress);
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        btntryagain.setOnClickListener(view -> {
            startActivity(new Intent(ScoreQuiz.this,Quiz1.class));
        });
        btnlogout.setOnClickListener(view -> {loginout();signoutgoogle();});
        Toast.makeText(this, "you got " + Quiz1.score, Toast.LENGTH_SHORT).show();
        donutProgress.setDonut_progress(String.valueOf(Quiz1.score));

    }
    public void loginout(){
        FirebaseAuth.getInstance().signOut();
        startActivity(new Intent(ScoreQuiz.this,MainActivity.class));
    }
    public void signoutgoogle(){
        gsc.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                finish();
                startActivity(new Intent(ScoreQuiz.this,MainActivity.class));
            }
        });
    }
}