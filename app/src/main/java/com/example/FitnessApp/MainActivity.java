package com.example.FitnessApp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    //main activity sign up/in page
    //https://developer.android.com/training/basics/firstapp/starting-activity
    Button btnSignUp,btnSignIn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnSignIn = (Button) findViewById(R.id.btnSignIn);
        btnSignUp = (Button) findViewById(R.id.btnSignUp);
        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                Intent signUp = new Intent(MainActivity.this,Signup.class);
                startActivity(signUp); //starts sign up page
            }
        });

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View viewer) {
                Intent signIn = new Intent(MainActivity.this,Signin.class);
                startActivity(signIn); //goes to sign in activity
            }
        });
    }
}
