package com.example.FitnessApp;

import android.content.Context;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class BMI extends AppCompatActivity {

    Button showPopupBtn, closePopupBtn;
    PopupWindow popupWindow;
    LinearLayout linearLayout1;

    private EditText height;
    private EditText weight;
    private TextView result;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        height = (EditText) findViewById(R.id.height);
        weight = (EditText) findViewById(R.id.weight);
        result = (TextView) findViewById(R.id.result);


        showPopupBtn = (Button) findViewById(R.id.showPopupBtn);
        linearLayout1 = (LinearLayout) findViewById(R.id.linearLayout1);

        showPopupBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //https://stackoverflow.com/questions/5944987/how-to-create-a-popup-window-popupwindow-in-android
                //shows the popup xml file
                LayoutInflater layoutInflater = (LayoutInflater) BMI.this.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                View customView = layoutInflater.inflate(R.layout.activity_popup,null);

                closePopupBtn = (Button) customView.findViewById(R.id.closePopupBtn);

                //Pop-up window is initiatied
                popupWindow = new PopupWindow(customView, LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);

                //the popup is then displayed
                popupWindow.showAtLocation(linearLayout1, Gravity.CENTER, 0, 0);

                //closes the window
                closePopupBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        popupWindow.dismiss();
                    }
                    //displays bmi chart
                });
            }
        });
    }

    public void calculateBMI(View v) { //method to calculate the BMI value
        String heightStr = height.getText().toString();
        String weightStr = weight.getText().toString();

        if (heightStr != null && !"".equals(heightStr)
                && weightStr != null  &&  !"".equals(weightStr)) {
            float heightValue = Float.parseFloat(heightStr) / 100;
            float weightValue = Float.parseFloat(weightStr);
            float bmi = weightValue / (heightValue * heightValue);
            displayBMI(bmi);
        } //https://codezips.com/android/bmi-calculator-in-android-with-source-code/
    }

    private void displayBMI(float bmi) { //the given bmi is classified according to class
        String bmiLabel = "";
        if (Float.compare(bmi, 13f) <= 0) {
            bmiLabel = getString(R.string.very_severely_underweight);
        } else if (Float.compare(bmi, 13f) > 0  &&  Float.compare(bmi, 15f) <= 0) {
            bmiLabel = getString(R.string.severely_underweight);
        } else if (Float.compare(bmi, 15f) > 0  &&  Float.compare(bmi, 17f) <= 0) {
            bmiLabel = getString(R.string.underweight);
        } else if (Float.compare(bmi, 17f) > 0  &&  Float.compare(bmi, 23f) <= 0) {
            bmiLabel = getString(R.string.normal);
        } else if (Float.compare(bmi, 23f) > 0  &&  Float.compare(bmi, 28f) <= 0) {
            bmiLabel = getString(R.string.overweight);
        } else if (Float.compare(bmi, 28f) > 0  &&  Float.compare(bmi, 32f) <= 0) {
            bmiLabel = getString(R.string.obese_class_i);
        } else if (Float.compare(bmi, 32f) > 0  &&  Float.compare(bmi, 40f) <= 0) {
            bmiLabel = getString(R.string.obese_class_ii);
        } else {
            bmiLabel = getString(R.string.obese_class_iii);
        }

        bmiLabel = "BMI: " + bmi + "\n\n" + bmiLabel;
        result.setText(bmiLabel);
    }
}