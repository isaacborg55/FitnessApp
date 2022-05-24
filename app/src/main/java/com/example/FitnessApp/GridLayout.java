package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import androidx.appcompat.app.AppCompatActivity;

public class GridLayout extends AppCompatActivity {
    ImageButton image1,image2,image3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grid_layout);

        image1=(ImageButton)findViewById(R.id.image1);
        image2=(ImageButton)findViewById(R.id.image2);
        image3=(ImageButton)findViewById(R.id.image3);

        image1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent food = new Intent(GridLayout.this,FoodLayout.class);
                startActivity(food);
            }
        });

        image2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent work = new Intent(GridLayout.this,WorkoutLayout.class);
                startActivity(work);
            }
        });

        image3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent  bmi = new Intent(GridLayout.this,BMI.class);
                startActivity(bmi);
            }
        });
    }

}
