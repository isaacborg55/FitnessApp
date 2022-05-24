package com.example.FitnessApp;

import static java.lang.Float.parseFloat;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

import javax.annotation.Nullable;

    public class FoodLayout extends AppCompatActivity {

        private EditText height;
        private EditText weight;
        private TextView fin;
        Button calc,next;
        FirebaseAuth forAuthent;
        FirebaseFirestore fstorage;
        String userId;
        String gender;
        String aged;
        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_food_layout);
            height = (EditText) findViewById(R.id.height1);
            weight = (EditText) findViewById(R.id.weight1);
            fin = (TextView) findViewById(R.id.res);
            calc = (Button) findViewById(R.id.calc1);
            next = (Button) findViewById(R.id.next);
            forAuthent = FirebaseAuth.getInstance();
            fstorage = FirebaseFirestore.getInstance();

            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user != null) {
                String name = user.getDisplayName();
                String email = user.getEmail();
            }

                userId = forAuthent.getCurrentUser().getUid();
            DocumentReference documentReference = fstorage.collection("users").document(userId);
            documentReference.addSnapshotListener(this, new EventListener<DocumentSnapshot>() {
                @Override
                public void onEvent(DocumentSnapshot docuSnapshot, @Nullable FirebaseFirestoreException e) {
                        if(docuSnapshot.exists()){ //
                            gender = docuSnapshot.getString("gender");
                            aged = docuSnapshot.getString("age");
                        }else {
                            Log.d("tag", "Documentation doesn't exist");
                        }
                        //checks if document exists in db
                    //https://stackoverflow.com/questions/72272694/java-lang-nullpointerexception-attempt-to-invoke-virtual-method-java-lang-long
                    //
                    //https://firebase.google.com/docs/reference/android/com/google/firebase/firestore/DocumentSnapshot
                }
            });
            calc.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String heightString = height.getText().toString();
                    String weightString = weight.getText().toString();
                    float calorie;
                    if (heightString != null && !"".equals(heightString)
                            && weightString != null  &&  !"".equals(weightString))
                    {
                        float heightval = parseFloat(heightString);
                        float weightval = parseFloat(weightString);
                        float age = parseFloat(aged);
                        if (gender.contains("M"))
                        {
                            calorie = (int) Math.round(1.2 * (66 + (13.7 * weightval) + (5 * heightval) - (6.8 * age)));
                            display(calorie);
                        }
                        if (gender.contains("F")) {
                            calorie = (int) Math.round(1.2 * (655 + (9.6 * weightval) + (1.8 * heightval) - (4.7 * age)));
                            display(calorie); //https://www.verywellfit.com/how-many-calories-do-i-need-each-day-2506873
                        }
                        //calculates number of calories needed based on gender
                    }
                }
            });
            next.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Intent cal = new Intent(FoodLayout.this,CalorieCounter.class);
                    startActivity(cal);
                }
            });
        }
        private void display(float cal) {
            String callabel = "";
            callabel = "Recommended Calorie intake "+cal;
            fin.setText(callabel);
            //the number of calories you need to take are shown on screen
        }
    }



