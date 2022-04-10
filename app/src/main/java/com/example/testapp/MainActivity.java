package com.example.testapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class MainActivity extends AppCompatActivity {
    EditText email;
    EditText password;
    Button btnlogin;
    TextView registerlink;
    FirebaseAuth mAuth;
    ImageView googleimg;
    GoogleSignInOptions gso;
    GoogleSignInClient gsc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        email =findViewById(R.id.Email1);
        password=findViewById(R.id.Password1);
        btnlogin=findViewById(R.id.button1);
        registerlink=findViewById(R.id.textView1);
        googleimg=findViewById(R.id.imageView2);
        //Sign in using google
        gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN).requestEmail().build();
        gsc = GoogleSignIn.getClient(this,gso);
        GoogleSignInAccount account = GoogleSignIn.getLastSignedInAccount(this);
        if(account!=null){
            sendUserQuiz();
        }

        // Initialize Firebase Auth
        mAuth = FirebaseAuth.getInstance();

        btnlogin.setOnClickListener(view -> {loginUser();
        });
        googleimg.setOnClickListener(view -> {
            signIngoogle();
        });

        registerlink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this,RegisterActivity.class));
            }
        });

    }
    public void signIngoogle(){
        Intent signInIntent = gsc.getSignInIntent();
        startActivityForResult(signInIntent,1000);
    }
    protected void onActivityResult(int requestCode, int resultCode,  Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 1000) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                sendUserQuiz();
            }catch (ApiException e) {
                Toast.makeText(getApplicationContext(), "Something went wrong", Toast.LENGTH_SHORT).show();
            }
        }
    }
    public void loginUser(){
        String email1=email.getText().toString();
        String password1=password.getText().toString();
        if(TextUtils.isEmpty(email1)){
            email.setError("Email field is empty");
            email.requestFocus();
        }
        else if(TextUtils.isEmpty(password1)){
            password.setError("Password field is empty");
            password.requestFocus();
        }
        else{
            mAuth.signInWithEmailAndPassword(email1, password1)
                    .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                        @Override
                        public void onComplete(@NonNull Task<AuthResult> task) {
                            if (task.isSuccessful()) {
                                // Sign in success
                                Toast.makeText(MainActivity.this,"Log in successful",Toast.LENGTH_SHORT).show();
                                FirebaseUser user = mAuth.getCurrentUser();
                                sendUserQuiz();
                            } else {
                                // If sign in fails, display a message to the user
                                Toast.makeText(MainActivity.this, "Authentication failed."+task.getException().getMessage(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
        }


    }
    public void sendUserQuiz() {
        Intent intent = new Intent(MainActivity.this, Quiz1.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
        finish();
    }

    @Override
    protected void onStart() {
        super.onStart();
        FirebaseUser currentuser=mAuth.getCurrentUser();
        if(currentuser!=null){
            sendUserQuiz();
        }
    }
}