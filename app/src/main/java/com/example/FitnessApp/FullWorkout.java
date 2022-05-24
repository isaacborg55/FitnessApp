package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FullWorkout extends AppCompatActivity {

    Button a1, a2, a3, a4, a5, a6, a7, a8, a9;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full_workout);
        //https://www.youtube.com/watch?v=VwhJaGVmjwg
        a1 = (Button) findViewById(R.id.a1);
        a2 = (Button) findViewById(R.id.a2);
        a3 = (Button) findViewById(R.id.a3);
        a4 = (Button) findViewById(R.id.a4);
        a5 = (Button) findViewById(R.id.a5);
        a6 = (Button) findViewById(R.id.a6);
        a7 = (Button) findViewById(R.id.a7);
        a8 = (Button) findViewById(R.id.a8);
        a9 = (Button) findViewById(R.id.a9);

        //method used to select each one of the  workouts
        a1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent a = new Intent(FullWorkout.this, workout1.class);
                startActivity(a);

            } //each methods will lead to the respective workout/class
        });
        a2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent b = new Intent(FullWorkout.this, workout2.class);
                startActivity(b);

            }
        });
        a3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent c = new Intent(FullWorkout.this, workout3.class);
                startActivity(c);

            }
        });
        a4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent d = new Intent(FullWorkout.this, workout4.class);
                startActivity(d);

            }
        });
        a5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent e = new Intent(FullWorkout.this, workout5.class);
                startActivity(e);

            }
        });
        a6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent f = new Intent(FullWorkout.this, workout6.class);
                startActivity(f);

            }
        });
        a7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent g = new Intent(FullWorkout.this, workout7.class);
                startActivity(g);

            }
        });
        a8.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent h = new Intent(FullWorkout.this, workout8.class);
                startActivity(h);

            }
        });
        a9.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent i = new Intent(FullWorkout.this, workout9.class);
                startActivity(i);

            }
        });
    }
}

