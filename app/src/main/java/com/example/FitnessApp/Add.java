package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.FitnessApp.Model.Food;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Add extends AppCompatActivity {

    EditText input1,input2,input3;
    Button insert,goback;
    long count=0;
    Integer counter1;
    String counter2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);


        input1 = (EditText) findViewById(R.id.input1);
        input2 = (EditText) findViewById(R.id.input2);
        input3 = (EditText) findViewById(R.id.input3);
        insert =(Button)findViewById(R.id.insert);
        goback = (Button)findViewById(R.id.goback);

        //Starting Firebase DB
        final FirebaseDatabase db = FirebaseDatabase.getInstance();
        final DatabaseReference category = db.getReference("Food");

            insert.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    category.addListenerForSingleValueEvent(new ValueEventListener() {

                        @Override

                        public void onDataChange(DataSnapshot dataSnapshot) {

                                count = dataSnapshot.getChildrenCount();
                                counter1 = (int) count + 1;
                                String counter = counter1.toString();
                                Food foods = new Food(input1.getText().toString(), input2.getText().toString(), input3.getText().toString());
                                category.child(counter).setValue(foods);
                                Toast.makeText(Add.this, "Input Added Succesfully", Toast.LENGTH_SHORT).show();
                                counter2 = counter; //pulling data from the db
                            //https://firebase.google.com/docs/reference/android/com/google/firebase/database/DataSnapshot
                            }
                        @Override
                        public void onCancelled(DatabaseError databaseError)
                        { }

                    });
                }
            });

        goback.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choose = new Intent(Add.this,CalorieCounter.class);
                startActivity(choose);
                finish(); //https://www.vogella.com/tutorials/AndroidIntent/article.html
            }
        });


    }
}
