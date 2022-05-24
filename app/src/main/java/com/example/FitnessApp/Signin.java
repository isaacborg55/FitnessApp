package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.rengwuxian.materialedittext.MaterialEditText;

public class Signin extends AppCompatActivity {

    MaterialEditText mEmail,mPassword;
    Button mLoginBtn;
    ProgressBar progressionBar;
    FirebaseAuth fAuthent;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);

        mEmail = (MaterialEditText) findViewById(R.id.editUsername);
        mPassword = (MaterialEditText) findViewById(R.id.editPassword);
        progressionBar = findViewById(R.id.progressBar);
        fAuthent = FirebaseAuth.getInstance();
        mLoginBtn = findViewById(R.id.btnSignIn);

        mLoginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();

                if(TextUtils.isEmpty(email)){
                    mEmail.setError("Email is Required.");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mPassword.setError("Password is Required.");
                    return;
                }

                if(password.length() < 6){
                    mPassword.setError("Password Must be >= 6 Characters");
                    return;
                } //validation for password

                progressionBar.setVisibility(View.VISIBLE);
                // User is authenticatied by checking DB
                fAuthent.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //https://developer.android.com/reference/com/google/android/play/core/tasks/OnCompleteListener
                        if(task.isSuccessful()){
                            Toast.makeText(Signin.this, "Logged in", Toast.LENGTH_SHORT).show();
                            startActivity(new Intent(getApplicationContext(),GridLayout.class));
                        }else {
                            Toast.makeText(Signin.this, "Invalid ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                            progressionBar.setVisibility(View.GONE);
                        }

                    }
                });

            }
        });
    }
}
