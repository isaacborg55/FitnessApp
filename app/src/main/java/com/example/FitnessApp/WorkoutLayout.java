package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.appcompat.app.AppCompatActivity;

public class WorkoutLayout extends AppCompatActivity {

  //method to select which workout you would like to see
    ImageButton classic0,full0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_workout_layout);
        classic0=(ImageButton)findViewById(R.id.classic);
        full0=(ImageButton)findViewById(R.id.full);


        classic0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent splitw = new Intent(WorkoutLayout.this, Workout.class);
                startActivity(splitw);
            }
        });

        full0.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fullw = new Intent(WorkoutLayout.this,FullWorkout.class);
                startActivity(fullw);
            }
        });
    }
}