package com.example.FitnessApp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

public class Workout extends AppCompatActivity {

    private ImageView img1,img2,img3,img4,img5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_split_workout);

        img1=(ImageView)findViewById(R.id.img1);
        img2=(ImageView)findViewById(R.id.img2);
        img3=(ImageView)findViewById(R.id.img3);
        img4=(ImageView)findViewById(R.id.img4);
        img5=(ImageView)findViewById(R.id.img5);

        String url1= "https://media.istockphoto.com/vectors/jumping-jacks-exercise-girl-workout-silhouettes-illustration-vector-id1213616980";
        String url2="https://qph.cf2.quoracdn.net/main-qimg-2a1af8e60656fc5d343d22ea7bd6a2f8-lq";
        String url3="https://thumbs.dreamstime.com/z/abdominal-crunch-exercise-workout-vector-illustration-white-background-vector-illustration-abdominal-crunch-exercise-215590322.jpg";
        String url4="https://media.istockphoto.com/vectors/sport-exercise-step-up-onto-chair-vector-id1060749074";
        String url5="https://innovativept.net/wp-content/uploads/2020/06/Screen-Shot-2020-06-01-at-2.25.03-PM-820x1024.png";

        Glide.with(getApplicationContext()).load(url1).into(img1);
        Glide.with(getApplicationContext()).load(url2).into(img2);
        Glide.with(getApplicationContext()).load(url3).into(img3);
        Glide.with(getApplicationContext()).load(url4).into(img4);
        Glide.with(getApplicationContext()).load(url5).into(img5);
        //loads the images of exercises

    };
    //clickable video tutorials of exercise
    public  void openvid1 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=nGaXj3kkmrU"));
        startActivity(browserIntent);
    }

    public  void openvid2 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=_l3ySVKYVJ8"));
        startActivity(browserIntent);
    }
    public  void openvid3 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Xyd_fa5zoEU"));
        startActivity(browserIntent);
    }

    public  void openvid4 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=aajhW7DD1EA"));
        startActivity(browserIntent);
    }
    public  void openvid5 (View view){
        Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=aclHkVaku9U"));
        startActivity(browserIntent);
    }
    //ref https://www.youtube.com/watch?v=VwhJaGVmjwg
}

