package com.example.FitnessApp.Model;

public class Food {
    private String Name,Measure,Cal;

    public Food() {
    }

    public Food( String name, String measure,String calorie) {
        Name = name;
        Measure = measure;
        Cal=calorie;
    }

//get and set methods for Food to be used
    //https://firebase.google.com/docs/database/android/read-and-write

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }
    public String getMeasure() {
        return Measure;
    }

    public void setMeasure(String measure) {
        Measure = measure;
    }

    public String getCal() {
        return Cal;
    }

    public void setCal(String calorie) {
        Cal = calorie;
    }
}
