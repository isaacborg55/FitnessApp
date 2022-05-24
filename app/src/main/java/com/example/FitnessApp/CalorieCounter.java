package com.example.FitnessApp;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.FitnessApp.Model.Food;
import com.example.FitnessApp.ViewHolder.CategoryView; //check categoryview
import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class CalorieCounter extends AppCompatActivity {

    FirebaseDatabase db;
    DatabaseReference category;
    RecyclerView recyclermenu;
    RecyclerView.LayoutManager layoutManager;
    Double tally=0.0;
    Button reset;
    Button addelement;
    TextView caltext;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calorie_counter);
        reset=(Button)findViewById(R.id.reset);
        addelement=(Button)findViewById(R.id.addelement);
        caltext = (TextView) findViewById(R.id.caltext);

        //Firebase is initiated again
        db = FirebaseDatabase.getInstance();
        category = db.getReference("Food");

        recyclermenu = (RecyclerView) findViewById(R.id.recycler_menu);
        recyclermenu.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        recyclermenu.setLayoutManager(layoutManager); ////https://www.simplifiedcoding.net/create-options-menu-recyclerview-item-tutorial/
        loadCategory();


        addelement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent choose = new Intent(CalorieCounter.this,Add.class);
                startActivity(choose);
                finish();
            }
        });
    }

    private void loadCategory() //use of CategoryView
    {
        FirebaseRecyclerAdapter<Food, CategoryView> adapter = new FirebaseRecyclerAdapter<Food, CategoryView>(Food.class,R.layout.category_item, CategoryView.class,category)
        //https://stackoverflow.com/questions/55025409/how-to-implement-firebase-recycler-adapter-in-newer-version-of-android-3-1-and-h
            //https://www.learnhowtoprogram.com/android/data-persistence/firebase-recycleradapter
        {
            @Override
            protected  void populateViewHolder(CategoryView viewHolder, Food model, int position){

                viewHolder.txtCategoryName.setText(model.getName());
                viewHolder.txtCategoryMeasure.setText(model.getMeasure());

                final Food clickItem = model;
                viewHolder.setItemClickListener(new ItemClickListener() {
                    //counts together the total amount of calories selected
                    @Override
                    public void onClick(View view, int position, boolean isLongClick) {

                        String lab,lab2;
                        String cal=clickItem.getCal();
                        float calorie=Float.parseFloat(cal);
                        tally+=calorie;
                        lab = "Total Calories: ";
                        lab2 = lab + tally;
                        caltext.setText(lab2);
                        Toast.makeText(CalorieCounter.this,""+clickItem.getName() + " Calories: " +clickItem.getCal(),Toast.LENGTH_SHORT).show();

                    }
                });
                reset.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        tally=0.0;
                        caltext.setText("");
                        Toast.makeText(CalorieCounter.this, "Calories have been reset", Toast.LENGTH_SHORT).show();
                    }
                });

            }
        };
        recyclermenu.setAdapter(adapter);
    }

}


