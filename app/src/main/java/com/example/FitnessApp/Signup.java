package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.rengwuxian.materialedittext.MaterialEditText;

import java.util.HashMap;
import java.util.Map;


public class Signup extends AppCompatActivity {
    public static final String TAG = "TAG";
    MaterialEditText mgender,mEmail,mPassword,mage;
    Button mRegisterBtn;
    FirebaseAuth fAuthent;
    FirebaseFirestore fStoring;
    String userID;
//sign up page
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        mgender   = (MaterialEditText) findViewById(R.id.editGender);
        mEmail      = (MaterialEditText) findViewById(R.id.editUsername);
        mPassword   = (MaterialEditText) findViewById(R.id.editPassword);
        mage      = (MaterialEditText) findViewById(R.id.editAge);

        mRegisterBtn= findViewById(R.id.btnSignUp);


        fAuthent = FirebaseAuth.getInstance();
        fStoring = FirebaseFirestore.getInstance();


        mRegisterBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email = mEmail.getText().toString().trim();
                String password = mPassword.getText().toString().trim();
                final String gender = mgender.getText().toString();
                final String age    = mage.getText().toString();

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
                } //validation


                    // User is registered on the firebase realtime database
                fAuthent.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){

                            //A link is sent by email to the user for verification

                            FirebaseUser fuse = fAuthent.getCurrentUser();
                            fuse.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Signup.this, "Verification Email Has been Sent.", Toast.LENGTH_SHORT).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                //https://developer.android.com/reference/com/google/android/play/core/tasks/OnFailureListener
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "onFailure: Email not sent " + e.getMessage());
                                }
                            });

                            Toast.makeText(Signup.this, "User Created.", Toast.LENGTH_SHORT).show();
                            userID = fAuthent.getCurrentUser().getUid();
                            DocumentReference documentReference = fStoring.collection("users").document(userID);
                            //HashMap used to store the account details
                            Map<String,Object> user = new HashMap<>();
                            user.put("gender",gender);
                            user.put("email",email);
                            user.put("age",age);
                            documentReference.set(user).addOnSuccessListener(new OnSuccessListener<Void>() {
                                //https://developer.android.com/reference/java/util/HashMap
                                //https://www.youtube.com/watch?v=3iv8WlVKo0I
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Log.d(TAG, "user Profile is created for "+ userID);
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Log.d(TAG, "fail:" + e.toString());
                                }
                            });
                            startActivity(new Intent(getApplicationContext(),MainActivity.class));

                        }else {
                            Toast.makeText(Signup.this, "Error ! " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                        }
                    }
                });
            }
        });
    }
}